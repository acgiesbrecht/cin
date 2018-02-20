package com.chortitzer.cin.ui.fieldextensions;

import com.sun.javafx.scene.KeyboardShortcutsHandler;
import com.sun.javafx.scene.traversal.Direction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.awt.*;

import static javafx.scene.input.KeyCode.TAB;

public class FacturaNroField extends MaskField {

    public FacturaNroField() {
        super();
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    try {
                        String temp = getText().replace("_", "");
                        String[] partes = temp.split("-");
                        if (partes.length > 1) {
                            temp = partes[0] + "-" + partes[1] + "-" + String.format("%07d", Integer.parseInt(partes[2]));
                        } else {
                            temp = getText();
                        }
                        setText(temp);
                    } catch (Exception ex) {
                        setText("");
                    }
                }
            }
        });
        this.setMask("DDD-DDD-DDDDDDD");
        final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);

        /*addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent KV) {
                try {
                    Robot eventRobot = new Robot();
                    switch (KV.getCode()) {
                        case ENTER: {
                            if (!(KV.getTarget() instanceof TextArea)) {
                                eventRobot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                                eventRobot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                                KV.consume();
                            }
                            break;
                        }
                        case TAB: {
                            if (!(KV.getTarget() instanceof TextArea)) {
                                KV.consume();
                            }
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });*/

    }

}
