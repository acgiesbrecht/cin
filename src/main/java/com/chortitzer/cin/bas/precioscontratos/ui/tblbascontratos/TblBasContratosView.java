package com.chortitzer.cin.bas.precioscontratos.ui.tblbascontratos;

import com.chortitzer.cin.bas.precioscontratos.model.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import com.chortitzer.cin.bas.precioscontratos.utils.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.table.TableFilter;

import javax.inject.Inject;

public class TblBasContratosView implements FxmlView<TblBasContratosViewModel> {

    @Inject
    Dialog dialog;

    @FXML
    private TableView<TblBasContratos> contratosTable;

    @FXML
    private DatePicker dtpFecha;
    @FXML
    private TypeAheadField<Tblempresa> thfEmpresa;
    @FXML
    private TypeAheadField<Tblproductos> thfProducto;
    @FXML
    private TextField txtPrecioPorKg;


    @InjectViewModel
    private TblBasContratosViewModel viewModel;

    public void initialize() {
        contratosTable.setItems(viewModel.getContratos());
        TableFilter<TblBasContratos> tableFilter = TableFilter.forTableView(contratosTable).apply();
        //TableFilter tableFilter = new TableFilter(contratosTable);
        tableFilter.setSearchStrategy((input, target) -> {
            try {
                return target.toString().toLowerCase().contains(input.toString().toLowerCase());
            } catch (Exception e) {
                return false;
            }
        });

        viewModel.selectedContratoProperty().bind(contratosTable.getSelectionModel().selectedItemProperty());

        // When the selectedTableRowProperty changes in the viewModel we need to update the master
        viewModel.setOnSelect(vm -> contratosTable.getSelectionModel().select(vm));

        contratosTable.disableProperty().bind(viewModel.differentProperty());

        contratosTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            //dtpFecha..textProperty().bindBidirectional(viewModel.nombreProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });
    }

    @FXML
    void save() {
        viewModel.save();
    }

    @FXML
    void delete() {
        viewModel.delete();
    }

    @FXML
    void reset() {
        viewModel.reset();
    }

    @FXML
    void add() {
        viewModel.add();
        thfEmpresa.requestFocus();
    }
}
