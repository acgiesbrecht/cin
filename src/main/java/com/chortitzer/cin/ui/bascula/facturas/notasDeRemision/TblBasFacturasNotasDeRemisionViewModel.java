package com.chortitzer.cin.ui.bascula.facturas.notasDeRemision;

import com.chortitzer.cin.model.bascula.TblBasFacturas;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.model.dao.bascula.TblBasFacturasDao;
import com.chortitzer.cin.model.dao.bascula.TblBasNotasDeRemisionDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionView;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TblBasFacturasNotasDeRemisionViewModel extends AbstractViewModel<TblBasNotasDeRemision>{

    @Inject
    TblBasNotasDeRemisionDao tblBasNotasDeRemisionDao;

    @Inject
    Stage primayStage;

    private Executor exec;

    public void initialize() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        updateItemsList();
    }

    public final ObjectProperty<TblBasFacturas> factura = new SimpleObjectProperty<>();
    public final ObservableList<TblBasNotasDeRemision> itemsList = FXCollections.observableArrayList();
    public final ObjectProperty<TblBasNotasDeRemision> selectedItem = new SimpleObjectProperty<>();
    public ModelWrapper<TblBasNotasDeRemision> itemWrapper = new ModelWrapper<>();

    public void updateItemsList() {

        itemsList.clear();
        Task<ObservableList<TblBasNotasDeRemision>> task = new Task<ObservableList<TblBasNotasDeRemision>>() {
            @Override
            protected ObservableList<TblBasNotasDeRemision> call() throws Exception {
                ObservableList<TblBasNotasDeRemision> list = FXCollections.observableArrayList(tblBasNotasDeRemisionDao.findAllByFacturaOrSinFactura(factura.get()));
                return list;
            }
        };
        task.setOnFailed(e->{task.getException().printStackTrace();});
        task.setOnSucceeded(e -> {itemsList.setAll(task.getValue());});
        loadingInProgressProperty().bind(task.runningProperty());
        exec.execute(task);
    }
}
