package com.chortitzer.cin.ui.fps.etiquetas;

import com.chortitzer.cin.App;
import com.chortitzer.cin.model.fps.EnvasesModel;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import tornadofx.control.DateTimePicker;

import java.util.Arrays;

public class FpsEtiquetasView implements FxmlView<FpsEtiquetasViewModel> {

    @FXML
    private TextField txtLote;
    @FXML
    private DateTimePicker dtpFechaElaboracion;
    @FXML
    private TextField txtCantidadTambores;
    @FXML
    private TypeAheadField<String> thfCliente;
    @FXML
    private TextField txtCodigoCliente;
    @FXML
    private TextField txtOrdenCompra;
    @FXML
    private TypeAheadField<EnvasesModel> thfEnvase;
    @FXML
    private TextField txtPesoNeto;
    @FXML
    private TextField txtPesoTara;

    @FXML
    private ImageView imageView;


    @InjectViewModel
    private FpsEtiquetasViewModel viewModel;

    public void initialize() {
        //loadingIndicator.visibleProperty().bind(viewModel.loadingInProgressProperty());
        //mainBox.disableProperty().bind(viewModel.loadingInProgressProperty());

        thfCliente.setItems(FXCollections.observableArrayList((Arrays.asList("INDUSTRIA",
                "AMIGO & ARDITI SA",
                "VIGON  INTERNATIONAL INC.",
                "FIRMINICH INC.",
                "ROBERTET INC.",
                "BIOLANDES",
                "JOH. VOEGELE KG"))));

        thfEnvase.setItems(FXCollections.observableArrayList(EnvasesModel.getList()));

        txtLote.textProperty().bindBidirectional(viewModel.loteProperty);
        dtpFechaElaboracion.dateTimeValueProperty().bindBidirectional(viewModel.fechaElaboracion);
        txtCantidadTambores.textProperty().bindBidirectional(viewModel.cantidadTamboresProperty);
        thfCliente.valueProperty().bindBidirectional(viewModel.clienteProperty);
        txtCodigoCliente.textProperty().bindBidirectional(viewModel.codigoClienteProperty);
        txtOrdenCompra.textProperty().bindBidirectional(viewModel.ordenCompraProperty);
        thfEnvase.valueProperty().bindBidirectional(viewModel.envaseProperty);
        txtPesoNeto.textProperty().bindBidirectional(viewModel.pesoNetoProperty, new NumberStringConverter());
        txtPesoTara.textProperty().bindBidirectional(viewModel.pesoTaraProperty, new NumberStringConverter());

    }

    @FXML
    void imprimirGrande() {
        viewModel.imprimirGrande();
    }

    @FXML
    void imprimirPequeno() {
        viewModel.imprimirPequeno();
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
