package com.chortitzer.cin.ui.fba.tblproductoxconvertidores;

import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.model.dao.fba.ProductoxDao;
import com.chortitzer.cin.model.dao.fba.TblProductoxConvertidoresDao;
import com.chortitzer.cin.model.fba.Productox;
import com.chortitzer.cin.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.model.dao.fba.ProductoxDao;
import com.chortitzer.cin.model.dao.fba.TblProductoxConvertidoresDao;
import com.chortitzer.cin.model.fba.Productox;
import com.chortitzer.cin.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.ui.AbstractViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class TblProductoxConvertidoresViewModel extends AbstractViewModel<TblProductoxConvertidores> {

    @Inject
    ProductoxDao productoxDao;
    private final ObservableList<Productox> productosList = FXCollections.observableArrayList();

    @Inject
    TblProductoxConvertidoresDao tblProductoxConvertidoresDao;

    public void initialize() {
        setDao(tblProductoxConvertidoresDao);
        initializeAbstract();
        productosList.addAll(productoxDao.findAll());
    }

    public ObjectProperty<Productox> productoProperty() { return itemWrapper.field("producto", TblProductoxConvertidores::getIdProductox, TblProductoxConvertidores::setIdProductox); }
    public IntegerProperty kgPorBolsaProperty() { return itemWrapper.field("kgPorBolsa", TblProductoxConvertidores::getKgPorBolsa, TblProductoxConvertidores::setKgPorBolsa); }
    public StringProperty descripcionEnEtiquetaProperty() { return itemWrapper.field("descripcionEnEtiqueta", TblProductoxConvertidores::getDescripcionEnEtiqueta, TblProductoxConvertidores::setDescripcionEnEtiqueta); }
    
    public ObservableList<Productox> getProductos() { return productosList; }

}
