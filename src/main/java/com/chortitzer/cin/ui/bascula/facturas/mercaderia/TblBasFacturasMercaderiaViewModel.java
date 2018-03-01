package com.chortitzer.cin.ui.bascula.facturas.mercaderia;

import com.chortitzer.cin.model.bascula.TblBasFacturasMercaderia;
import com.chortitzer.cin.model.dao.bascula.TblBasFacturasMercaderiaDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.ui.bascula.facturas.mercaderia.notasDeRemision.TblBasFacturasMercaderiaNotasDeRemisionView;
import com.chortitzer.cin.ui.bascula.facturas.mercaderia.notasDeRemision.TblBasFacturasMercaderiaNotasDeRemisionViewModel;
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

public class TblBasFacturasMercaderiaViewModel extends AbstractViewModel<TblBasFacturasMercaderia> {

    @Inject
    TblBasFacturasMercaderiaDao tblBasFacturasMercaderiaDao;

    @Inject
    Stage primayStage;

    public void initialize() {
        setDao(tblBasFacturasMercaderiaDao);
        initializeAbstract();
    }

    public ObjectProperty<LocalDateTime> fechaProperty() {
        return itemWrapper.field("fecha", TblBasFacturasMercaderia::getFecha, TblBasFacturasMercaderia::setFecha);
    }

    public StringProperty nroProperty() {
        return itemWrapper.field("nro", TblBasFacturasMercaderia::getNro, TblBasFacturasMercaderia::setNro);
    }

    public StringProperty nroTimbradoProperty() {
        return itemWrapper.field("nroTimbrado", TblBasFacturasMercaderia::getNroTimbrado, TblBasFacturasMercaderia::setNroTimbrado);
    }

    public StringProperty razonSocialProperty() {
        return itemWrapper.field("razonSocial", TblBasFacturasMercaderia::getRazonSocial, TblBasFacturasMercaderia::setRazonSocial);
    }

    public StringProperty rucProperty() {
        return itemWrapper.field("ruc", TblBasFacturasMercaderia::getRuc, TblBasFacturasMercaderia::setRuc);
    }

    public LongProperty cantidadProperty() {
        return itemWrapper.field("cantidad", TblBasFacturasMercaderia::getCantidad, TblBasFacturasMercaderia::setCantidad);
    }

    public IntegerProperty nroOcProperty() {
        return itemWrapper.field("nroOc", TblBasFacturasMercaderia::getNroOc, TblBasFacturasMercaderia::setNroOc);
    }

    public void showTblBasNotasDeRemisionView() {
        final ViewTuple<TblBasFacturasMercaderiaNotasDeRemisionView, TblBasFacturasMercaderiaNotasDeRemisionViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasMercaderiaNotasDeRemisionView.class).load();
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
