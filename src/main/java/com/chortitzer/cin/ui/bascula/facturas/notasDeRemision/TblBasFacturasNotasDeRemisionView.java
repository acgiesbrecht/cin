package com.chortitzer.cin.ui.bascula.facturas.notasDeRemision;

import com.chortitzer.cin.model.bascula.TblBasFacturas;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.*;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.converter.NumberStringConverter;

public class TblBasFacturasNotasDeRemisionView  implements FxmlView<TblBasFacturasNotasDeRemisionViewModel> {

    private TableView<TblBasNotasDeRemision> itemsTable = new TableView();

    private Button btnSave = new Button("Guardar");
    private Button btnCancel = new Button("Cancelar");

    @FXML
    public StackPane mainStackPane;

    @InjectViewModel
    private TblBasFacturasNotasDeRemisionViewModel viewModel;

    public void initialize() {

        TableColumnString<TblBasNotasDeRemision> col1 = new TableColumnString<>("Nro. Remision", "nro", 150.0);
        TableColumnString<TblBasNotasDeRemision> col2 = new TableColumnString<>("Nro Timbrado", "nroTimbrado", 150.0);
        TableColumnLocalDateTime<TblBasNotasDeRemision> col3 = new TableColumnLocalDateTime<>("Fecha", "fecha", 170.0);
        TableColumnString<TblBasNotasDeRemision> col4 = new TableColumnString<>("Razon Social Emisor", "razonSocialEmisor", 250.0);
        TableColumnString<TblBasNotasDeRemision> col5 = new TableColumnString<>("RUC Emisor", "rucEmisor", 150.0);
        TableColumnString<TblBasNotasDeRemision> col6 = new TableColumnString<>("Razon Social Transportadora", "razonSocialTransportadora", 250.0);
        TableColumnString<TblBasNotasDeRemision> col7 = new TableColumnString<>("RUC Transportadora", "rucTransportadora", 150.0);
        TableColumnLong<TblBasNotasDeRemision> col8 = new TableColumnLong<>("Peso Neto", "cantidad", 150.0);
        TableColumnBoolean<TblBasNotasDeRemision> col9 = new TableColumnBoolean<>("Aplicar a Factura", "isSelected", 150.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);

        AnchorPane editPane = new AnchorPane();
        AnchorPane.setLeftAnchor(itemsTable, 10.0);
        AnchorPane.setTopAnchor(itemsTable, 10.0);
        AnchorPane.setRightAnchor(itemsTable, 10.0);
        AnchorPane.setBottomAnchor(itemsTable, 10.0);
        mainStackPane.getChildren().add(itemsTable);


        //gridPane.add(btnRemision, 2, 6);

        /*txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
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
                } else if (facturas.getNro().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });*/

    }
}
