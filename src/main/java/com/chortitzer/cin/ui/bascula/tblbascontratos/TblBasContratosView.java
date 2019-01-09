package com.chortitzer.cin.ui.bascula.tblbascontratos;

import com.chortitzer.cin.model.bascula.TblBasContratos;
import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.*;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDateTime;

public class TblBasContratosView extends AbstractView<TblBasContratos> implements FxmlView<TblBasContratosViewModel> {

    private DateTimePickerField dtpFecha = new DateTimePickerField();
    private DateTimePickerField dtpFechaFin = new DateTimePickerField();
    private TypeAheadField<Tblempresa> thfEmpresa = new TypeAheadField<>();
    private TypeAheadField<Tblproductos> thfProducto = new TypeAheadField<>();
    private TextFieldInteger txtPrecioPorKg = new TextFieldInteger();
    private TextFieldInteger txtVolumenKg = new TextFieldInteger();

    @InjectViewModel
    private TblBasContratosViewModel viewModel;

    public void initialize() {

        //empresaColumn.setCellFactory(TypeAheadTableCell::new);

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnLocalDateTime<TblBasContratos> col1 = new TableColumnLocalDateTime<>("Fecha/Hora Inicio", "fecha", 170.0);
        TableColumnLocalDateTime<TblBasContratos> col2 = new TableColumnLocalDateTime<>("Fecha/Hora Fin", "fechaFinVigencia", 170.0);
        TableColumnBase<TblBasContratos, Tblempresa> col3 = new TableColumnBase<>("Empresa", "idEmpresa", 200.0);
        TableColumnBase<TblBasContratos, Tblproductos> col4 = new TableColumnBase<>("Producto", "idProducto", 200.0);
        TableColumnInteger<TblBasContratos> col5 = new TableColumnInteger<>("Precio (PYG/Kg)", "precioGsPorKg", 150.0);
        TableColumnInteger<TblBasContratos> col6 = new TableColumnInteger<>("Volumen (Kg)", "volumenKg", 150.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4, col5, col6);

        gridPane.add(new Label("Fecha/Hora Inicio Vigencia"), 1, 1);
        gridPane.add(new Label("Fecha/Hora Fin Vigencia"), 1, 2);
        gridPane.add(new Label("Empresa"), 1, 3);
        gridPane.add(new Label("Producto"), 1, 4);
        gridPane.add(new Label("Precio (PYG/Kg)"), 1, 5);
        gridPane.add(new Label("Volumen (Kg)"), 1, 6);

        gridPane.add(dtpFecha, 2, 1);
        gridPane.add(dtpFechaFin, 2, 2);
        gridPane.add(thfEmpresa, 2, 3);
        gridPane.add(thfProducto, 2, 4);
        txtPrecioPorKg.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(txtPrecioPorKg, 2, 5);
        gridPane.add(txtVolumenKg, 2, 6);

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
                } else if (contrato.getVolumenKg().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return ((Integer) contrato.getPrecioGsPorKg()).toString().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            dtpFechaFin.dateTimeValueProperty().bindBidirectional(viewModel.fechaFinProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
            txtVolumenKg.textProperty().bindBidirectional(viewModel.volumenProperty(), new NumberStringConverter());
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
