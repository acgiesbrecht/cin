package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbascontratos;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblBasContratosDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractViewModel;
import javafx.beans.property.*;
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
    public ObjectProperty<Tblempresa> empresaProperty() { return itemWrapper.field("empresa", TblBasContratos::getIdEmpresa, TblBasContratos::setIdEmpresa); }
    public ObjectProperty<Tblproductos> productoProperty() { return itemWrapper.field("producto", TblBasContratos::getIdProducto, TblBasContratos::setIdProducto); }
    public IntegerProperty precioProperty() { return itemWrapper.field("precioGsPorKg", TblBasContratos::getPrecioGsPorKg, TblBasContratos::setPrecioGsPorKg); }

    public ObservableList<Tblempresa> getEmpresas() { return empresasList; }
    public ObservableList<Tblproductos> getProductos() { return productosList; }

}
