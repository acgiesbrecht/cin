package com.chortitzer.cin.bas.precioscontratos.ui.bascula.notasderemision;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.ui.fieldextensions.*;
import com.chortitzer.cin.bas.precioscontratos.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.JavaView;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.MaskTextField;
import jidefx.scene.control.field.NumberField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import tornadofx.control.DateTimePicker;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class TblBasNotasDeRemisionView implements FxmlView<TblBasNotasDeRemisionViewModel> {

    @FXML
    public StackPane mainStackPane;

    private DateTimePicker dtpFecha = new DateTimePicker();
    private FacturaNroField txtNro = new FacturaNroField();
    private MaskField txtNroTimbrado = new MaskField();
    private TextField thfRazonSocialEmisor = new TextField();
    private TextField txtRucEmisor = new TextField();
    private TextField thfRazonSocialTransportadora = new TextField();
    private TextField txtRucTransportadora = new TextField();
    private NumberField txtPesoNeto = new NumberField();
    private Button btnSave = new Button("Guardar");
    private Button btnDelete = new Button("Eliminar");
    private Button btnCancel = new Button("Cancelar");
    public ObjectProperty<Stage> owningStage = new SimpleObjectProperty<>();

    @InjectViewModel
    private TblBasNotasDeRemisionViewModel viewModel;

    public void initialize() {

        GridPane gridPane = new GridPane();

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        //columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(350,350, Double.MAX_VALUE);
        columnTwoConstrains.setFillWidth(false);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        gridPane.add(new Label("Nro. Remsión"), 0, 1);
        gridPane.add(new Label("Nro. Timbrado"), 0, 2);
        gridPane.add(new Label("Fecha"), 0, 3);
        gridPane.add(new Label("RUC"), 0, 4);
        gridPane.add(new Label("Razon Social"), 0, 5);
        gridPane.add(new Label("RUC - Transportadora"), 0, 6);
        gridPane.add(new Label("Razón Social - Transportadora"), 0, 7);
        gridPane.add(new Label("Peso Neto (Kg)"), 0, 8);

        txtNro.setMaxWidth(150);
        gridPane.add(txtNro, 1, 1);
        txtNroTimbrado.setMask("DDDDDDD");
        txtNroTimbrado.setPrefWidth(100);
        gridPane.add(txtNroTimbrado, 1, 2);
        dtpFecha.setFormat("dd/MM/yy");
        gridPane.add(dtpFecha, 1, 3);
        txtRucEmisor.setMaxWidth(100);
        gridPane.add(txtRucEmisor, 1, 4);
        txtRucEmisor.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                thfRazonSocialEmisor.setText(viewModel.getContribuyenteRazonSocial(txtRucEmisor.getText()));
            }
        });
        thfRazonSocialEmisor.setPrefWidth(350);
        gridPane.add(thfRazonSocialEmisor, 1, 5);
        txtRucTransportadora.setPrefWidth(100);
        txtRucTransportadora.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                thfRazonSocialTransportadora.setText(viewModel.getContribuyenteRazonSocial(txtRucTransportadora.getText()));
            }
        });
        gridPane.add(txtRucTransportadora, 1, 6);
        thfRazonSocialTransportadora.setPrefWidth(350);
        gridPane.add(thfRazonSocialTransportadora, 1, 7);
        txtPesoNeto.setAlignment(Pos.CENTER_RIGHT);
        txtPesoNeto.setAutoSelectAll(true);
        txtPesoNeto.setMaxWidth(100);
        DecimalFormat df = new DecimalFormat();
        df.setDecimalSeparatorAlwaysShown(false);
        df.setParseIntegerOnly(true);
        df.setGroupingUsed(true);
        df.setMaximumFractionDigits(0);
        txtPesoNeto.setDecimalFormat(df);
        gridPane.add(txtPesoNeto, 1, 8);
        btnSave.setDefaultButton(true);
        btnSave.setOnAction((event) -> {
            viewModel.commit();
            owningStage.get().close();
        });
        //btnDelete.setStyle("-fx-background-color: #ffe6e6;");
        btnCancel.setOnAction((event) -> {
            viewModel.delete();
            owningStage.get().close();
        });
        btnCancel.setCancelButton(true);
        btnCancel.setOnAction((event) -> {
            viewModel.cancel();
            owningStage.get().close();
        });
        HBox hbox = new HBox();
        hbox.setSpacing(5.0);
        hbox.getChildren().addAll(btnSave, btnDelete, btnCancel);
        gridPane.add(hbox, 0, 9);
        GridPane.setColumnSpan(hbox,2);
        AnchorPane editPane = new AnchorPane();
        gridPane.setHgap(10.0);
        gridPane.setVgap(10.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setTopAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);
        AnchorPane.setBottomAnchor(gridPane, 10.0);
        editPane.getChildren().add(gridPane);
        mainStackPane.getChildren().add(editPane);

        dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaEmisionProperty());
        txtNro.textProperty().bindBidirectional(viewModel.nroProperty());
        txtNroTimbrado.textProperty().bindBidirectional(viewModel.nroTimbradoProperty());
        thfRazonSocialEmisor.textProperty().bindBidirectional(viewModel.razonSocialEmisorProperty());
        txtRucEmisor.textProperty().bindBidirectional(viewModel.rucEmisorProperty());
        thfRazonSocialTransportadora.textProperty().bindBidirectional(viewModel.razonSocialTransportadoraProperty());
        txtRucTransportadora.textProperty().bindBidirectional(viewModel.rucTransportadoraProperty());
        txtPesoNeto.textProperty().bindBidirectional(viewModel.pesoNetoProperty(), new NumberStringConverter());

        AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(thfRazonSocialEmisor, SuggestionProvider.create(viewModel.getEmisoresList()));
        AutoCompletionBinding<String> binding2 = TextFields.bindAutoCompletion(thfRazonSocialTransportadora, SuggestionProvider.create(viewModel.getTransportadorasList()));

       /* btnAdd.setOnAction((event) -> {
            viewModel.add(new Tblpesadas());
            dtpFecha.setDateTimeValue(LocalDateTime.now());
            thfEmpresa.requestFocus();
            addAbstract();
        });*/
    }
}

