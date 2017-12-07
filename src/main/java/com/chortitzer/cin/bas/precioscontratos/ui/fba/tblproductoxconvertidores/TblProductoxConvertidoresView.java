package com.chortitzer.cin.bas.precioscontratos.ui.fba.tblproductoxconvertidores;

import com.chortitzer.cin.bas.precioscontratos.model.fba.Productox;
import com.chortitzer.cin.bas.precioscontratos.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import com.panemu.tiwulfx.control.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class TblProductoxConvertidoresView extends AbstractView<TblProductoxConvertidores> implements FxmlView<TblProductoxConvertidoresViewModel> {

    @FXML
    private TypeAheadField<Productox> thfProducto;
    @FXML
    private TextField txtKgPorBolsa;
    @FXML
    private TextField txtDescripcionEnEtiqueta;

    @InjectViewModel
    private TblProductoxConvertidoresViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtKgPorBolsa.textProperty().bindBidirectional(viewModel.kgPorBolsaProperty(), new NumberStringConverter());
            txtDescripcionEnEtiqueta.textProperty().bindBidirectional(viewModel.descripcionEnEtiquetaProperty());
        });

        thfProducto.setItems(viewModel.getProductos());
    }

    @FXML
    void add() {
        viewModel.add();
        thfProducto.requestFocus();
    }
}