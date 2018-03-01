package com.chortitzer.cin.ui.main;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainView implements FxmlView<MainViewModel> {

	@InjectViewModel
	private MainViewModel viewModel;

	@FXML
	private ImageView welcomeImage;

	public void initialize() {
		welcomeImage.setImage(new Image(this.getClass().getResourceAsStream("/images/membrete-cin-header.png")));
	}
}
