package models;

public class Admin extends User{

	public Admin(String email, String password, String role) {
		super(email, password, "Admin");
	}

}
