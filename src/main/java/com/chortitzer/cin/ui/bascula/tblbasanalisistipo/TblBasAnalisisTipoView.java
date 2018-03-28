package com.chortitzer.cin.ui.bascula.tblbasanalisistipo;

import com.chortitzer.cin.model.bascula.TblBasAnalisisTipo;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnInteger;
import com.chortitzer.cin.ui.fieldextensions.TableColumnString;
import com.chortitzer.cin.ui.fieldextensions.TextFieldBase;
import com.chortitzer.cin.ui.fieldextensions.TextFieldLong;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class TblBasAnalisisTipoView extends AbstractView<TblBasAnalisisTipo> implements FxmlView<TblBasAnalisisTipoViewModel> {


    private TextFieldBase txtDescripcion = new TextFieldBase();
    private TextFieldBase txtUnidadeDeMedida = new TextFieldBase();
    private TextFieldLong txtCantidadDecimales = new TextFieldLong();

    @InjectViewModel
    private TblBasAnalisisTipoViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<TblBasAnalisisTipo> col1 = new TableColumnInteger<>("ID", "id", 100.0);
        TableColumnString<TblBasAnalisisTipo> col2 = new TableColumnString<>("Descripcion", "descripcion", 300.0);
        TableColumnString<TblBasAnalisisTipo> col3 = new TableColumnString<>("Unidad de Medida", "unidadDeMedida", 150.0);
        TableColumnInteger<TblBasAnalisisTipo> col4 = new TableColumnInteger<>("Cantidad de Decimales", "cantidadDecimnales", 100.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4);

        gridPane.add(new Label("Descripcion"), 1, 1);
        gridPane.add(new Label("Unidad de Medida"), 1, 2);
        gridPane.add(new Label("Canitdad de Decimales"), 1, 3);

        gridPane.add(txtDescripcion, 2, 1);
        gridPane.add(txtUnidadeDeMedida, 2, 2);
        gridPane.add(txtCantidadDecimales, 2, 3);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tipo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return tipo.getDescripcion().toString().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtDescripcion.textProperty().bindBidirectional(viewModel.descripcionProperty());
            txtUnidadeDeMedida.textProperty().bindBidirectional(viewModel.unidadDeMedidaProperty());
            txtCantidadDecimales.textProperty().bindBidirectional(viewModel.cantidadDecimalesProperty(), new NumberStringConverter());
        });

        /**
         * Bind on load while no item is selected.
         */
        txtDescripcion.textProperty().bindBidirectional(viewModel.descripcionProperty());
        txtUnidadeDeMedida.textProperty().bindBidirectional(viewModel.unidadDeMedidaProperty());
        txtCantidadDecimales.textProperty().bindBidirectional(viewModel.cantidadDecimalesProperty(), new NumberStringConverter());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblBasAnalisisTipo());
            txtDescripcion.requestFocus();
        });
    }
}
