package com.chortitzer.cin.ui.bascula.productos;

import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;

public class TblproductosViewModel extends AbstractViewModel<Tblproductos> {

    @Inject
    TblproductosDao tblproductosDao;

    public void initialize() {
        setDao(tblproductosDao);
        initializeAbstract();
    }

    public StringProperty descripcionProperty() { return itemWrapper.field("descripcion", Tblproductos::getDescripcion, Tblproductos::setDescripcion); }
    public IntegerProperty materiaPrimaProperty() { return itemWrapper.field("materiaPrima", Tblproductos::getMateriaprima, Tblproductos::setMateriaprima); }

}
