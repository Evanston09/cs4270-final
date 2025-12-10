package me.evankim.cs4270final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main JavaFX Application class for the FLL RefBot Scoresheet Scanner.
 * Initializes the GUI with the scoresheet view loaded from FXML.
 */
public class FLLRefBotApplication extends Application {
    /**
     * Starts the JavaFX application by loading the scoresheet view.
     * Initializes the primary stage with a 900x800 window displaying the scoresheet interface.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the scoresheet-view.fxml file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FLLRefBotApplication.class.getResource("scoresheet-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("CS4270 Final: FLL RefBot");
        stage.setScene(scene);
        stage.show();
    }
}
