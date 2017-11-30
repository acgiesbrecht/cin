package com.chortitzer.cin.bas.precioscontratos;

import com.chortitzer.cin.bas.precioscontratos.ui.main.MainView;
import com.chortitzer.cin.bas.precioscontratos.ui.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Entry point of the application.
 *
 * @author sialcasa
 */
public class App extends MvvmfxCdiApplication {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void startMvvmfx(final Stage stage) throws Exception {

        //Injector injector = Guice.createInjector(new DbModule());
        //Injector injector = Guice.createInjector(new JpaPersistModule("PU"));
        //injector.getInstance(JPAInitializer.class);

        final ViewTuple<MainView, MainViewModel> tuple
                = FluentViewLoader.fxmlView(MainView.class).load();

        //Injector injector = Guice.createInjector(new JpaPersistModule("PU"));
        //tblempresaDao = injector.getInstance(TblempresaDao.class);

        // Locate View for loaded FXML file
        final Parent view = tuple.getView();

        final Scene scene = new Scene(view);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });


        stage.setScene(scene);
        stage.show();
    }

}
