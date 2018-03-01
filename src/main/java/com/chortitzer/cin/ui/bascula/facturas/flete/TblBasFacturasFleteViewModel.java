package com.chortitzer.cin.ui.bascula.facturas.flete;

import com.chortitzer.cin.model.bascula.TblBasFacturasFlete;
import com.chortitzer.cin.model.dao.bascula.TblBasFacturasFleteDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.ui.bascula.facturas.flete.notasDeRemision.TblBasFacturasFleteNotasDeRemisionView;
import com.chortitzer.cin.ui.bascula.facturas.flete.notasDeRemision.TblBasFacturasFleteNotasDeRemisionViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblBasFacturasFleteViewModel extends AbstractViewModel<TblBasFacturasFlete> {

    @Inject
    TblBasFacturasFleteDao tblBasFacturasFleteDao;

    @Inject
    Stage primayStage;

    public void initialize() {
        setDao(tblBasFacturasFleteDao);
        initializeAbstract();
    }

    public IntegerProperty idProperty() {
        return itemWrapper.field("id", TblBasFacturasFlete::getId, TblBasFacturasFlete::setId);
    }

    public ObjectProperty<LocalDateTime> fechaProperty() {
        return itemWrapper.field("fecha", TblBasFacturasFlete::getFecha, TblBasFacturasFlete::setFecha);
    }

    public StringProperty nroProperty() {
        return itemWrapper.field("nro", TblBasFacturasFlete::getNro, TblBasFacturasFlete::setNro);
    }

    public StringProperty nroTimbradoProperty() {
        return itemWrapper.field("nroTimbrado", TblBasFacturasFlete::getNroTimbrado, TblBasFacturasFlete::setNroTimbrado);
    }

    public StringProperty razonSocialProperty() {
        return itemWrapper.field("razonSocial", TblBasFacturasFlete::getRazonSocial, TblBasFacturasFlete::setRazonSocial);
    }

    public StringProperty rucProperty() {
        return itemWrapper.field("ruc", TblBasFacturasFlete::getRuc, TblBasFacturasFlete::setRuc);
    }

    public LongProperty cantidadProperty() {
        return itemWrapper.field("cantidad", TblBasFacturasFlete::getCantidad, TblBasFacturasFlete::setCantidad);
    }

    public void showTblBasNotasDeRemisionView() {
        final ViewTuple<TblBasFacturasFleteNotasDeRemisionView, TblBasFacturasFleteNotasDeRemisionViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasFleteNotasDeRemisionView.class).load();
        tuple.getViewModel().factura.set(selectedItem.get());

        Stage modal = new Stage();
        modal.setTitle("Notas de Remisión Aplicadas");
        modal.initOwner(primayStage);
        tuple.getCodeBehind().owningStage.set(modal);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/cin-icon.png")));
        modal.setScene(new Scene(tuple.getView()));
        modal.showAndWait();
        updateItemsList();
    }

}
