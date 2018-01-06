package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbascontratos;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import com.chortitzer.cin.bas.precioscontratos.utils.tiwulfx.TypeAheadField;
import com.chortitzer.cin.bas.precioscontratos.utils.tiwulfx.TypeAheadTableCell;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblBasContratosView extends AbstractView<TblBasContratos> implements FxmlView<TblBasContratosViewModel> {

    @FXML
    private DateTimePicker dtpFecha;
    @FXML
    private TypeAheadField<Tblempresa> thfEmpresa;
    @FXML
    private TypeAheadField<Tblproductos> thfProducto;
    @FXML
    private TextField txtPrecioPorKg;

    @InjectViewModel
    private TblBasContratosViewModel viewModel;

    public void initialize() {

        //empresaColumn.setCellFactory(TypeAheadTableCell::new);

        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        thfEmpresa.setItems(viewModel.getEmpresas());
        thfProducto.setItems(viewModel.getProductos());
    }

    @FXML
    void add() {
        addAbstract();
        viewModel.add(new TblBasContratos());
        dtpFecha.setDateTimeValue(LocalDateTime.now());
        thfEmpresa.requestFocus();
    }
}
