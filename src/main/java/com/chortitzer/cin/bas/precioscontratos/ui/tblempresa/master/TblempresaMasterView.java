package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.master;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.controlsfx.control.table.TableFilter;

/**
 * Main View which creates the necessary subviews, and manages them. Does not
 * need a concrete Viewmodel, so it is typed with the inferface. Have a careful
 * look on the FXML file to see, how to include different views into a
 * MasterView.
 */
public class TblempresaMasterView implements FxmlView<TblempresaMasterViewModel> {

	@FXML
	private TableView<Tblempresa> empresaTable;

	@InjectViewModel
	private TblempresaMasterViewModel viewModel;

	public void initialize() {
        //empresaTable.setItems(viewModel.getEmpresas());
        TableFilter<Tblempresa> tableFilter = TableFilter.forTableView(empresaTable).apply();
        //TableFilter tableFilter = new TableFilter(empresaTable);
        tableFilter.setSearchStrategy((input,target) -> {
            try {
                return target.toString().toLowerCase().contains(input.toString().toLowerCase());
            } catch (Exception e) {
                return false;
            }
        });
        tableFilter.getBackingList().setAll(viewModel.getEmpresas());

		viewModel.selectedTableRowProperty().bind(empresaTable.getSelectionModel().selectedItemProperty());

		// When the selectedTableRowProperty changes in the viewModel we need to update the master
		viewModel.setOnSelect(vm -> empresaTable.getSelectionModel().select(vm));
	}

}
