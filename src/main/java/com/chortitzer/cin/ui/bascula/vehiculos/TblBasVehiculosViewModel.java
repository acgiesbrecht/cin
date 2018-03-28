package com.chortitzer.cin.ui.bascula.vehiculos;

import com.chortitzer.cin.model.bascula.TblBasVehiculos;
import com.chortitzer.cin.model.dao.bascula.TblBasVehiculosDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;

public class TblBasVehiculosViewModel extends AbstractViewModel<TblBasVehiculos> {

    public StringProperty razonSocialProperty = new SimpleStringProperty();
    @Inject
    TblBasVehiculosDao tblBasVehiculosDao;
    @Inject
    TblContribuyentesDao tblContribuyentesDao;
    @Inject
    Utils UTILS;

    public void initialize() {
        setDao(tblBasVehiculosDao);
        initializeAbstract();
        rucProperty().addListener((observableValue, o, n) -> {
            if (UTILS.rucPredicate.test(n)) {
                try {
                    razonSocialProperty.set(tblContribuyentesDao.findByRuc(rucProperty().get()).getRazonSocial());
                } catch (Exception ex) {
                    razonSocialProperty.set("");
                }
            }
        });
    }

    public StringProperty chapaProperty() {
        return itemWrapper.field("chapa", TblBasVehiculos::getChapa, TblBasVehiculos::setChapa);
    }

    public StringProperty rucProperty() {
        return itemWrapper.field("ruc", TblBasVehiculos::getRuc, TblBasVehiculos::setRuc);
    }
}
