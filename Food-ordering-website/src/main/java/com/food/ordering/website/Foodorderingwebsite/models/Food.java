package com.food.ordering.website.Foodorderingwebsite.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.mongodb.lang.NonNull;

@Document(collection = "food")
public class Food extends AbstractMongoEventListener<Food>{

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private Double price;

    private List<String> tags;

    private Boolean favorite = false; // Default value

    private Integer stars = 3; // Default value

    @NonNull
    private String imageUrl;

    @NonNull
    private List<String> origins;

    @NonNull
    private String cookTime;

    private Date createdAt; // Timestamp fields
    private Date updatedAt;

    // Default constructor
    public Food() {
        // Set createdAt to current time
        this.createdAt = new Date();
        this.updatedAt = new Date(); // Initialize updatedAt to current time
    }

    // All-arguments constructor
    public Food(String id, String name, Double price, List<String> tags, Boolean favorite, Integer stars,
                String imageUrl, List<String> origins, String cookTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tags = tags;
        this.favorite = favorite != null ? favorite : this.favorite;
        this.stars = stars != null ? stars : this.stars;
        this.imageUrl = imageUrl;
        this.origins = origins;
        this.cookTime = cookTime;
        this.createdAt = new Date(); // Initialize createdAt to current time
        this.updatedAt = this.createdAt; // Initialize updatedAt to current time
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getTags() {
        return tags;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Integer getStars() {
        return stars;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public String getCookTime() {
        return cookTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Food> event) {
        Food food = event.getSource();
        food.updatedAt = new Date();
        if (food.createdAt == null) {
            food.createdAt = new Date();
        }
    }
}
