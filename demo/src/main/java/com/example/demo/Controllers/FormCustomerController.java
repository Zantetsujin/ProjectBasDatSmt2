package com.example.demo.Controllers;

import com.example.demo.Beans.Customer;
import com.example.demo.DatabaseConnection;
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
    private boolean isEdit = false;
    private Customer customerTemp;
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

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public Customer getCustomerTemp() {
        return customerTemp;
    }

    public void setCustomerTemp(Customer customerTemp) {
        this.customerTemp = customerTemp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isEdit) {
            txtID.setText(String.valueOf(customerTemp.getId()));
            txtName.setText(customerTemp.getName());
            txtAddress.setText(customerTemp.getAddress());
            txtPhoneNumber.setText(customerTemp.getPhoneNumber());
        } else {
            txtID.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
        }
    }
    @FXML
    void onSubmit(ActionEvent event) {
        if (isEdit){
            int id = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phoneNumber = txtPhoneNumber.getText();

            String query = String.format("UPDATE `customer` SET `nama`='%s',`address`='%s', `phone_number`='%s' WHERE customer_id = '%d'", name, address, phoneNumber, id);

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
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
