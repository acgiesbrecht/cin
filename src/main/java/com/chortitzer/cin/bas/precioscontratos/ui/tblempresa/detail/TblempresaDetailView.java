package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.detail;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import javax.inject.Inject;

public class TblempresaDetailView implements FxmlView<TblempresaDetailViewModel> {

	@FXML
	private TextField txtId;

    @FXML
    private TextField txtNombre;

	@InjectViewModel
	private TblempresaDetailViewModel viewModel;

	@Inject
	private Stage primaryStage;

	public void initialize() {
        txtId.textProperty().bindBidirectional(viewModel.idProperty(), new NumberStringConverter());
	    txtNombre.textProperty().bindBidirectional(viewModel.nombreProperty());

    }

}
