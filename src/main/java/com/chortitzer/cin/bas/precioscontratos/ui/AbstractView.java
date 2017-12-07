package com.chortitzer.cin.bas.precioscontratos.ui;

import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.table.TableFilter;

import javax.inject.Inject;

public class AbstractView<T> {

    @Inject
    Dialog dialog;

    @FXML
    private Pagination pagination;
    @FXML
    public TableView<T> itemsTable;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;

    private AbstractViewModel<T> viewModel;

    private final static int rowsPerPage = 1000;
    TableFilter<T> tableFilter;

    public void initializeAbstract(){


        itemsTable.setItems(viewModel.getItems());
        tableFilter = TableFilter.forTableView(itemsTable).apply();
        tableFilter.setSearchStrategy((input, target) -> {
            try {
                return target.toString().toLowerCase().contains(input.toString().toLowerCase());
            } catch (Exception e) {
                return false;
            }
        });

        viewModel.selectedItemProperty().bind(itemsTable.getSelectionModel().selectedItemProperty());

        // When the selectedTableRowProperty changes in the viewModel we need to update the master
        viewModel.setOnSelect(vm -> itemsTable.getSelectionModel().select(vm));

        itemsTable.disableProperty().bind(getViewModel().differentProperty());
        btnSave.disableProperty().bind(getViewModel().differentProperty().not());
        btnDelete.disableProperty().bind(Bindings.size(itemsTable.getSelectionModel().getSelectedItems()).lessThan(1));
    }

    @FXML
    void save() {
        getViewModel().save();
    }

    @FXML
    void delete() {
        getViewModel().delete();
    }

    @FXML
    void reset() { getViewModel().reset(); }


    public AbstractViewModel<T> getViewModel() {
        return viewModel;
    }

    public void setViewModel(AbstractViewModel<T> viewModel) {
        this.viewModel = viewModel;
    }

}
