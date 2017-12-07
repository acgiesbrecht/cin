package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbasprecios;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasPrecios;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblBasPreciosDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblBasPreciosViewModel extends AbstractViewModel<TblBasPrecios>{

    @Inject
    TblproductosDao tblproductosDao;
    private final ObservableList<Tblproductos> productosList = FXCollections.observableArrayList();

    @Inject
    TblBasPreciosDao tblBasPreciosDao;

    public void initialize() {
        setDao(tblBasPreciosDao);
        initializeAbstract();
        productosList.addAll(tblproductosDao.findAll());
    }

    public ObjectProperty<LocalDateTime> fechaProperty() { return itemWrapper.field("fecha", TblBasPrecios::getFechahoraVigencia, TblBasPrecios::setFechahoraVigencia); }
    public ObjectProperty<Tblproductos> productoProperty() { return itemWrapper.field("producto", TblBasPrecios::getIdProducto, TblBasPrecios::setIdProducto); }
    public IntegerProperty precioProperty() { return itemWrapper.field("precioGsPorKg", TblBasPrecios::getValorGsPorKg, TblBasPrecios::setValorGsPorKg); }
    
    public ObservableList<Tblproductos> getProductos() { return productosList; }

}
