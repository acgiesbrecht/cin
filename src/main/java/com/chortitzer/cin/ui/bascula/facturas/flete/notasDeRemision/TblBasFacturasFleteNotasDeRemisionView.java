package com.chortitzer.cin.ui.bascula.facturas.flete.notasDeRemision;

import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.ui.fieldextensions.*;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TblBasFacturasFleteNotasDeRemisionView implements FxmlView<TblBasFacturasFleteNotasDeRemisionViewModel> {

    private TableView<TblBasNotasDeRemision> itemsTable = new TableView();

    private Button btnSave = new Button("Guardar");
    private Button btnCancel = new Button("Cancelar");

    @FXML
    public StackPane mainStackPane;

    @InjectViewModel
    private TblBasFacturasFleteNotasDeRemisionViewModel viewModel;

    public ObjectProperty<Stage> owningStage = new SimpleObjectProperty<>();

    public void initialize() {

        TableColumnString<TblBasNotasDeRemision> col1 = new TableColumnString<>("Nro. Remision", "nro", 150.0);
        TableColumnString<TblBasNotasDeRemision> col2 = new TableColumnString<>("Nro Timbrado", "nroTimbrado", 150.0);
        TableColumnLocalDate<TblBasNotasDeRemision> col3 = new TableColumnLocalDate<>("Fecha", "fecha", 170.0);
        TableColumnString<TblBasNotasDeRemision> col4 = new TableColumnString<>("Razon Social Emisor", "razonSocialEmisor", 250.0);
        TableColumnString<TblBasNotasDeRemision> col5 = new TableColumnString<>("RUC Emisor", "rucEmisor", 150.0);
        TableColumnString<TblBasNotasDeRemision> col6 = new TableColumnString<>("Razon Social Transportadora", "razonSocialTransportadora", 250.0);
        TableColumnString<TblBasNotasDeRemision> col7 = new TableColumnString<>("RUC Transportadora", "rucTransportadora", 150.0);
        TableColumnLong<TblBasNotasDeRemision> col8 = new TableColumnLong<>("Peso Neto", "cantidad", 150.0);
        TableColumnBoolean<TblBasNotasDeRemision> col9 = new TableColumnBoolean<>("Aplicar a Factura", "selected", 150.0);

        itemsTable.getColumns().addAll(col9, col1, col2, col3, col4, col5, col6, col7, col8);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        //columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(500, 500, Double.MAX_VALUE);
        columnTwoConstrains.setFillWidth(false);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        AnchorPane.setLeftAnchor(itemsTable, 10.0);
        AnchorPane.setTopAnchor(itemsTable, 10.0);
        AnchorPane.setRightAnchor(itemsTable, 10.0);
        AnchorPane.setBottomAnchor(itemsTable, 10.0);

        btnSave.setDefaultButton(true);
        btnSave.setOnAction((event) -> {
            viewModel.commit();
            owningStage.get().close();
        });
        btnCancel.setCancelButton(true);
        btnCancel.setOnAction((event) -> {
            owningStage.get().close();
        });
        AnchorPane buttonBarPane = new AnchorPane();
        ButtonBar buttonBar = new ButtonBar();
        AnchorPane.setBottomAnchor(buttonBar, 10.0);
        AnchorPane.setTopAnchor(buttonBar, 10.0);
        AnchorPane.setLeftAnchor(buttonBar, 10.0);
        AnchorPane.setRightAnchor(buttonBar, 10.0);
        buttonBar.getButtons().addAll(btnSave, btnCancel);
        buttonBarPane.getChildren().add(buttonBar);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        AnchorPane masterAnchorPane = new AnchorPane();
        masterAnchorPane.setBottomAnchor(vBox, 10.0);
        masterAnchorPane.setLeftAnchor(vBox, 10.0);
        masterAnchorPane.setTopAnchor(vBox, 10.0);
        masterAnchorPane.setRightAnchor(vBox, 10.0);
        vBox.getChildren().addAll(itemsTable,buttonBarPane);
        masterAnchorPane.getChildren().add(vBox);
        mainStackPane.getChildren().add(masterAnchorPane);


        itemsTable.setEditable(true);
        itemsTable.setItems(viewModel.itemsList);

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            System.out.println(n.getSelected());
        });

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
