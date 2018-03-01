package com.chortitzer.cin.ui.fieldextensions;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableColumnBoolean<R> extends TableColumnBase<R, Boolean> {

    public TableColumnBoolean(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, false);
        //setCellFactory(tc -> new CheckBoxTableCell<>());
        setCellFactory(CheckBoxTableCell.forTableColumn(this));
        setEditable(true);
        setMaxWidth(50);
        setMinWidth(50);
    }

}
