package com.chortitzer.cin.bas.precioscontratos.utils;

import javafx.scene.control.Alert;

import javax.inject.Singleton;

@Singleton
public class Dialog {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void showAlert(String msg){
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
