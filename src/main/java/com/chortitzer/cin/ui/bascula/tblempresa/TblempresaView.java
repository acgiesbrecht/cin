package com.chortitzer.cin.ui.bascula.tblempresa;

import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnInteger;
import com.chortitzer.cin.ui.fieldextensions.TableColumnString;
import com.chortitzer.cin.ui.fieldextensions.TextFieldBase;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;

public class TblempresaView extends AbstractView<Tblempresa> implements FxmlView<TblempresaViewModel> {

    private TextFieldBase txtNombre = new TextFieldBase();
    private TextFieldBase txtRUC = new TextFieldBase();
    private TextFieldBase txtCtaCte = new TextFieldBase();

    @InjectViewModel
    private TblempresaViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<Tblempresa> col1 = new TableColumnInteger<>("Nº", "id", 60.0);
        TableColumnString<Tblempresa> col2 = new TableColumnString<>("Nombre", "nombre", 350.0);
        TableColumnString<Tblempresa> col3 = new TableColumnString<>("RUC", "ruc", 140.0);
        TableColumnString<Tblempresa> col4 = new TableColumnString<>("Cta. Cte.", "ctacte", 140.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4);

        gridPane.add(new Label("Nombre"), 1, 1);
        gridPane.add(new Label("RUC"), 1, 2);
        gridPane.add(new Label("Cta. Cte."), 1, 3);

        gridPane.add(txtNombre, 2, 1);
        gridPane.add(txtRUC, 2, 2);
        gridPane.add(txtCtaCte, 2, 3);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(empresa -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (empresa.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (empresa.getRuc().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return empresa.getCuentacorriente().toLowerCase().contains(lowerCaseFilter);
            });
        });


        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtNombre.textProperty().bindBidirectional(viewModel.nombreProperty());
            txtRUC.textProperty().bindBidirectional(viewModel.rucProperty());
            txtCtaCte.textProperty().bindBidirectional(viewModel.ctacteProperty());
        });

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new Tblempresa());
            txtNombre.requestFocus();
        });

    }
}
