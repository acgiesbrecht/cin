package com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbasprecios;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasPrecios;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.AbstractView;
import com.chortitzer.cin.bas.precioscontratos.ui.fieldextensions.TableColumnBase;
import com.chortitzer.cin.bas.precioscontratos.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import jidefx.scene.control.field.FormattedTextField;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblBasPreciosView extends AbstractView<TblBasPrecios> implements FxmlView<TblBasPreciosViewModel> {


    private DateTimePicker dtpFecha = new DateTimePicker();
    private TypeAheadField<Tblproductos> thfProducto = new TypeAheadField<>();
    private FormattedTextField txtPrecioPorKg = new FormattedTextField();

    @InjectViewModel
    private TblBasPreciosViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnBase<TblBasPrecios, LocalDateTime> col1 = new TableColumnBase<>("Fecha/Hora", "fechahoraVigencia", 170.0, true);
        TableColumnBase<TblBasPrecios, Tblproductos> col2 = new TableColumnBase<>("Producto", "idProducto", 200.0);
        TableColumnBase<TblBasPrecios, Integer> col3 = new TableColumnBase<>("Precio (PYG/Kg)", "valorGsPorKg", 150.0,true);

        itemsTable.getColumns().addAll(col1, col2, col3);

        gridPane.add(new Label("Fecha/Hora Inicio Vigencia"), 1, 1);
        gridPane.add(new Label("Producto"), 1, 2);
        gridPane.add(new Label("Precio (PYG/Kg)"), 1, 3);

        gridPane.add(dtpFecha, 2, 1);
        gridPane.add(thfProducto, 2, 2);
        txtPrecioPorKg.setAlignment(Pos.CENTER_RIGHT);
        txtPrecioPorKg.setAutoSelectAll(true);
        gridPane.add(txtPrecioPorKg, 2, 3);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(precio -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (precio.getIdProducto().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        thfProducto.setItems(viewModel.getProductos());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblBasPrecios());
            dtpFecha.setDateTimeValue(LocalDateTime.now());
            thfProducto.requestFocus();
        });
    }
}
