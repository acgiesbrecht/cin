package com.chortitzer.cin.ui.bascula.facturas.flete;

import com.chortitzer.cin.model.bascula.TblBasFacturasFlete;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.*;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

public class TblBasFacturasFleteView extends AbstractView<TblBasFacturasFlete> implements FxmlView<TblBasFacturasFleteViewModel> {

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    private DatePickerField dtpFecha = new DatePickerField();
    private TextFieldFacturaNro txtNro = new TextFieldFacturaNro();
    private TextFieldTimbrado txtNroTimbrado = new TextFieldTimbrado();
    private TextFieldBase txtRazonSocial = new TextFieldBase();
    private TextFieldBase txtRuc = new TextFieldBase();
    private TextFieldLong txtCantidad= new TextFieldLong();
    private Button btnRemision = new Button("Nota de Remision");

    @InjectViewModel
    private TblBasFacturasFleteViewModel viewModel;

    public void initialize() {

        //empresaColumn.setCellFactory(TypeAheadTableCell::new);

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnLocalDate<TblBasFacturasFlete> col1 = new TableColumnLocalDate<>("Fecha", "fecha", 170.0);
        TableColumnString<TblBasFacturasFlete> col2 = new TableColumnString<>("Nro. Factura", "nro", 150.0);
        TableColumnString<TblBasFacturasFlete> col3 = new TableColumnString<>("Nro Timbrado", "nroTimbrado", 150.0);
        TableColumnString<TblBasFacturasFlete> col4 = new TableColumnString<>("Razon Social", "razonSocial", 250.0);
        TableColumnString<TblBasFacturasFlete> col5 = new TableColumnString<>("RUC", "ruc", 150.0);
        TableColumnLong<TblBasFacturasFlete> col6 = new TableColumnLong<>("Cantidad Facturada", "cantidad", 150.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4, col5, col6);
        itemsTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                viewModel.showTblBasNotasDeRemisionView();
            }
        });

        gridPane.add(new Label("Nro. Factura"), 1, 1);
        gridPane.add(new Label("Nro. Timbrado"), 1, 2);
        gridPane.add(new Label("Fecha"), 1, 3);
        gridPane.add(new Label("Proveedor"), 1, 4);
        gridPane.add(new Label("Cantidad Facturada"), 1, 5);

        gridPane.add(txtNro, 2, 1);
        gridPane.add(txtNroTimbrado, 2, 2);
        gridPane.add(dtpFecha, 2, 3);
        txtRuc.setPrefWidth(120);
        txtRuc.setPromptText("RUC");
        txtRuc.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                txtRazonSocial.setText(viewModel.getContribuyenteRazonSocial(txtRuc.getText()));
            }
        });
        txtRazonSocial.setPrefWidth(350);
        txtRazonSocial.setPromptText("Razon Social");
        HBox hBoxEmisor = new HBox();
        hBoxEmisor.setSpacing(5.0);
        hBoxEmisor.getChildren().addAll(txtRuc, txtRazonSocial);
        gridPane.add(hBoxEmisor, 2, 4);
        gridPane.add(txtCantidad, 2, 5);
        btnRemision.setDisable(true);
        gridPane.add(btnRemision, 2, 6);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(facturas -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (facturas.getRazonSocial().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (facturas.getRuc().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (facturas.getNroTimbrado().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return facturas.getNro().toString().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            txtNro.textProperty().bindBidirectional(viewModel.nroProperty());
            txtNroTimbrado.textProperty().bindBidirectional(viewModel.nroTimbradoProperty(), new NumberStringConverter());
            txtRazonSocial.textProperty().bindBidirectional(viewModel.razonSocialProperty());
            txtRuc.textProperty().bindBidirectional(viewModel.rucProperty());
            txtCantidad.textProperty().bindBidirectional(viewModel.cantidadProperty(), new NumberStringConverter());
            btnRemision.disableProperty().bind(viewModel.idProperty().greaterThan(-1).not());
        });

        dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
        txtNro.textProperty().bindBidirectional(viewModel.nroProperty());
        txtNroTimbrado.textProperty().bindBidirectional(viewModel.nroTimbradoProperty(), new NumberStringConverter());
        txtRazonSocial.textProperty().bindBidirectional(viewModel.razonSocialProperty());
        txtRuc.textProperty().bindBidirectional(viewModel.rucProperty());
        txtCantidad.textProperty().bindBidirectional(viewModel.cantidadProperty(), new NumberStringConverter());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblBasFacturasFlete());
            itemsTable.refresh();
            txtNro.requestFocus();
        });

        btnRemision.setOnAction((event) -> {
            viewModel.showTblBasNotasDeRemisionView();
        });



    }
}
