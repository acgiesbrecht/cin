package com.chortitzer.cin.ui.fba.etiquetas;

import com.chortitzer.cin.App;
import com.chortitzer.cin.model.fba.Formulas;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jidefx.scene.control.field.FormattedTextField;
import tornadofx.control.DateTimePicker;

import java.util.Arrays;

public class FbaEtiquetasView implements FxmlView<FbaEtiquetasViewModel> {

    @FXML
    private TypeAheadField<Formulas> thfFormula;
    @FXML
    private DateTimePicker dtpFechaElaboracion;
    @FXML
    private DateTimePicker dtpFechaVencimiento;
    @FXML
    private TypeAheadField<String> thfLinea;
    @FXML
    private TypeAheadField<String> thfTurno;
    @FXML
    private TypeAheadField<String> thfPeso;
    @FXML
    private TextField txtLote;
    @FXML
    private TextField txtCantidad;

    @FXML
    private TextArea txtIndicaciones;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private TextArea txtNiveles;
    @FXML
    private FormattedTextField txtSenacsa;
    @FXML
    private ImageView imageView;

    @FXML
    private VBox mainBox;
    @FXML
    private VBox loadingIndicator;


    @InjectViewModel
    private FbaEtiquetasViewModel viewModel;

    public void initialize() {
        loadingIndicator.visibleProperty().bind(viewModel.loadingInProgressProperty());
        mainBox.disableProperty().bind(viewModel.loadingInProgressProperty());

        thfFormula.setItems(viewModel.getItems());
        thfFormula.valueProperty().bindBidirectional(viewModel.selectedItem);

        thfLinea.setItems(FXCollections.observableArrayList((Arrays.asList("1", "2"))));
        thfTurno.setItems(FXCollections.observableArrayList((Arrays.asList("1", "2"))));
        thfPeso.setItems(FXCollections.observableArrayList((Arrays.asList("5", "25", "30", "40", "50", "Granel"))));

        //thfFormula.valueProperty().addListener((observableValue, o, n) -> {
        dtpFechaElaboracion.dateTimeValueProperty().bindBidirectional(viewModel.fechaElaboracion);
        dtpFechaVencimiento.dateTimeValueProperty().bindBidirectional(viewModel.fechaVencimiento);
        txtLote.textProperty().bindBidirectional(viewModel.loteProperty);
        thfLinea.valueProperty().bindBidirectional(viewModel.lineaProperty);
        thfTurno.valueProperty().bindBidirectional(viewModel.turnoProperty);
        thfPeso.valueProperty().bindBidirectional(viewModel.pesoProperty);
        txtCantidad.textProperty().bindBidirectional(viewModel.cantidadProperty);

        txtIndicaciones.textProperty().bindBidirectional(viewModel.indicacionesProperty);
        txtIngredientes.textProperty().bindBidirectional(viewModel.ingredientesProperty);
        txtNiveles.textProperty().bindBidirectional(viewModel.nivelesProperty);
        txtSenacsa.textProperty().bindBidirectional(viewModel.senacsaRegProperty);

        //imageView.imageProperty().bind(viewModel.image);

        //});
    }

    @FXML
    void print() {
        viewModel.print();
    }

    @FXML
    void reset() {
        viewModel.reset();
    }

    @FXML
    void preview() {
        viewModel.preview();

        Stage dialog = new Stage();
        ImageView imgW = new ImageView();
        imgW.setPreserveRatio(true);
        //imgW.setRotate(90);

        imgW.setFitHeight(800);
        //imgW.setFitWidth(800);
        imgW.setImage(viewModel.image.get());
        VBox ap = new VBox();
        ap.getChildren().add(imgW);
        Scene scene = new Scene(ap);
        dialog.setScene(scene);
        dialog.initOwner(App.mainStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }
}
