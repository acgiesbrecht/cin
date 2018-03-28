package com.chortitzer.cin.ui.fieldextensions;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class TextFieldChapa extends MaskField {

    public TextFieldChapa() {
        super();
        setMask("AAADDD");
        setMaxWidth(100);
        final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
    }
}
