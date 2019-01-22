package com.chortitzer.cin.ui.bascula.visor;

import com.chortitzer.cin.model.bascula.TblBasPersonas;
import com.chortitzer.cin.model.bascula.TblBasPesadas;
import com.chortitzer.cin.model.bascula.TblContribuyentes;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TextFieldInteger;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public class VisorView extends AbstractView<TblContribuyentes> implements FxmlView<VisorViewModel> {

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
    @FXML
    private ComboBox<TblBasPersonas> cboTest;
    @FXML
    private TypeAheadField<TblBasPersonas> thfProducto;

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

        cboTest.setItems(viewModel.getPersonasList());
        cboTest.setEditable(true);
        TextFields.bindAutoCompletion(cboTest.getEditor(), cboTest.getItems()).minWidthProperty().bind(cboTest.widthProperty());

        thfProducto.setItems(viewModel.getPersonasList());
    }

}
