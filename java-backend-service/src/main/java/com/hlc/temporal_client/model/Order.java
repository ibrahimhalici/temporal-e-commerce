package com.hlc.temporal_client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import java.time.LocalDateTime;

public class Order {

    private long id;
    @JsonProperty("user_id")
    private long userId;
    private List<Product> products;
    @JsonProperty("order_date")
    private LocalDateTime orderDate;
    private OrderStatus status;
    @JsonProperty("total_price")
    private float totalPrice;

    public Order(long id, long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
        this.id = id;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.CREATED;
        this.totalPrice = getTotalPrice();
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProductNames(List<Product> products) {
        this.products = products;
    }

    public float getTotalPrice() {
        updateTotalPrice();
        return this.totalPrice;
    }

    public void updateTotalPrice() {
        float total = 0;
        for (Product product : this.products) {
            total += product.getTotalPrice();
        }
        this.totalPrice = total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

