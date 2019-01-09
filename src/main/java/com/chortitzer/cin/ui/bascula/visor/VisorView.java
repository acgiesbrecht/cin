package com.chortitzer.cin.ui.bascula.visor;

import com.chortitzer.cin.model.bascula.TblBasPesadas;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TextFieldInteger;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

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
    private ToggleButton tbAutomatic;

    @InjectViewModel
    private VisorViewModel viewModel;

    public void initialize() {
        tbAutomatic.selectedProperty().bindBidirectional(viewModel.automaticProperty);
        txtVisor.textProperty().bindBidirectional(viewModel.visorProperty);
    }


}
