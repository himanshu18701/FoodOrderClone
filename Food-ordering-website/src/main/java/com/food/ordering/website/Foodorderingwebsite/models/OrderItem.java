package com.food.ordering.website.Foodorderingwebsite.models;


import com.mongodb.lang.NonNull;

class OrderItem {
    @NonNull
    private String foodId;

    @NonNull
    private Double price;

    @NonNull
    private Integer quantity;
    public OrderItem() {}

    public OrderItem(String food, Double price, Integer quantity) {
        this.foodId = food;
        this.price = price;
        this.quantity = quantity;
    }
    public String getFood() { return foodId; }
    public Double getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
    public void setFood(String food) { this.foodId = food; }
    public void setPrice(Double price) { this.price = price; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
