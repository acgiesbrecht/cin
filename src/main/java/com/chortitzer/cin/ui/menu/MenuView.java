package com.chortitzer.cin.ui.menu;

import com.chortitzer.cin.model.bascula.TblBasAnalisisTipo;
import com.chortitzer.cin.model.bascula.TblBasFacturas;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.bascula.contribuyentes.TblContribuyentesUpdateView;
import com.chortitzer.cin.ui.bascula.contribuyentes.TblContribuyentesUpdateViewModel;
import com.chortitzer.cin.ui.bascula.facturas.TblBasFacturasView;
import com.chortitzer.cin.ui.bascula.facturas.TblBasFacturasViewModel;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionView;
import com.chortitzer.cin.ui.bascula.notasderemision.TblBasNotasDeRemisionViewModel;
import com.chortitzer.cin.ui.bascula.pesadas.TblpesadasView;
import com.chortitzer.cin.ui.bascula.productos.TblproductosView;
import com.chortitzer.cin.ui.bascula.productos.TblproductosViewModel;
import com.chortitzer.cin.ui.bascula.tblbasanalisistipo.TblBasAnalisisTipoView;
import com.chortitzer.cin.ui.bascula.tblbasanalisistipo.TblBasAnalisisTipoViewModel;
import com.chortitzer.cin.ui.bascula.tblbascontratos.TblBasContratosView;
import com.chortitzer.cin.ui.bascula.tblbascontratos.TblBasContratosViewModel;
import com.chortitzer.cin.ui.bascula.tblbasprecios.TblBasPreciosView;
import com.chortitzer.cin.ui.bascula.tblbasprecios.TblBasPreciosViewModel;
import com.chortitzer.cin.ui.bascula.tblempresa.TblempresaView;
import com.chortitzer.cin.ui.bascula.tblempresa.TblempresaViewModel;
import com.chortitzer.cin.ui.fba.tblproductoxconvertidores.TblProductoxConvertidoresView;
import com.chortitzer.cin.ui.fba.tblproductoxconvertidores.TblProductoxConvertidoresViewModel;
import com.chortitzer.cin.utils.EnumRoles;
import com.chortitzer.cin.ui.bascula.contribuyentes.TblContribuyentesUpdateView;
import com.chortitzer.cin.ui.bascula.pesadas.TblpesadasView;
import com.chortitzer.cin.ui.bascula.productos.TblproductosView;
import com.chortitzer.cin.ui.bascula.productos.TblproductosViewModel;
import com.chortitzer.cin.ui.bascula.tblbascontratos.TblBasContratosView;
import com.chortitzer.cin.ui.bascula.tblbascontratos.TblBasContratosViewModel;
import com.chortitzer.cin.ui.bascula.tblbasprecios.TblBasPreciosView;
import com.chortitzer.cin.ui.bascula.tblbasprecios.TblBasPreciosViewModel;
import com.chortitzer.cin.ui.bascula.tblempresa.TblempresaView;
import com.chortitzer.cin.ui.bascula.tblempresa.TblempresaViewModel;
import com.chortitzer.cin.utils.EnumRoles;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;

public class MenuView implements FxmlView<MenuViewModel> {

    @InjectViewModel
    private MenuViewModel viewModel;

    @Inject
    private Stage primaryStage;

    @FXML
    MenuItem mnuPesadas, mnuFacturas, mnuContratos, mnuPrecios, mnuProductos, mnuEmpresas, mnuAnalisisTipo, mnuUpdateContribuyentes;

    @FXML
    MenuItem mnuConvertidores;

    @FXML
    Menu mnuBascula, mnuBalanceados, mnuEsencia;

    public void initialize() {
        mnuPesadas.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuPesadas.setOnAction((event) -> {
            Task<Parent> loadTask = new Task<Parent>() {
                @Override
                protected Parent call() throws Exception {
                    try {
                        return FluentViewLoader.fxmlView(TblpesadasView.class).load().getView();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                }
            };
            loadTask.setOnSucceeded(evento -> {
                try {
                    setView(loadTask.getValue());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            new Thread(loadTask).start();
        });

        mnuFacturas.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuFacturas.setOnAction((event) -> {
            final ViewTuple<TblBasFacturasView, TblBasFacturasViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasView.class).load();
            setView(tuple.getView());
        });

        mnuContratos.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuContratos.setOnAction((event) -> {
            final ViewTuple<TblBasContratosView, TblBasContratosViewModel> tuple = FluentViewLoader.fxmlView(TblBasContratosView.class).load();
            setView(tuple.getView());
        });

        mnuPrecios.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuPrecios.setOnAction((event) -> {
            final ViewTuple<TblBasPreciosView, TblBasPreciosViewModel> tuple = FluentViewLoader.fxmlView(TblBasPreciosView.class).load();
            setView(tuple.getView());
        });

        mnuEmpresas.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuEmpresas.setOnAction((event) -> {
            final ViewTuple<TblempresaView, TblempresaViewModel> tuple = FluentViewLoader.fxmlView(TblempresaView.class).load();
            setView(tuple.getView());
        });

        mnuProductos.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuProductos.setOnAction((event) -> {
            final ViewTuple<TblproductosView, TblproductosViewModel> tuple = FluentViewLoader.fxmlView(TblproductosView.class).load();
            setView(tuple.getView());
        });

        mnuAnalisisTipo.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuAnalisisTipo.setOnAction((event) -> {
            final ViewTuple<TblBasAnalisisTipoView, TblBasAnalisisTipoViewModel> tuple = FluentViewLoader.fxmlView(TblBasAnalisisTipoView.class).load();
            setView(tuple.getView());
        });

        mnuUpdateContribuyentes.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuUpdateContribuyentes.setOnAction((event) -> {
            final ViewTuple<TblContribuyentesUpdateView, TblContribuyentesUpdateViewModel> tuple = FluentViewLoader.fxmlView(TblContribuyentesUpdateView.class).load();
            Stage modal = new Stage();
            modal.initOwner(primaryStage);
            tuple.getCodeBehind().owningStage.set(modal);
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.setScene(new Scene(tuple.getView()));
            modal.showAndWait();
        });


        mnuConvertidores.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuConvertidores.setOnAction((event) -> {
            final ViewTuple<TblProductoxConvertidoresView, TblProductoxConvertidoresViewModel> tuple = FluentViewLoader.fxmlView(TblProductoxConvertidoresView.class).load();
            setView(tuple.getView());
        });

        mnuEsencia.setDisable(!viewModel.hasRole(EnumRoles.ESENCIA));
    }

    private void setView(Parent view) {
        BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();
        bp.setCenter(view);
    }

}
