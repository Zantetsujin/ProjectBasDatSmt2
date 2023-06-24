package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FormDiscountController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dpStart;

    @FXML
    private TextField txtPrice;

    @FXML
    private DatePicker dpEnd;

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
    void save(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());
        String startDate = String.valueOf(dpStart.getValue());
        String endDate = String.valueOf(dpEnd.getValue());
        String price = String.valueOf(txtPrice.getText());

        String query = String.format("INSERT INTO `discount`(`discount_id`, `discount_start_date`, `discount_end_date`, `discount_price`) VALUES ('%d','%s','%s','%s')", id, startDate, endDate, price);


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
