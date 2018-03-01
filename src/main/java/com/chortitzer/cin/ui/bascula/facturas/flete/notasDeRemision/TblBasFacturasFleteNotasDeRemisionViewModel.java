package com.chortitzer.cin.ui.bascula.facturas.flete.notasDeRemision;

import com.chortitzer.cin.model.bascula.TblBasFacturasFlete;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.model.dao.bascula.TblBasFacturasMercaderiaDao;
import com.chortitzer.cin.model.dao.bascula.TblBasNotasDeRemisionDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TblBasFacturasFleteNotasDeRemisionViewModel extends AbstractViewModel<TblBasNotasDeRemision> {

    public ObjectProperty<TblBasFacturasFlete> factura = new SimpleObjectProperty<>();
    public final ObservableList<TblBasNotasDeRemision> itemsList = FXCollections.observableArrayList();

    @Inject
    TblBasNotasDeRemisionDao tblBasNotasDeRemisionDao;
    @Inject
    TblBasFacturasMercaderiaDao tblBasFacturasDao;

    @Inject
    Stage primayStage;

    public enum CommitStatus {COMMIT, CANCEL}

    public CommitStatus commitStatus;
    private Executor exec;

    public void initialize() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        factura.addListener(e -> {
            updateItemsList();
        });
    }

    public void updateItemsList() {

        itemsList.clear();
        Task<ObservableList<TblBasNotasDeRemision>> task = new Task<ObservableList<TblBasNotasDeRemision>>() {
            @Override
            protected ObservableList<TblBasNotasDeRemision> call() throws Exception {
                ObservableList<TblBasNotasDeRemision> list = FXCollections.observableArrayList(tblBasNotasDeRemisionDao.findAllByFacturaFleteOrSinFactura(factura.get()));
                for (TblBasNotasDeRemision ndr : list) {
                    if (ndr.getIdFacturaFlete() != null && ndr.getIdFacturaFlete().equals(factura.get())) {
                        ndr.setSelected(true);
                    }
                }
                return list;
            }
        };
        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });
        task.setOnSucceeded(e -> {
            itemsList.setAll(task.getValue());
        });
        loadingInProgressProperty().bind(task.runningProperty());
        exec.execute(task);
    }

    public void commit() {
        List<TblBasNotasDeRemision> mergeList = new ArrayList<>();
        if(factura.get().getTblBasNotasDeRemisionList().size()>0) {
            factura.get().getTblBasNotasDeRemisionList().stream().forEach(ndr -> {
                ndr.setIdFacturaFlete(null);
                mergeList.add(ndr);
            });
            tblBasNotasDeRemisionDao.merge(mergeList);
        }
        List<TblBasNotasDeRemision> mergeList2 = new ArrayList<>();
        itemsList.stream().forEach(ndr -> {
            if (ndr.getSelected()) {
                ndr.setIdFacturaFlete(factura.get());
                mergeList2.add(ndr);
            }

        });
        tblBasNotasDeRemisionDao.merge(mergeList2);
    }
}
