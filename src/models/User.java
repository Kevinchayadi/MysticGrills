package models;

import java.util.ArrayList;

import view.Food;

public class User {
	private String username;
	private String email;
	private String password;
	private String role;
	private ArrayList<Food> food = new ArrayList<Food>();
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = "Customer";
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return this.role;
	}

	public ArrayList<Food> getFood() {
		return food;
	}

	public void setFood(ArrayList<Food> food) {
		this.food = food;
	}
}
