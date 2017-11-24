package com.chortitzer.cin.bas.precioscontratos.ui.menu;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import javax.inject.Inject;

public class MenuView implements FxmlView<MenuViewModel> {

	@FXML
	private MenuItem removeMenuItem;

	@InjectViewModel
	private MenuViewModel viewModel;

	@Inject
	private Stage primaryStage;

}
