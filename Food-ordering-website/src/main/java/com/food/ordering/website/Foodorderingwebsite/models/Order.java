package com.food.ordering.website.Foodorderingwebsite.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import java.util.Date;
import java.util.List;

@Document(collection = "order")
public class Order {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private LatLng addressLatLng;

    private String paymentId;

    @NonNull
    private Double totalPrice;

    @NonNull
    private List<OrderItem> items;

    private String status = "NEW"; // Default status

    @NonNull
    private String userId; // Reference to the User document

    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Order() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Order(String id, String name, String address, LatLng addressLatLng, String paymentId,
                 Double totalPrice, List<OrderItem> items, String status, String userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.addressLatLng = addressLatLng;
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.items = items;
        this.status = status;
        this.userId = userId;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public LatLng getAddressLatLng() { return addressLatLng; }
    public String getPaymentId() { return paymentId; }
    public Double getTotalPrice() { return totalPrice; }
    public List<OrderItem> getItems() { return items; }
    public String getStatus() { return status; }
    public String getUserId() { return userId; }
    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setAddressLatLng(LatLng addressLatLng) { this.addressLatLng = addressLatLng; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public void setStatus(String status) { this.status = status; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

}
