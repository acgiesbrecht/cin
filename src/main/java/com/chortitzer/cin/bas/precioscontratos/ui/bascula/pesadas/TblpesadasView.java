package com.chortitzer.cin.bas.precioscontratos.ui.bascula.pesadas;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import com.chortitzer.cin.bas.precioscontratos.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import jidefx.scene.control.field.FormattedTextField;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblpesadasView extends AbstractView<Tblpesadas> implements FxmlView<TblpesadasViewModel> {

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
    private FormattedTextField txtBruto;
    @FXML
    private FormattedTextField txtTara;
    @FXML
    private FormattedTextField txtPrecioPorKg;
    @FXML
    private TextField txtFilter;

    @InjectViewModel
    private TblpesadasViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);

        initializeAbstract();

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pesada -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (pesada.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (pesada.getChapa().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (pesada.getEmpresaid().getNombre().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (pesada.getProductoid().getDescripcion().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

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
        viewModel.add(new Tblpesadas());
        dtpFecha.setDateTimeValue(LocalDateTime.now());
        thfEmpresa.requestFocus();
        addAbstract();
    }
}
