package com.chortitzer.cin.ui.main;

import com.sun.jna.platform.win32.Secur32;
import com.sun.jna.platform.win32.Secur32Util;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MainView implements FxmlView<MainViewModel> {

    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private ImageView welcomeImage;

    @FXML
    private Text txtUser;

    public void initialize() {
        welcomeImage.setImage(new Image(this.getClass().getResourceAsStream("/images/membrete-cin-header.png")));
        txtUser.setText(Secur32Util.getUserNameEx(Secur32.EXTENDED_NAME_FORMAT.NameDisplay));
    }
}
