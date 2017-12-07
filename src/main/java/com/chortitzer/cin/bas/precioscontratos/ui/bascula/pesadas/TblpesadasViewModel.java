package com.chortitzer.cin.bas.precioscontratos.ui.bascula.pesadas;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblpesadasDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblproductosDao;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblpesadasViewModel extends AbstractViewModel<Tblpesadas>{

    @Inject
    TblempresaDao tblempresaDao;
    private final ObservableList<Tblempresa> empresasList = FXCollections.observableArrayList();

    @Inject
    TblproductosDao tblproductosDao;
    private final ObservableList<Tblproductos> productosList = FXCollections.observableArrayList();

    @Inject
    TblpesadasDao tblpesadasDao;

    public void initialize() {
        setDao(tblpesadasDao);
        initializeAbstract();
        empresasList.addAll(tblempresaDao.findAll());
        productosList.addAll(tblproductosDao.findAll());
    }

    public ObjectProperty<LocalDateTime> fechaProperty() { return itemWrapper.field("fecha", Tblpesadas::getFechahora, Tblpesadas::setFechahora); }
    public StringProperty idRemisionProperty() { return itemWrapper.field("idRemision", Tblpesadas::getIdRemision, Tblpesadas::setIdRemision); }
    public StringProperty chapaProperty() { return itemWrapper.field("chapa", Tblpesadas::getChapa, Tblpesadas::setChapa); }
    public ObjectProperty<Tblempresa> empresaProperty() { return itemWrapper.field("empresa", Tblpesadas::getEmpresaid, Tblpesadas::setEmpresaid); }
    public ObjectProperty<Tblproductos> productoProperty() { return itemWrapper.field("producto", Tblpesadas::getProductoid, Tblpesadas::setProductoid); }
    public IntegerProperty brutoProperty() { return itemWrapper.field("bruto", Tblpesadas::getBruto, Tblpesadas::setBruto); }
    public IntegerProperty taraProperty() { return itemWrapper.field("tara", Tblpesadas::getTara, Tblpesadas::setTara); }
    public IntegerProperty precioProperty() { return itemWrapper.field("precioGsPorKg", Tblpesadas::getPrecioGsPorKg, Tblpesadas::setPrecioGsPorKg); }

    public ObservableList<Tblempresa> getEmpresas() { return empresasList; }
    public ObservableList<Tblproductos> getProductos() { return productosList; }

}
