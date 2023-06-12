package com.example.project_basdat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.io.IOException;

public class dataController {
    private static Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project_basdat/FormIsiData.fxml"));
            scene.setRoot((Parent) loader.load());
            FormIsiData formIsiData = loader.getController();
            formIsiData.setScene(scene);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void edit() {

    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}