package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import com.example.demo.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ResourceBundle;

public class FormCustomerController implements Initializable {
    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnView;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onSubmit(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNumber = txtPhoneNumber.getText();

        String query = String.format("INSERT INTO `customer`(`customer_id`, `nama`, `address`, `phone_number`) VALUES ('%d','%s','%s','%s')", id, name, address, phoneNumber);

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
    void onView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/data.fxml"));
            scene.setRoot((Parent) loader.load());
            DataController dataController = loader.getController();
            dataController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }


}
