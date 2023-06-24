package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FormPaymentController implements Initializable {

    @FXML
    private TextField txtID;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtType;

    private Scene scene;
    DatabaseConnection connectNow;
    Connection connectionSQL;

    {
        connectNow = new DatabaseConnection();
        connectionSQL = connectNow.getConnection();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    void save(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());
        String paymentType = txtType.getText();

        String query = String.format("INSERT INTO `payment`(`payment_id`, `payment_type`) VALUES ('%d','%s')", id, paymentType);

        try {
            Statement statement = connectNow.databaseLink.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/data.fxml"));
            scene.setRoot((Parent) loader.load());
            DataController dataController = loader.getController();
            dataController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void back(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

