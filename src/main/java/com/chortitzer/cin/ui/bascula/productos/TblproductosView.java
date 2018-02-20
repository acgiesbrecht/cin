package com.chortitzer.cin.ui.bascula.productos;

import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnBase;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class TblproductosView extends AbstractView<Tblproductos> implements FxmlView<TblproductosViewModel> {

    private TextField txtDescripcion = new TextField();
    private TextField txtMateriaPrima = new TextField();

    @InjectViewModel
    private TblproductosViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        TableColumnBase<Tblproductos, Integer> col1 = new TableColumnBase<>("Nº", "id", 60.0, true);
        TableColumnBase<Tblproductos, String> col2 = new TableColumnBase<>("Descripcion", "descripcion", 350.0);
        TableColumnBase<Tblproductos, Integer> col3 = new TableColumnBase<>("Es Materia Prima", "materiaprima", 140.0);

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
                if (producto.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
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
