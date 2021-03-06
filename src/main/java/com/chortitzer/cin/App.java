package com.chortitzer.cin;

import com.chortitzer.cin.ui.main.MainView;
import com.chortitzer.cin.ui.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Properties;

/**
 * Entry point of the application.
 *
 * @author sialcasa
 */
public class App extends MvvmfxCdiApplication {

    private static final BorderPane root = new BorderPane();
    public static Stage mainStage;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void startMvvmfx(final Stage stage) throws Exception {

        final ViewTuple<MainView, MainViewModel> tuple
                = FluentViewLoader.fxmlView(MainView.class).load();

        // Locate View for loaded FXML file
        final Parent view = tuple.getView();

        final Scene scene = new Scene(view);

        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        scene.getStylesheets().add(this.getClass().getResource("/css/main.css").toExternalForm());

        stage.setOnCloseRequest(handle -> {
            Platform.exit();
            System.exit(0);
        });

        mainStage = stage;

        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/version.properties"));

        stage.setTitle("CIN " + prop.getProperty("project.version") + "." + prop.getProperty("project.build"));
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/cin-icon.png")));
        stage.setScene(scene);
        stage.show();
    }

}
