package com.chortitzer.cin.bas.precioscontratos.ui.bascula.pesadas;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
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

public class TblpesadasView extends AbstractView<Tblpesadas> implements FxmlView<TblpesadasViewModel> {

    @FXML
    private ProgressIndicator loadingIndicator;
    @FXML
    private DateTimePicker dtpFecha;
    @FXML
    private TextField txtIdRemision;
    @FXML
    private TextField txtChapa;
    @FXML
    private TypeAheadField<Tblempresa> thfEmpresa;
    @FXML
    private TypeAheadField<Tblproductos> thfProducto;
    @FXML
    private TextField txtBruto;
    @FXML
    private TextField txtTara;
    @FXML
    private TextField txtPrecioPorKg;

    @InjectViewModel
    private TblpesadasViewModel viewModel;

    public void initialize() {
        loadingIndicator.visibleProperty().bind(viewModel.loadingInProgressProperty());
        setViewModel(viewModel);
        initializeAbstract();

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            txtChapa.textProperty().bindBidirectional(viewModel.chapaProperty());
            txtIdRemision.textProperty().bindBidirectional(viewModel.idRemisionProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtBruto.textProperty().bindBidirectional(viewModel.brutoProperty(), new NumberStringConverter());
            txtTara.textProperty().bindBidirectional(viewModel.taraProperty(), new NumberStringConverter());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        thfEmpresa.setItems(viewModel.getEmpresas());
        thfProducto.setItems(viewModel.getProductos());
    }

    @FXML
    void add() {
        viewModel.add();
        dtpFecha.setDateTimeValue(LocalDateTime.now());
        thfEmpresa.requestFocus();
    }
}
