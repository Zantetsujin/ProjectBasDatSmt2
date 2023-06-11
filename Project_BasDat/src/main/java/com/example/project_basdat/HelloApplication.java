package com.example.project_basdat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FormIsiData.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        fxmlLoader.<FormIsiData>getController().setScene(scene);
        stage.setTitle("DATABASE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}