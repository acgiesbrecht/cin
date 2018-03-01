package com.chortitzer.cin.ui.bascula.contribuyentes;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TblContribuyentesUpdateView implements FxmlView<TblContribuyentesUpdateViewModel> {

    @FXML
    public StackPane mainStackPane;

    private VBox vBox = new VBox();
    private ProgressBar progressBar = new ProgressBar();
    private Label label = new Label();
    private Button btnClose = new Button("Cerrar");

    public ObjectProperty<Stage> owningStage = new SimpleObjectProperty<>();

    @InjectViewModel
    private TblContribuyentesUpdateViewModel viewModel;

    public void initialize() {

        StackPane.setMargin(vBox, new Insets(10,10,10,10));
        vBox.setPrefWidth(500);
        vBox.setPrefHeight(300);
        vBox.setSpacing(10.0);
        vBox.setAlignment(Pos.CENTER);
        progressBar.setPrefWidth(100);
        vBox.getChildren().addAll(progressBar,label,btnClose);

        label.textProperty().bind(viewModel.messageProperty);
        btnClose.disableProperty().bind(viewModel.runningProperty);
        btnClose.setOnAction((event) -> {
            owningStage.get().close();
        });

        mainStackPane.getChildren().add(vBox);

        viewModel.update();
    }
}

