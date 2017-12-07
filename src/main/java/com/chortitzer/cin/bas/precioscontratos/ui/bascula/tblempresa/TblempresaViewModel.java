package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblempresa;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.dao.bascula.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractViewModel;
import javafx.beans.property.*;

import javax.inject.Inject;

public class TblempresaViewModel extends AbstractViewModel<Tblempresa> {

    @Inject
    TblempresaDao tblempresaDao;

    public void initialize() {
        setDao(tblempresaDao);
        initializeAbstract();
    }

    public StringProperty nombreProperty() { return itemWrapper.field("nombre", Tblempresa::getNombre, Tblempresa::setNombre); }
    public StringProperty rucProperty() { return itemWrapper.field("ruc", Tblempresa::getRuc, Tblempresa::setRuc); }
    public StringProperty ctacteProperty() { return itemWrapper.field("ctacte", Tblempresa::getCuentacorriente, Tblempresa::setCuentacorriente); }

}
