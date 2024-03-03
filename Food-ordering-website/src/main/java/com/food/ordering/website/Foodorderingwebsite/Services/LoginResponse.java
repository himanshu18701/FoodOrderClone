package com.food.ordering.website.Foodorderingwebsite.Services;

public class LoginResponse {
	private String token;
	private String name;
	private String id;
	private String address;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LoginResponse(String token, String name,String id, String address) {
		this.token = token;
		this.name = name;
		this.id=id;
		this.address=address;
	}
	public LoginResponse() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
