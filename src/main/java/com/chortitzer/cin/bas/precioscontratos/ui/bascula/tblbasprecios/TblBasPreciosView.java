package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbasprecios;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasPrecios;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import com.panemu.tiwulfx.control.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblBasPreciosView extends AbstractView<TblBasPrecios> implements FxmlView<TblBasPreciosViewModel> {

    @FXML
    private DateTimePicker dtpFecha;
    @FXML
    private TypeAheadField<Tblproductos> thfProducto;
    @FXML
    private TextField txtPrecioPorKg;

    @InjectViewModel
    private TblBasPreciosViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        thfProducto.setItems(viewModel.getProductos());
    }

    @FXML
    void add() {
        viewModel.add();
        dtpFecha.setDateTimeValue(LocalDateTime.now());
        thfProducto.requestFocus();
    }
}
