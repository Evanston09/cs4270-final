package me.evankim.cs4270final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FLLRefBotApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FLLRefBotApplication.class.getResource("scoresheet-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("CS4270 Final: FLL RefBot");
        stage.setScene(scene);
        stage.show();
    }
}
