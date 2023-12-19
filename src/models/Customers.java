package models;

public class Customers extends User{

	public Customers(String email, String password) {
		super(email, password, "Customer");
	}
}
