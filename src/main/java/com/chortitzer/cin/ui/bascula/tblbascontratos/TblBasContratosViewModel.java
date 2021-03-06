package com.chortitzer.cin.ui.bascula.tblbascontratos;

import com.chortitzer.cin.model.bascula.TblBasContratos;
import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.model.dao.bascula.TblBasContratosDao;
import com.chortitzer.cin.model.dao.bascula.TblempresaDao;
import com.chortitzer.cin.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblBasContratosViewModel extends AbstractViewModel<TblBasContratos>{

    @Inject
    TblempresaDao tblempresaDao;
    private final ObservableList<Tblempresa> empresasList = FXCollections.observableArrayList();

    @Inject
    TblproductosDao tblproductosDao;
    private final ObservableList<Tblproductos> productosList = FXCollections.observableArrayList();

    @Inject
    TblBasContratosDao tblBasContratosDao;

    public void initialize() {
        setDao(tblBasContratosDao);
        initializeAbstract();
        empresasList.addAll(tblempresaDao.findAll());
        productosList.addAll(tblproductosDao.findAll());
    }

    public ObjectProperty<LocalDateTime> fechaProperty() { return itemWrapper.field("fecha", TblBasContratos::getFecha, TblBasContratos::setFecha); }

    public ObjectProperty<LocalDateTime> fechaFinProperty() {
        return itemWrapper.field("fechaFinVigencia", TblBasContratos::getFechaFinVigencia, TblBasContratos::setFechaFinVigencia);
    }
    public ObjectProperty<Tblempresa> empresaProperty() { return itemWrapper.field("empresa", TblBasContratos::getIdEmpresa, TblBasContratos::setIdEmpresa); }
    public ObjectProperty<Tblproductos> productoProperty() { return itemWrapper.field("producto", TblBasContratos::getIdProducto, TblBasContratos::setIdProducto); }
    public IntegerProperty precioProperty() { return itemWrapper.field("precioGsPorKg", TblBasContratos::getPrecioGsPorKg, TblBasContratos::setPrecioGsPorKg); }

    public IntegerProperty volumenProperty() {
        return itemWrapper.field("volumenKg", TblBasContratos::getVolumenKg, TblBasContratos::setVolumenKg);
    }

    public ObservableList<Tblempresa> getEmpresas() { return empresasList; }
    public ObservableList<Tblproductos> getProductos() { return productosList; }

}
