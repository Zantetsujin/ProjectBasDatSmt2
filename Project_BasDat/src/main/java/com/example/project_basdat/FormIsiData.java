package com.example.project_basdat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FormIsiData {
    private static Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    @FXML
    public void view(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project_basdat/data.fxml"));
            scene.setRoot((Parent) loader.load());
            EditData editData = loader.getController();
            editData.setScene(scene);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void submit() {

    }
}
