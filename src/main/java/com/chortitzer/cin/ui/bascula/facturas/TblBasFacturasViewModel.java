package com.chortitzer.cin.ui.bascula.facturas;

import com.chortitzer.cin.model.bascula.*;
import com.chortitzer.cin.model.dao.bascula.TblBasContratosDao;
import com.chortitzer.cin.model.dao.bascula.TblBasFacturasDao;
import com.chortitzer.cin.model.dao.bascula.TblempresaDao;
import com.chortitzer.cin.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionView;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TblBasFacturasViewModel extends AbstractViewModel<TblBasFacturas>{

    @Inject
    TblBasFacturasDao tblBasFacturasDao;

    @Inject
    Stage primayStage;

    public void initialize() {
        setDao(tblBasFacturasDao);
        initializeAbstract();
    }

    public ObjectProperty<LocalDateTime> fechaProperty() { return itemWrapper.field("fecha", TblBasFacturas::getFecha, TblBasFacturas::setFecha); }
    public StringProperty nroProperty() { return itemWrapper.field("nro", TblBasFacturas::getNro, TblBasFacturas::setNro); }
    public StringProperty nroTimbradoProperty() { return itemWrapper.field("nroTimbrado", TblBasFacturas::getNroTimbrado, TblBasFacturas::setNroTimbrado); }
    public StringProperty razonSocialProperty() { return itemWrapper.field("razonSocial", TblBasFacturas::getRazonSocial, TblBasFacturas::setRazonSocial); }
    public StringProperty rucProperty() { return itemWrapper.field("ruc", TblBasFacturas::getRuc, TblBasFacturas::setRuc); }
    public LongProperty cantidadProperty() { return itemWrapper.field("cantidad", TblBasFacturas::getCantidad, TblBasFacturas::setCantidad); }

    public void showTblBasNotasDeRemisionView() {
        final ViewTuple<TblBasNotasDeRemisionView, TblBasNotasDeRemisionViewModel> tuple = FluentViewLoader.fxmlView(TblBasNotasDeRemisionView.class).load();
        if (selectedItem.get().getIdNotaDeRemision() != null) {
            tuple.getViewModel().itemWrapper.set(selectedItem.get().getIdNotaDeRemision());
        } else {
            tuple.getViewModel().itemWrapper.set(new TblBasNotasDeRemision());
        }
        Stage modal = new Stage();
        modal.setTitle("Notas de Remisión");
        modal.initOwner(primayStage);
        tuple.getCodeBehind().owningStage.set(modal);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setScene(new Scene(tuple.getView()));
        modal.showAndWait();

        if (tuple.getViewModel().commitStatus == TblBasNotasDeRemisionViewModel.CommitStatus.COMMIT) {
            selectedItem.get().setIdNotaDeRemision(tuple.getViewModel().itemWrapper.get());
            tblpesadasDao.merge(selectedItem.get());
        }else if (tuple.getViewModel().commitStatus == TblBasNotasDeRemisionViewModel.CommitStatus.DELETE) {
            //tblBasNotasDeRemisionDao.remove(selectedItem.get().getIdNotaDeRemision());
            selectedItem.get().setIdNotaDeRemision(null);
            tblpesadasDao.merge(selectedItem.get());
        }
    }

}
