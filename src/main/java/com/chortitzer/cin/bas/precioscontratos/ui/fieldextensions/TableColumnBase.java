package com.chortitzer.cin.bas.precioscontratos.ui.fieldextensions;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableColumnBase<R, T> extends TableColumn<R, T> {

    public TableColumnBase(String sText, String valuePropertyName, Double prefWith) {
        this(sText, valuePropertyName, prefWith, false);
    }

    public TableColumnBase(String sText, String valuePropertyName, Double prefWith, Boolean rightAlign) {
        super(sText);
        super.setPrefWidth(prefWith);
        if (rightAlign == true) { super.setStyle("-fx-alignment: CENTER-RIGHT"); }
        super.setCellValueFactory(new PropertyValueFactory<>(valuePropertyName));
    }

}
