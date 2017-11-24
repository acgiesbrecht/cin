package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.scopes;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TblempresaMasterDetailScope implements Scope {

	public static final String UPDATE = "Scope.update";

	private final ObjectProperty<Tblempresa> selectedEmpresa = new SimpleObjectProperty<>(this, "selectedEmpresa");

	public ObjectProperty<Tblempresa> selectedEmpresaProperty() {
		return this.selectedEmpresa;
	}

	public final Tblempresa getSelectedEmpresa() {
		return this.selectedEmpresaProperty().get();
	}

	public final void setSelectedEmpresa(final Tblempresa selectedEmpresa) {
		this.selectedEmpresaProperty().set(selectedEmpresa);
	}

}
