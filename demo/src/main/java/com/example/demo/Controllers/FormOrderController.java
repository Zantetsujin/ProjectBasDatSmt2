package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FormOrderController implements Initializable {

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnView;

    @FXML
    private ChoiceBox<Integer> cBoxItem;

    @FXML
    private ChoiceBox<String> cBoxServiceType;

    @FXML
    private DatePicker dpPickupDate;

    @FXML
    private TextField txtCustID;

    @FXML
    private ChoiceBox<String> cBoxDeliveryType;

    @FXML
    private TextField txtItemCondition;

    @FXML
    private TextField txtPaymentID;

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
        cBoxServiceType.getItems().removeAll(cBoxServiceType.getItems());
        cBoxServiceType.getItems().addAll("Cuci Kering", "Laundry");

        cBoxItem.getItems().removeAll(cBoxItem.getItems());
        cBoxItem.getItems().addAll(1, 2, 3);

        cBoxDeliveryType.getItems().removeAll(cBoxDeliveryType.getItems());
        cBoxDeliveryType.getItems().addAll("Ambil", "Antar");
    }

    @FXML
    void onSubmit() {
        int custID = Integer.parseInt(txtCustID.getText());
        String serviceType = cBoxServiceType.getSelectionModel().getSelectedItem();
        String pickupDate = String.valueOf(dpPickupDate.getValue());
        int item = cBoxItem.getSelectionModel().getSelectedItem();
        String itemCondition = txtItemCondition.getText();
        int paymentID = Integer.parseInt(txtPaymentID.getText());
        String deliveryType = cBoxDeliveryType.getSelectionModel().getSelectedItem();

        String query = String.format("INSERT INTO `orders`(`customer_id`, `service_type`, `pickup_date`, `item_id`, `item_condition`, `payment_id`, `delivery_type`) VALUES ('%d','%s','%s','%d','%s','%d','%s')",
                custID, serviceType, pickupDate, item, itemCondition, paymentID, deliveryType);

        try {
            Statement statement = connectNow.databaseLink.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    void onView() {

    }


}
