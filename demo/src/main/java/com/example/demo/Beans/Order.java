package com.example.demo.Beans;

public class Order {
    int id;
    String serviceType;
    String pickupDate;
    String itemCondition;
    int totalPrice;
    int customerId;
    int paymentId;
    int itemId;
    String deliveryType;

    public Order() {
    }

    public Order(int id, String serviceType, String pickupDate, String itemCondition, int totalPrice, int customerId, int paymentId, int itemId, String deliveryType) {
        this.id = id;
        this.serviceType = serviceType;
        this.pickupDate = pickupDate;
        this.itemCondition = itemCondition;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.itemId = itemId;
        this.deliveryType = deliveryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
