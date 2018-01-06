package com.chortitzer.cin.bas.precioscontratos.ui;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.table.TableFilter;

import javax.inject.Inject;

public class AbstractView<T> {

    @Inject
    Dialog dialog;

    @FXML
    private VBox mainBox;
    @FXML
    private AnchorPane editPane;
    @FXML
    private VBox loadingIndicator;
    @FXML
    private Pagination pagination;
    @FXML
    public TableView<T> itemsTable;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;

    private AbstractViewModel<T> viewModel;

    private BooleanBinding isSelectedOrNewBinding;
    private BooleanBinding isDifferentOrNewBinding;
    public BooleanProperty isNewProperty = new SimpleBooleanProperty();

    public IntegerProperty pageCountProperty = new SimpleIntegerProperty();

    public FilteredList<T> filteredData;
    public SortedList<T> sortedData;

    private final static int rowsPerPage = 1000;
    TableFilter<T> tableFilter;

    public void initializeAbstract() {
        loadingIndicator.visibleProperty().bind(viewModel.loadingInProgressProperty());
        mainBox.disableProperty().bind(viewModel.loadingInProgressProperty());
        itemsTable.visibleProperty().bind(viewModel.loadingInProgressProperty().not());

        filteredData = new FilteredList<>(viewModel.getItems(), p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(itemsTable.comparatorProperty());
        itemsTable.setItems(sortedData);

        //pagination.setPageCount((sortedData.size() / rowsPerPage + 1));
        sortedData.addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> c) {
                pagination.setPageCount((sortedData.size() / rowsPerPage + 1));
                pagination.setCurrentPageIndex(0);
            }
        });

        pagination.setPageFactory(this::createPage);

        //itemsTable.setItems(viewModel.itemsList);

        viewModel.selectedItemProperty().bind(itemsTable.getSelectionModel().selectedItemProperty());

        // When the selectedTableRowProperty changes in the viewModel we need to update the master
        viewModel.setOnSelect(vm -> itemsTable.getSelectionModel().select(vm));

        BooleanBinding isSelectedBinding = Bindings.size(itemsTable.getSelectionModel().getSelectedItems()).greaterThan(0);
        isSelectedOrNewBinding = isSelectedBinding.or(isNewProperty);
        isDifferentOrNewBinding = getViewModel().differentProperty().or(isNewProperty);

        itemsTable.disableProperty().bind(isDifferentOrNewBinding);
        btnSave.disableProperty().bind(isDifferentOrNewBinding.not());
        btnDelete.disableProperty().bind(isSelectedBinding.not().or(isNewProperty));

        editPane.disableProperty().bind(isSelectedOrNewBinding.not());
    }

    private Node createPage(int pageIndex) {

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, sortedData.size());
        itemsTable.setItems(FXCollections.observableArrayList(sortedData.subList(fromIndex, toIndex)));
        /*int toIndex = Math.min(fromIndex + rowsPerPage, viewModel.itemsList.size());
        itemsTable.setItems(FXCollections.observableArrayList(viewModel.itemsList.subList(fromIndex, toIndex)));*/

        return new BorderPane(itemsTable);
    }

    public void addAbstract() {
        isNewProperty.set(true);
        itemsTable.getSelectionModel().clearSelection();
    }

    @FXML
    void save() {
        getViewModel().save();
        isNewProperty.set(false);
    }

    @FXML
    void delete() {
        getViewModel().delete();
    }

    @FXML
    void reset() {
        getViewModel().reset();
        isNewProperty.set(false);
    }


    public AbstractViewModel<T> getViewModel() {
        return viewModel;
    }

    public void setViewModel(AbstractViewModel<T> viewModel) {
        this.viewModel = viewModel;
    }

}
