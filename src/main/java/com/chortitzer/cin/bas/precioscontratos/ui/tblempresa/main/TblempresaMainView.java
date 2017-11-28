package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.main;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.control.table.TableFilter;

import javax.inject.Inject;

public class TblempresaMainView implements FxmlView<TblempresaMainViewModel> {

    @Inject
    Dialog dialog;

    @FXML
    private TableView<Tblempresa> empresaTable;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnSave;

    @InjectViewModel
    private TblempresaMainViewModel viewModel;

    public void initialize() {
        //empresaTable.setItems(viewModel.getEmpresas());
        TableFilter<Tblempresa> tableFilter = TableFilter.forTableView(empresaTable).apply();
        //TableFilter tableFilter = new TableFilter(empresaTable);
        tableFilter.setSearchStrategy((input, target) -> {
            try {
                return target.toString().toLowerCase().contains(input.toString().toLowerCase());
            } catch (Exception e) {
                return false;
            }
        });
        tableFilter.getBackingList().setAll(viewModel.getEmpresas());

        viewModel.selectedEmpresaProperty().bind(empresaTable.getSelectionModel().selectedItemProperty());

        // When the selectedTableRowProperty changes in the viewModel we need to update the master
        viewModel.setOnSelect(vm -> empresaTable.getSelectionModel().select(vm));

        empresaTable.disableProperty().bind(viewModel.differentProperty());

        empresaTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtNombre.textProperty().bindBidirectional(viewModel.nombreProperty());
        });
    }

    @FXML
    void save() {
        viewModel.save();
    }
}
