package com.chortitzer.cin.ui.bascula.visor;

import com.chortitzer.cin.model.bascula.TblBasPesadas;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TextFieldInteger;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class VisorView extends AbstractView<TblBasPesadas> implements FxmlView<VisorViewModel> {

    /*@FXML
    private TypeAheadField<TblContribuyentes> thfPropietario;
    @FXML
    private TypeAheadField<TblContribuyentes> thfTransportista;
    @FXML
    private TextFieldChapa txtChapa;
    @FXML
    private TextFieldInteger txtBruto;
    @FXML
    private TextFieldInteger txtTara;
    @FXML
    private TextFieldInteger txtNeto;*/
    @FXML
    private TextFieldInteger txtVisor;
    @FXML
    private ChoiceBox<String> chbLectura;

    @InjectViewModel
    private VisorViewModel viewModel;

    public void initialize() {
        chbLectura.getItems().add("Automatico");
        chbLectura.getItems().add("Manual");
        chbLectura.valueProperty().addListener((ChangeListener) (o, oldVal, newVal) -> {
            if (newVal.equals("Automatico")) {
                txtVisor.setEditable(false);
                viewModel.openPort();
            } else {
                viewModel.closePort();
                txtVisor.setEditable(true);
            }
        });
        chbLectura.setValue("Automatico");
        txtVisor.textProperty().bindBidirectional(viewModel.visorProperty);
    }

}
