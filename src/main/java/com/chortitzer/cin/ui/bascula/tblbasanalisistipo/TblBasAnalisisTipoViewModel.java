package com.chortitzer.cin.ui.bascula.tblbasanalisistipo;

import com.chortitzer.cin.model.bascula.TblBasAnalisisTipo;
import com.chortitzer.cin.model.bascula.TblBasPrecios;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.model.dao.bascula.TblBasAnalisisTipoDao;
import com.chortitzer.cin.model.dao.bascula.TblBasPreciosDao;
import com.chortitzer.cin.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblBasAnalisisTipoViewModel extends AbstractViewModel<TblBasAnalisisTipo>{

    @Inject
    TblBasAnalisisTipoDao tblBasAnalisisTipoDao;

    @Inject
    TblBasPreciosDao tblBasPreciosDao;

    public void initialize() {
        setDao(tblBasAnalisisTipoDao);
        initializeAbstract();
    }

    public StringProperty descripcionProperty() { return itemWrapper.field("descripcion", TblBasAnalisisTipo::getDescripcion, TblBasAnalisisTipo::setDescripcion); }
    public StringProperty unidadDeMedidaProperty() { return itemWrapper.field("unidadDeMedida", TblBasAnalisisTipo::getUnidadDeMedida, TblBasAnalisisTipo::setUnidadDeMedida); }
    public IntegerProperty cantidadDecimalesProperty() { return itemWrapper.field("cantidadDecimales", TblBasAnalisisTipo::getCantidadDecimales, TblBasAnalisisTipo::setCantidadDecimales); }

}
