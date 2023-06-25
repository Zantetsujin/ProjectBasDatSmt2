package com.example.demo.Controllers;


import com.example.demo.Beans.*;
import com.example.demo.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    private Button BtnAddItem;

    @FXML
    private Button btnAddCategory;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnAddDiscount;

    @FXML
    private Button btnAddOrder;

    @FXML
    private Button btnAddPayment;

    @FXML
    private Button btnDeleteCategory;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnDeleteDiscount;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnDeleteOrder;

    @FXML
    private Button btnDeletePayment;

    @FXML
    private Button btnEditCategory;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnEditDiscount;

    @FXML
    private Button btnEditItem;

    @FXML
    private Button btnEditOrder;

    @FXML
    private Button btnEditPayment;

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
    private TableColumn<Order, String> colOrder_deliveryType;

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
        refreshTableDiscount();
        refreshTableItem();
        refreshTableItemCategory();
        refreshTableOrder();
        refreshTablePayment();
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

    void refreshTableDiscount(){
        colDiscount_discountId.setCellValueFactory(new PropertyValueFactory<Discount, Integer>("id"));
        colDiscount_discountStartDate.setCellValueFactory(new PropertyValueFactory<Discount, String>("startDate"));
        colDiscount_discountEndDate.setCellValueFactory(new PropertyValueFactory<Discount, String>("endDate"));
        colDiscount_discountPrice.setCellValueFactory(new PropertyValueFactory<Discount, String>("price"));

        String query = "SELECT * FROM discount";

        ObservableList<Discount> discountList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id = Integer.parseInt(queryOutput.getString("discount_id"));
                String startDate = queryOutput.getString("discount_start_date");
                String endDate = queryOutput.getString("discount_end_date");
                String price = queryOutput.getString("discount_price");
                discountList.add(new Discount(id, startDate, endDate, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableDiscount.setItems(discountList);
    }

    void refreshTableItem(){
        colItem_itemId.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        colItem_itemName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        colItem_itemPrice.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
        colItem_categoryId.setCellValueFactory(new PropertyValueFactory<Item, Integer>("category_id"));

        String query = "SELECT * FROM item";

        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id = Integer.parseInt(queryOutput.getString("item_id"));
                String name = queryOutput.getString("item_name");
                int price = Integer.parseInt(queryOutput.getString("item_price"));
                int category_id = Integer.parseInt(queryOutput.getString("category_id"));
                itemList.add(new Item(id, name, price, category_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableItem.setItems(itemList);
    }

    void refreshTableItemCategory(){
        colItemCategory_categoryId.setCellValueFactory(new PropertyValueFactory<ItemCategory, Integer>("id"));
        colItemCategory_categoryName.setCellValueFactory(new PropertyValueFactory<ItemCategory, String>("name"));
        colItemCategory_discountId.setCellValueFactory(new  PropertyValueFactory<ItemCategory, Integer>("discountId"));

        String query = "SELECT * FROM item_category";

        ObservableList<ItemCategory> item_CategoryList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id = Integer.parseInt(queryOutput.getString("category_id"));
                String name = queryOutput.getString("category_name");
                int discount_id = Integer.parseInt(queryOutput.getString("discount_id"));
                item_CategoryList.add(new ItemCategory(id, name, discount_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableItemCategory.setItems(item_CategoryList);
    }

    void refreshTableOrder(){
        colOrder_orderId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        colOrder_serviceType.setCellValueFactory(new PropertyValueFactory<Order, String>("serviceType"));
        colOrder_pickupDate.setCellValueFactory(new PropertyValueFactory<Order, String>("pickupDate"));
        colOrder_itemCondition.setCellValueFactory(new PropertyValueFactory<Order, String>("itemCondition"));
        colOrder_totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalPrice"));
        colOrder_customerId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("customerId"));
        colOrder_paymentId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("paymentId"));
        colOrder_itemId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("itemId"));
        colOrder_deliveryType.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryType"));

        String query = "SELECT * FROM orders";

        ObservableList<Order> orderList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id = Integer.parseInt(queryOutput.getString("order_id"));
                String serviceType = queryOutput.getString("service_type");
                String pickUpDate = queryOutput.getString("pickup_date");
                String itemCondition = queryOutput.getString("item_condition");
                int totalPrice = 0;
                int customerId = Integer.parseInt(queryOutput.getString("customer_id"));
                int paymentId = Integer.parseInt(queryOutput.getString("payment_id"));
                int itemId = Integer.parseInt(queryOutput.getString("item_id"));

                String deliveryType = queryOutput.getString("delivery_type");

                orderList.add(new Order(id, serviceType, pickUpDate, itemCondition, totalPrice, customerId, paymentId, itemId, deliveryType));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableOrder.setItems(orderList);
    }

    void refreshTablePayment(){
        colPayment_paymentId.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("id"));
        colPayment_paymentType.setCellValueFactory(new PropertyValueFactory<Payment, String>("type"));

        String query = "SELECT * FROM payment";

        ObservableList<Payment> paymentList = FXCollections.observableArrayList();

        try {
            Statement statement =  connectNow.databaseLink.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int id = Integer.parseInt(queryOutput.getString("payment_id"));
                String name = queryOutput.getString("payment_type");
                paymentList.add(new Payment(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tablePayment.setItems(paymentList);
    }

    @FXML
    void onAddCustomer(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/FormIsiData.fxml"));
            scene.setRoot((Parent) loader.load());
            FormCustomerController formCustomerController = loader.getController();
            formCustomerController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditCustomer() {
        if (tableCustomer.getSelectionModel().getSelectedItem() != null) {
            Customer customer = tableCustomer.getSelectionModel().getSelectedItem();
            System.out.println(customer.getAddress());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/FormIsiData.fxml"));
                FormCustomerController formCustomerController = loader.getController();
                scene.setRoot((Parent) loader.load());
                formCustomerController.setScene(scene);
                formCustomerController.setEdit(true);
                formCustomerController.setCustomerTemp(customer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void onDeleteCustomer(){
        if (tableCustomer.getSelectionModel().getSelectedItem() != null) {
            Customer customer = tableCustomer.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `customer` WHERE customer_id='%d'", customer.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTableCustomer();
    }
    @FXML
    void onAddCategory(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/item_category.fxml"));
            scene.setRoot((Parent) loader.load());
            FormCategoryController formCategoryController = loader.getController();
            formCategoryController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditCategory(){

    }
    @FXML
    void onDeleteCategory(){
        if (tableItemCategory.getSelectionModel().getSelectedItem() != null) {
            ItemCategory itemCategory = tableItemCategory.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `item_category` WHERE category_id='%d'", itemCategory.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTableItemCategory();
    }
    @FXML
    void onAddDiscount(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/discount.fxml"));
            scene.setRoot((Parent) loader.load());
            FormDiscountController formDiscountController = loader.getController();
            formDiscountController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditDiscount(){

    }
    @FXML
    void
    onDeleteDiscount(){
        if (tableDiscount.getSelectionModel().getSelectedItem() != null) {
            Discount discount = tableDiscount.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `discount` WHERE discount_id='%d'", discount.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTableDiscount();
    }
    @FXML
    void onAddItem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/item.fxml"));
            scene.setRoot((Parent) loader.load());
            FormItemController formItemController = loader.getController();
            formItemController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditItem(){

    }
    @FXML
    void onDeleteItem(){
        if (tableItem.getSelectionModel().getSelectedItem() != null) {
            Item item = tableItem.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `item` WHERE item_id='%d'", item.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTableItem();
    }
    @FXML
    void onAddPayment(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/payment.fxml"));
            scene.setRoot((Parent) loader.load());
            FormPaymentController formPaymentController = loader.getController();
            formPaymentController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditPayment(){

    }
    @FXML
    void onDeletePayment(){
        if (tablePayment.getSelectionModel().getSelectedItem() != null) {
            Payment payment = tablePayment.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `payment` WHERE payment_id='%d'", payment.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTablePayment();
    }
    @FXML
    void onAddOrder(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/FormOrder.fxml"));
            scene.setRoot((Parent) loader.load());
            FormOrderController formOrderController = loader.getController();
            formOrderController.setScene(scene);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void onEditOrder(){

    }
    @FXML
    void onDeleteOrder(){
        if (tableOrder.getSelectionModel().getSelectedItem() != null) {
            Order order = tableOrder.getSelectionModel().getSelectedItem();
            String query = String.format("DELETE FROM `orders` WHERE order_id='%d'", order.getId());

            try {
                Statement statement = connectNow.databaseLink.createStatement();
                statement.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        refreshTableOrder();
    }
}

