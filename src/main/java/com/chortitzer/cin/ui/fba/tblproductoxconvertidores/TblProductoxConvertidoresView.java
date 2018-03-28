package com.chortitzer.cin.ui.fba.tblproductoxconvertidores;

import com.chortitzer.cin.model.fba.Productox;
import com.chortitzer.cin.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.*;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class TblProductoxConvertidoresView extends AbstractView<TblProductoxConvertidores> implements FxmlView<TblProductoxConvertidoresViewModel> {

    private TypeAheadField<Productox> thfProducto = new TypeAheadField<>();
    private TextFieldInteger txtKgPorBolsa = new TextFieldInteger();
    private TextFieldBase txtDescripcionEnEtiqueta = new TextFieldBase();

    @InjectViewModel
    private TblProductoxConvertidoresViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        TableColumnBase<TblProductoxConvertidores, Productox> col1 = new TableColumnBase<>("Producto", "idProductox", 350.0);
        TableColumnInteger<TblProductoxConvertidores> col2 = new TableColumnInteger<>("Kg por Bolsa", "kgPorBolsa", 100.0);
        TableColumnString<TblProductoxConvertidores> col3 = new TableColumnString<>("Descripcion en Etiqueta", "descripcionEnEtiqueta", 350.0);

        itemsTable.getColumns().addAll(col1, col2, col3);

        gridPane.add(new Label("Producto"), 1, 1);
        gridPane.add(new Label("Kg por Bolsa"), 1, 2);
        gridPane.add(new Label("Descripcion en Etiqueta"), 1, 3);

        gridPane.add(thfProducto, 2, 1);
        gridPane.add(txtKgPorBolsa, 2, 2);
        gridPane.add(txtDescripcionEnEtiqueta, 2, 3);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(convertidor -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (convertidor.getIdProductox().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(convertidor.getKgPorBolsa()).contains(lowerCaseFilter)) {
                    return true;
                } else return convertidor.getDescripcionEnEtiqueta().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtKgPorBolsa.textProperty().bindBidirectional(viewModel.kgPorBolsaProperty(), new NumberStringConverter());
            txtDescripcionEnEtiqueta.textProperty().bindBidirectional(viewModel.descripcionEnEtiquetaProperty());
        });

        thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
        txtKgPorBolsa.textProperty().bindBidirectional(viewModel.kgPorBolsaProperty(), new NumberStringConverter());
        txtDescripcionEnEtiqueta.textProperty().bindBidirectional(viewModel.descripcionEnEtiquetaProperty());

        thfProducto.setItems(viewModel.getProductos());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblProductoxConvertidores());
            thfProducto.requestFocus();
        });
    }

}
