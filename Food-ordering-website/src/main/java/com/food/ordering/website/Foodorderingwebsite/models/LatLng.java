package com.food.ordering.website.Foodorderingwebsite.models;

import com.mongodb.lang.NonNull;


public class LatLng {
 @NonNull
 private String lat;

 @NonNull
 private String lng;

 // Constructors
 public LatLng() {}

 public LatLng(String lat, String lng) {
     this.lat = lat;
     this.lng = lng;
 }

 // Getters
 public String getLat() { return lat; }
 public String getLng() { return lng; }

 // Setters
 public void setLat(String lat) { this.lat = lat; }
 public void setLng(String lng) { this.lng = lng; }
}