package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblempresa;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TblempresaView extends AbstractView<Tblempresa> implements FxmlView<TblempresaViewModel> {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtRUC;
    @FXML
    private TextField txtCtaCte;

    @InjectViewModel
    private TblempresaViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtNombre.textProperty().bindBidirectional(viewModel.nombreProperty());
            txtRUC.textProperty().bindBidirectional(viewModel.rucProperty());
            txtCtaCte.textProperty().bindBidirectional(viewModel.ctacteProperty());
        });
    }

    @FXML
    void add() {
        viewModel.add();
        txtNombre.requestFocus();
    }
}
