package com.chortitzer.cin.ui.bascula.notasderemision;

import com.chortitzer.cin.ui.fieldextensions.DateTimePickerField;
import com.chortitzer.cin.ui.fieldextensions.TextFieldBase;
import com.chortitzer.cin.ui.fieldextensions.TextFieldFacturaNro;
import com.chortitzer.cin.ui.fieldextensions.TextFieldTimbrado;
import com.chortitzer.cin.utils.InformationDialog;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import jidefx.scene.control.field.NumberField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javax.inject.Inject;
import java.text.DecimalFormat;

public class TblBasNotasDeRemisionView implements FxmlView<TblBasNotasDeRemisionViewModel> {

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    @FXML
    public StackPane mainStackPane;

    @Inject
    InformationDialog informationDialog;

    private DateTimePickerField dtpFecha = new DateTimePickerField();
    private TextFieldFacturaNro txtNro = new TextFieldFacturaNro();
    private TextFieldTimbrado txtNroTimbrado = new TextFieldTimbrado();
    private TextFieldBase thfRazonSocialEmisor = new TextFieldBase();
    private TextFieldBase txtRucEmisor = new TextFieldBase();
    private TextFieldBase thfRazonSocialTransportadora = new TextFieldBase();
    private TextFieldBase txtRucTransportadora = new TextFieldBase();
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
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(500,500, Double.MAX_VALUE);
        columnTwoConstrains.setFillWidth(false);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        gridPane.add(new Label("Nro. Remsión"), 0, 1);
        gridPane.add(new Label("Nro. Timbrado"), 0, 2);
        gridPane.add(new Label("Fecha"), 0, 3);
        gridPane.add(new Label("Emisor"), 0, 4);
        gridPane.add(new Label("Transportadora"), 0, 5);
        gridPane.add(new Label("Peso Neto (Kg)"), 0, 6);

        txtNro.setMaxWidth(150);
        gridPane.add(txtNro, 1, 1);
        gridPane.add(txtNroTimbrado, 1, 2);
        dtpFecha.setFormat("dd/MM/yy");
        gridPane.add(dtpFecha, 1, 3);
        txtRucEmisor.setPrefWidth(120);
        txtRucEmisor.setPromptText("RUC");
        /*txtRucEmisor.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                thfRazonSocialEmisor.setText(getContribuyenteRazonSocial(txtRucEmisor.getText()));
            }
        });*/
        thfRazonSocialEmisor.setPrefWidth(350);
        thfRazonSocialEmisor.setPromptText("Razon Social");
        HBox hBoxEmisor = new HBox();
        hBoxEmisor.setSpacing(5.0);
        hBoxEmisor.getChildren().addAll(txtRucEmisor, thfRazonSocialEmisor);
        gridPane.add(hBoxEmisor, 1, 4);
        txtRucTransportadora.setPrefWidth(120);
        txtRucTransportadora.setPromptText("RUC");
        /*txtRucTransportadora.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                thfRazonSocialTransportadora.setText(viewModel.getContribuyenteRazonSocial(txtRucTransportadora.getText()));
            }
        });*/
        gridPane.add(txtRucTransportadora, 1, 6);
        thfRazonSocialTransportadora.setPrefWidth(350);
        thfRazonSocialTransportadora.setPromptText("Razon Social");
        HBox hBoxTransportadora = new HBox();
        hBoxTransportadora.setSpacing(5.0);
        hBoxTransportadora.getChildren().addAll(txtRucTransportadora, thfRazonSocialTransportadora);
        gridPane.add(hBoxTransportadora, 1, 5);
        txtPesoNeto.setAlignment(Pos.CENTER_RIGHT);
        txtPesoNeto.setAutoSelectAll(true);
        txtPesoNeto.setMaxWidth(100);
        DecimalFormat df = new DecimalFormat();
        df.setDecimalSeparatorAlwaysShown(false);
        df.setParseIntegerOnly(true);
        df.setGroupingUsed(true);
        df.setMaximumFractionDigits(0);
        txtPesoNeto.setDecimalFormat(df);
        gridPane.add(txtPesoNeto, 1, 6);
        btnSave.setDefaultButton(true);
        btnSave.setOnAction((event) -> {
            viewModel.commit();
            owningStage.get().close();
        });
        //btnDelete.setStyle("-fx-background-color: #ffe6e6;");
        btnDelete.setOnAction((event) -> {
            if(informationDialog.showConfirmation("Esta segura que desae eliminar la Nota de Remision?",false)){
                viewModel.delete();
                owningStage.get().close();
            }
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
        txtNroTimbrado.textProperty().bindBidirectional(viewModel.nroTimbradoProperty(), new NumberStringConverter());
        thfRazonSocialEmisor.textProperty().bindBidirectional(viewModel.razonSocialEmisorProperty());
        txtRucEmisor.textProperty().bindBidirectional(viewModel.rucEmisorProperty());
        thfRazonSocialTransportadora.textProperty().bindBidirectional(viewModel.razonSocialTransportadoraProperty());
        txtRucTransportadora.textProperty().bindBidirectional(viewModel.rucTransportadoraProperty());
        txtPesoNeto.textProperty().bindBidirectional(viewModel.pesoNetoProperty(), new NumberStringConverter());

        validationVisualizer.initVisualization(viewModel.rucEmisorValidation(), txtRucEmisor, true);
        validationVisualizer.initVisualization(viewModel.rucTransportadoraValidation(), txtRucTransportadora, true);

        btnSave.disableProperty().bind(viewModel.formValidation().validProperty().not());

        AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtRucEmisor, SuggestionProvider.create(viewModel.getEmisoresRucList()));
        AutoCompletionBinding<String> binding2 = TextFields.bindAutoCompletion(txtRucTransportadora, SuggestionProvider.create(viewModel.getTransportadorasRucList()));

       /* btnAdd.setOnAction((event) -> {
            viewModel.add(new Tblpesadas());
            dtpFecha.setDateTimeValue(LocalDateTime.now());
            thfEmpresa.requestFocus();
            addAbstract();
        });*/
    }
}

