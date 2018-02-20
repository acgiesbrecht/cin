package com.chortitzer.cin.ui.bascula.tblbascontratos;

import com.chortitzer.cin.model.bascula.TblBasContratos;
import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnBase;
import com.chortitzer.cin.ui.fieldextensions.TableColumnInteger;
import com.chortitzer.cin.ui.fieldextensions.TableColumnLocalDateTime;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import jidefx.scene.control.field.NumberField;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblBasContratosView extends AbstractView<TblBasContratos> implements FxmlView<TblBasContratosViewModel> {

    private DateTimePicker dtpFecha = new DateTimePicker();
    private TypeAheadField<Tblempresa> thfEmpresa = new TypeAheadField<>();
    private TypeAheadField<Tblproductos> thfProducto = new TypeAheadField<>();
    private NumberField txtPrecioPorKg = new NumberField();

    @InjectViewModel
    private TblBasContratosViewModel viewModel;

    public void initialize() {

        //empresaColumn.setCellFactory(TypeAheadTableCell::new);

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnLocalDateTime<TblBasContratos> col1 = new TableColumnLocalDateTime<>("Fecha/Hora", "fecha", 170.0);
        TableColumnBase<TblBasContratos, Tblempresa> col2 = new TableColumnBase<>("Empresa", "idEmpresa", 200.0);
        TableColumnBase<TblBasContratos, Tblproductos> col3 = new TableColumnBase<>("Producto", "idProducto", 200.0);
        TableColumnInteger<TblBasContratos> col4 = new TableColumnInteger<>("Precio (PYG/Kg)", "precioGsPorKg", 150.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4);

        gridPane.add(new Label("Fecha/Hora Inicio Vigencia"), 1, 1);
        gridPane.add(new Label("Empresa"), 1, 2);
        gridPane.add(new Label("Producto"), 1, 3);
        gridPane.add(new Label("Precio (PYG/Kg)"), 1, 4);

        gridPane.add(dtpFecha, 2, 1);
        gridPane.add(thfEmpresa, 2, 2);
        gridPane.add(thfProducto, 2, 3);
        txtPrecioPorKg.setAlignment(Pos.CENTER_RIGHT);
        txtPrecioPorKg.setAutoSelectAll(true);
        gridPane.add(txtPrecioPorKg, 2, 4);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contrato -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (contrato.getIdProducto().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (contrato.getIdEmpresa().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (((Integer) contrato.getPrecioGsPorKg()).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
        thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
        thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
        txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());

        thfEmpresa.setItems(viewModel.getEmpresas());
        thfProducto.setItems(viewModel.getProductos());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblBasContratos());
            dtpFecha.setDateTimeValue(LocalDateTime.now());
            thfEmpresa.requestFocus();
        });
    }
}
