package com.example.demo.Controllers;


import com.example.demo.Beans.*;
import com.example.demo.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableColumn<Customer, String> colCustomer_customerAddress;

    @FXML
    private TableColumn<Customer, Integer> colCustomer_customerId;

    @FXML
    private TableColumn<Customer, String> colCustomer_customerName;

    @FXML
    private TableColumn<Customer, String> colCustomer_phoneNumber;

    @FXML
    private TableColumn<Discount, String> colDiscount_discountEndDate;

    @FXML
    private TableColumn<Discount, Integer> colDiscount_discountId;

    @FXML
    private TableColumn<Discount, String> colDiscount_discountPrice;

    @FXML
    private TableColumn<Discount, String> colDiscount_discountStartDate;

    @FXML
    private TableColumn<ItemCategory, Integer> colItemCategory_categoryId;

    @FXML
    private TableColumn<ItemCategory, String> colItemCategory_categoryName;

    @FXML
    private TableColumn<ItemCategory, Integer> colItemCategory_discountId;

    @FXML
    private TableColumn<Item, Integer> colItem_categoryId;

    @FXML
    private TableColumn<Item, Integer> colItem_itemId;

    @FXML
    private TableColumn<Item, String> colItem_itemName;

    @FXML
    private TableColumn<Item, Integer> colItem_itemPrice;

    @FXML
    private TableColumn<Order, Integer> colOrder_customerId;

    @FXML
    private TableColumn<Order, String> colOrder_itemCondition;

    @FXML
    private TableColumn<Order, Integer> colOrder_itemId;

    @FXML
    private TableColumn<Order, Integer> colOrder_orderId;

    @FXML
    private TableColumn<Order, Integer> colOrder_paymentId;

    @FXML
    private TableColumn<Order, String> colOrder_pickupDate;

    @FXML
    private TableColumn<Order, String> colOrder_serviceType;

    @FXML
    private TableColumn<Order, Integer> colOrder_totalPrice;

    @FXML
    private TableColumn<Payment, Integer> colPayment_paymentId;

    @FXML
    private TableColumn<Payment, String> colPayment_paymentType;

    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    private TableView<Discount> tableDiscount;

    @FXML
    private TableView<Item> tableItem;

    @FXML
    private TableView<ItemCategory> tableItemCategory;

    @FXML
    private TableView<Order> tableOrder;

    @FXML
    private TableView<Payment> tablePayment;
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
        refreshTableCustomer();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    void refreshTableCustomer(){
        colCustomer_customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        colCustomer_customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colCustomer_customerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        colCustomer_phoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

        String query = "SELECT * FROM customer";

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id =Integer.parseInt(queryOutput.getString("customer_id"));
                String nama = queryOutput.getString("nama");
                String address = queryOutput.getString("address");
                String phone_number = queryOutput.getString("phone_number");
                customerList.add(new Customer(id, nama, address, phone_number));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableCustomer.setItems(customerList);
    }

}

