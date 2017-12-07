package com.chortitzer.cin.bas.precioscontratos.ui.menu;

import com.chortitzer.cin.bas.precioscontratos.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.pesadas.TblpesadasView;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.pesadas.TblpesadasViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.productos.TblproductosView;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.productos.TblproductosViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbascontratos.TblBasContratosView;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbascontratos.TblBasContratosViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbasprecios.TblBasPreciosView;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblbasprecios.TblBasPreciosViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblempresa.TblempresaView;
import com.chortitzer.cin.bas.precioscontratos.ui.bascula.tblempresa.TblempresaViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.fba.tblproductoxconvertidores.TblProductoxConvertidoresView;
import com.chortitzer.cin.bas.precioscontratos.ui.fba.tblproductoxconvertidores.TblProductoxConvertidoresViewModel;
import com.chortitzer.cin.bas.precioscontratos.ui.main.MainView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.inject.Inject;

public class MenuView implements FxmlView<MenuViewModel> {



	@InjectViewModel
	private MenuViewModel viewModel;

	@Inject
	private Stage primaryStage;

    @FXML
    void mnuPesadas(){
        Task<Parent> loadTask = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                return FluentViewLoader.fxmlView(TblpesadasView.class).load().getView();
            }
        };
        loadTask.setOnSucceeded(event -> {
            setView(loadTask.getValue());
        });

        new Thread(loadTask).start();
    }

    @FXML
    void mnuContratos(){
        final ViewTuple<TblBasContratosView, TblBasContratosViewModel> tuple = FluentViewLoader.fxmlView(TblBasContratosView.class).load();
        setView(tuple.getView());
    }

    @FXML
    void mnuPrecios(){
        final ViewTuple<TblBasPreciosView, TblBasPreciosViewModel> tuple = FluentViewLoader.fxmlView(TblBasPreciosView.class).load();
        setView(tuple.getView());
    }

    @FXML
    void mnuEmpresas(){
        final ViewTuple<TblempresaView, TblempresaViewModel> tuple = FluentViewLoader.fxmlView(TblempresaView.class).load();
        setView(tuple.getView());
    }

    @FXML
    void mnuProductos(){
        final ViewTuple<TblproductosView, TblproductosViewModel> tuple = FluentViewLoader.fxmlView(TblproductosView.class).load();
        setView(tuple.getView());
    }

    @FXML
    void mnuConvertidores(){
        final ViewTuple<TblProductoxConvertidoresView, TblProductoxConvertidoresViewModel> tuple = FluentViewLoader.fxmlView(TblProductoxConvertidoresView.class).load();
        setView(tuple.getView());
    }

	private void setView(Parent view){
        BorderPane bp = (BorderPane)primaryStage.getScene().getRoot();
        bp.setCenter(view);
	}

}
