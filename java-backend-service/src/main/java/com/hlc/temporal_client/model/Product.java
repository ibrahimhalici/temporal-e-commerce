package com.hlc.temporal_client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private long id;
    private String name;
    @JsonProperty("unit_price")
    private float unitPrice;
    private int quantity;
    @JsonProperty("total_price")
    private float totalPrice;

    public Product(long id, String name, float unitPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = getTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        this.totalPrice = unitPrice * quantity;
        return totalPrice;
    }
}
