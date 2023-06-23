package com.example.demo.Beans;

public class ItemCategory {
    int id;
    String name;
    int discountId;

    public ItemCategory(){

    }

    public ItemCategory(int id, String name, int discountId) {
        this.id = id;
        this.name = name;
        this.discountId = discountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
