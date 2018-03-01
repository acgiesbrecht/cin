package com.chortitzer.cin.ui.fieldextensions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class TextFieldTimbrado extends MaskField {

    public TextFieldTimbrado(){
        super();
        setMask("DDDDDDD");
        setMaxWidth(80);
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    try {
                        setText(String.format("%07d", Integer.parseInt(getText().replace("_",""))));
                    } catch (Exception ex) {
                        setText("");
                    }
                }
            }
        });
    }

}
