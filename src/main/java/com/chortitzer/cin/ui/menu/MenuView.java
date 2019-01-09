package com.chortitzer.cin.ui.menu;

import com.chortitzer.cin.ui.bascula.contribuyentes.TblContribuyentesUpdateView;
import com.chortitzer.cin.ui.bascula.contribuyentes.TblContribuyentesUpdateViewModel;
import com.chortitzer.cin.ui.bascula.facturas.flete.TblBasFacturasFleteView;
import com.chortitzer.cin.ui.bascula.facturas.flete.TblBasFacturasFleteViewModel;
import com.chortitzer.cin.ui.bascula.facturas.mercaderia.TblBasFacturasMercaderiaView;
import com.chortitzer.cin.ui.bascula.facturas.mercaderia.TblBasFacturasMercaderiaViewModel;
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
import com.chortitzer.cin.ui.bascula.vehiculos.TblBasVehiculosView;
import com.chortitzer.cin.ui.bascula.vehiculos.TblBasVehiculosViewModel;
import com.chortitzer.cin.ui.bascula.visor.VisorView;
import com.chortitzer.cin.ui.bascula.visor.VisorViewModel;
import com.chortitzer.cin.ui.fba.etiquetas.FbaEtiquetasView;
import com.chortitzer.cin.ui.fba.etiquetas.FbaEtiquetasViewModel;
import com.chortitzer.cin.ui.fps.etiquetas.FpsEtiquetasView;
import com.chortitzer.cin.ui.fps.etiquetas.FpsEtiquetasViewModel;
import com.chortitzer.cin.utils.EnumRoles;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.Properties;

public class MenuView implements FxmlView<MenuViewModel> {

    @InjectViewModel
    private MenuViewModel viewModel;

    @Inject
    private Stage primaryStage;

    @FXML
    MenuItem mnuBasculaVisor, mnuPesadas, mnuFacturasFlete, mnuFacturasMercaderia, mnuContratos, mnuPrecios, mnuProductos, mnuEmpresas, mnuAnalisisTipo, mnuVehiculos, mnuUpdateContribuyentes;

    @FXML
    MenuItem mnuFbaEtiquetas;

    @FXML
    Menu mnuBascula, mnuBalanceados, mnuEtiquetasVarias;

    @FXML
    MenuItem mnuEsenciaEtiquetas;

    public void initialize() {
        mnuPesadas.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuPesadas.setOnAction((event) -> {
            Task<Parent> loadTask = new Task<Parent>() {
                @Override
                protected Parent call() {
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
                    setView(loadTask.getValue(),"Pesadas");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            new Thread(loadTask).start();
        });

        mnuBascula.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));

        mnuFacturasFlete.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuFacturasFlete.setOnAction((event) -> {
            final ViewTuple<TblBasFacturasFleteView, TblBasFacturasFleteViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasFleteView.class).load();
            setView(tuple.getView(),"Facturas de Flete");
        });

        mnuFacturasMercaderia.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuFacturasMercaderia.setOnAction((event) -> {
            final ViewTuple<TblBasFacturasMercaderiaView, TblBasFacturasMercaderiaViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasMercaderiaView.class).load();
            setView(tuple.getView(),"Facturas de Mercaderia");
        });

        mnuContratos.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuContratos.setOnAction((event) -> {
            final ViewTuple<TblBasContratosView, TblBasContratosViewModel> tuple = FluentViewLoader.fxmlView(TblBasContratosView.class).load();
            setView(tuple.getView(),"Contratos");
        });

        mnuPrecios.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuPrecios.setOnAction((event) -> {
            final ViewTuple<TblBasPreciosView, TblBasPreciosViewModel> tuple = FluentViewLoader.fxmlView(TblBasPreciosView.class).load();
            setView(tuple.getView(),"Precios");
        });

        mnuEmpresas.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuEmpresas.setOnAction((event) -> {
            final ViewTuple<TblempresaView, TblempresaViewModel> tuple = FluentViewLoader.fxmlView(TblempresaView.class).load();
            setView(tuple.getView(),"Empresas");
        });

        mnuProductos.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuProductos.setOnAction((event) -> {
            final ViewTuple<TblproductosView, TblproductosViewModel> tuple = FluentViewLoader.fxmlView(TblproductosView.class).load();
            setView(tuple.getView(),"Prodcutos");
        });

        mnuAnalisisTipo.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuAnalisisTipo.setOnAction((event) -> {
            final ViewTuple<TblBasAnalisisTipoView, TblBasAnalisisTipoViewModel> tuple = FluentViewLoader.fxmlView(TblBasAnalisisTipoView.class).load();
            setView(tuple.getView(),"Tipo de Analisis");
        });

        mnuVehiculos.setDisable(!viewModel.hasRole(EnumRoles.BASCULA));
        mnuVehiculos.setOnAction((event) -> {
            final ViewTuple<TblBasVehiculosView, TblBasVehiculosViewModel> tuple = FluentViewLoader.fxmlView(TblBasVehiculosView.class).load();
            setView(tuple.getView(), "Vehiculos de Transportadoras");
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

        mnuFbaEtiquetas.setDisable(!viewModel.hasRole(EnumRoles.BALANCEADOS));
        mnuFbaEtiquetas.setOnAction((event) -> {
            final ViewTuple<FbaEtiquetasView, FbaEtiquetasViewModel> tuple = FluentViewLoader.fxmlView(FbaEtiquetasView.class).load();
            setView(tuple.getView(),"Etiquetas");
        });

        mnuEtiquetasVarias.setDisable(!viewModel.hasRole(EnumRoles.ETIQUETAS));

        mnuBasculaVisor.setVisible(viewModel.hasRole(EnumRoles.BASCULAVISOR));
        mnuBasculaVisor.setOnAction((event) -> {
            final ViewTuple<VisorView, VisorViewModel> tuple = FluentViewLoader.fxmlView(VisorView.class).load();
            setView(tuple.getView(), "Visor Bsscula");
        });


        mnuEsenciaEtiquetas.setOnAction((event) -> {
            final ViewTuple<FpsEtiquetasView, FpsEtiquetasViewModel> tuple = FluentViewLoader.fxmlView(FpsEtiquetasView.class).load();
            setView(tuple.getView(), "Etiquetas Esencia");
        });
    }

    private void setView(Parent view, String displayTitle) {
        try {
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/version.properties"));
            String title = "CIN " + prop.getProperty("project.version") + "." + prop.getProperty("project.build");
            primaryStage.setTitle(title + " " + displayTitle);
            bp.setCenter(view);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
