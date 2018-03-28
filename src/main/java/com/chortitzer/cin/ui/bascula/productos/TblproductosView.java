package com.chortitzer.cin.ui.bascula.productos;

import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnInteger;
import com.chortitzer.cin.ui.fieldextensions.TableColumnString;
import com.chortitzer.cin.ui.fieldextensions.TextFieldBase;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class TblproductosView extends AbstractView<Tblproductos> implements FxmlView<TblproductosViewModel> {

    private TextFieldBase txtDescripcion = new TextFieldBase();
    private TextFieldBase txtMateriaPrima = new TextFieldBase();

    @InjectViewModel
    private TblproductosViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<Tblproductos> col1 = new TableColumnInteger<>("Nº", "id", 60.0);
        TableColumnString<Tblproductos> col2 = new TableColumnString<>("Descripcion", "descripcion", 350.0);
        TableColumnInteger<Tblproductos> col3 = new TableColumnInteger<>("Es Materia Prima", "materiaprima", 140.0);

        itemsTable.getColumns().addAll(col1, col2, col3);

        gridPane.add(new Label("Descripcion"), 1, 1);
        gridPane.add(new Label("Es Materia Prima"), 1, 2);

        gridPane.add(txtDescripcion, 2, 1);
        gridPane.add(txtMateriaPrima, 2, 2);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return producto.getDescripcion().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtDescripcion.textProperty().bindBidirectional(viewModel.descripcionProperty());
            txtMateriaPrima.textProperty().bindBidirectional(viewModel.materiaPrimaProperty(), new NumberStringConverter());
        });

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new Tblproductos());
            txtDescripcion.requestFocus();
        });
    }
}
