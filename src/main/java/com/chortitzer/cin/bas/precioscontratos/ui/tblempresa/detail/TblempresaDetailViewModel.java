package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.detail;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.scopes.TblempresaMasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;

public class TblempresaDetailViewModel implements ViewModel {

	private final ModelWrapper<Tblempresa> empresaWrapper = new ModelWrapper<>();

	@InjectScope
	private TblempresaMasterDetailScope mdScope;

	@Inject
	private TblempresaDao tblempresaDao;

	//private final ReadOnlyBooleanWrapper removeItemDisabled = new ReadOnlyBooleanWrapper();

	public void initialize() {

        mdScope.selectedEmpresaProperty().addListener((observable, oldValue, newValue) -> {
            /*if (newValue != null) {
                empresaWrapper.set(newValue);
                empresaWrapper.reload();
            }*/
            nombreProperty().bindBidirectional(newValue.nombreProperty());
        });


	}

    public IntegerProperty idProperty() {
        return empresaWrapper.field("id", Tblempresa::getId, Tblempresa::setId);
    }

    public StringProperty nombreProperty() {
        return empresaWrapper.field("nombre", Tblempresa::getNombre, Tblempresa::setNombre);
    }

}
