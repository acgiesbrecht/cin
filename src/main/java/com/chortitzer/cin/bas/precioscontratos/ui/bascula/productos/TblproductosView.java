package com.chortitzer.cin.bas.precioscontratos.ui.bascula.productos;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class TblproductosView extends AbstractView<Tblproductos> implements FxmlView<TblproductosViewModel> {

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtMateriaPrima;

    @InjectViewModel
    private TblproductosViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtDescripcion.textProperty().bindBidirectional(viewModel.descripcionProperty());
            txtMateriaPrima.textProperty().bindBidirectional(viewModel.materiaPrimaProperty(), new NumberStringConverter());
        });
    }

    @FXML
    void add() {
        addAbstract();
        viewModel.add(new Tblproductos());
        txtDescripcion.requestFocus();
    }
}
