package models;

public class User {
	private String email;
	private String password;
	private String role;
	
	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
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
}
