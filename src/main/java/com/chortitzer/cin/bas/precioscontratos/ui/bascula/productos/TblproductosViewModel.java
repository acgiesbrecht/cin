package com.chortitzer.cin.bas.precioscontratos.ui.bascula.productos;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractViewModel;
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
