package com.chortitzer.cin.ui.fieldextensions;

import javafx.scene.control.TableCell;

public class TableColumnString<R> extends TableColumnBase<R, String> {

    public TableColumnString(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, false);
    }

}
