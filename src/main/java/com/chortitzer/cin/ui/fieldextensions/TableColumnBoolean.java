package com.chortitzer.cin.ui.fieldextensions;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableColumnBoolean<R> extends TableColumnBase<R, Boolean> {

    public TableColumnBoolean(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, false);
        //setCellValueFactory( new PropertyValueFactory<R,Boolean>() );
        setCellFactory(new Callback<TableColumn<R, Boolean>, TableCell<R, Boolean>>() {
            @Override
            public TableCell<R, Boolean> call(TableColumn<R, Boolean> param) {
                return new CheckBoxTableCell<R, Boolean>() {
                    {
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        if (!empty) {
                            TableRow row = getTableRow();

                            if (row != null) {
                                int rowNo = row.getIndex();
                                TableView.TableViewSelectionModel sm = getTableView().getSelectionModel();

                                if (item) sm.select(rowNo);
                                else sm.clearSelection(rowNo);
                            }
                        }
                        super.updateItem(item, empty);
                    }
                };
            }
        });
        setEditable(true);
        setMaxWidth(50);
        setMinWidth(50);
    }

}
