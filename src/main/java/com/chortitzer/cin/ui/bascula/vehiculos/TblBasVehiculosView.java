package com.chortitzer.cin.ui.bascula.vehiculos;

import com.chortitzer.cin.model.bascula.TblBasVehiculos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnString;
import com.chortitzer.cin.ui.fieldextensions.TextFieldBase;
import com.chortitzer.cin.ui.fieldextensions.TextFieldChapa;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;

public class TblBasVehiculosView extends AbstractView<TblBasVehiculos> implements FxmlView<TblBasVehiculosViewModel> {

    private TextFieldChapa txtChapa = new TextFieldChapa();
    private TextFieldBase txtRuc = new TextFieldBase();
    private Label lblRazonSocial = new Label();

    @InjectViewModel
    private TblBasVehiculosViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnString<TblBasVehiculos> col1 = new TableColumnString<>("Chapa Nº", "chapa", 100.0);
        TableColumnString<TblBasVehiculos> col2 = new TableColumnString<>("RUC Transportadora", "ruc", 200.0);

        itemsTable.getColumns().addAll(col1, col2);

        gridPane.add(new Label("Chapa Nª"), 1, 1);
        gridPane.add(new Label("RUC Transportadora"), 1, 2);

        gridPane.add(txtChapa, 2, 1);
        gridPane.add(txtRuc, 2, 2);

        gridPane.add(lblRazonSocial, 3, 2);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vehiculo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (vehiculo.getChapa().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return vehiculo.getRuc().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtChapa.textProperty().bindBidirectional(viewModel.chapaProperty());
            txtRuc.textProperty().bindBidirectional(viewModel.rucProperty());
            lblRazonSocial.textProperty().bindBidirectional(viewModel.razonSocialProperty);
        });

        /**
         * Bind on load while no item is selected.
         */
        txtChapa.textProperty().bindBidirectional(viewModel.chapaProperty());
        txtRuc.textProperty().bindBidirectional(viewModel.rucProperty());
        lblRazonSocial.textProperty().bindBidirectional(viewModel.razonSocialProperty);

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblBasVehiculos());
            txtChapa.requestFocus();
        });
    }
}
