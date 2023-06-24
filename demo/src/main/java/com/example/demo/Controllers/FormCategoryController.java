package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FormCategoryController implements Initializable {

    @FXML
    private TextField txtID;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtDiscount;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtName;

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
        int categoryId = Integer.parseInt(txtID.getText());
        String name = String.valueOf(txtName.getText());
        int discountId = Integer.parseInt(txtDiscount.getText());

        String query = String.format("INSERT INTO `item_category`(`category_id`, `category_name`, `discount_id`) VALUES ('%d','%s','%d')", categoryId, name, discountId);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

