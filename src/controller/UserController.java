package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Connect;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.User;

public class UserController {

	Connect db = Connect.getConnection();
	private User user;
	
	public UserController() {
		user = new User("", "" , "");
	}
	
	public void InsertUserToDB(String Username, String Email, String Password, String ConfPassword) {
		
		if(validateRegistration(Username, Email, Password, ConfPassword) != null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText(validateRegistration(Username, Email, Password, ConfPassword));
			alert.show();
		} else {
			String role;
		    if (Username.equals("Edward Lauwis")) {
		    	role = "Admin";
		    } else {
		    	role = "Customer";
		    }
		    System.out.println(user.getRole());
			String query = String.format("INSERT INTO `user` (`Username` , `Email`, `Password`, `Role`) VALUES ('%s', '%s', '%s', '%s')", Username ,Email, Password, role);
			db.executeUpdate(query);
		}
	}
		
	private boolean isEmailUnique(String email) {
	    try {
	        String query = "SELECT * FROM user WHERE Email = ?";
	        PreparedStatement preparedStatement = db.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        return !resultSet.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private String validateRegistration(String username, String email, String password, String confirmPassword) {
		String error = "";
		if (username.isEmpty()) {
		    error += "Username cannot be empty.\n";
		}

		if(email.isEmpty()) {
		   	error += "Email cannot be empty and must be unique.\n";
		}
		    
		if (!isEmailUnique(email)) {
		    error += "Email is already registered.\n";
		}

		if (password.isEmpty()) {
		    error += "Password cannot be empty.\n";
		}

		if (!password.equals(confirmPassword)) {
		    error += "Password and Confirm Password do not match.\n";
		}

		if (password.length() < 6) {
		    error += "Password must be at least 6 characters long.\n";
		}

		if(error == "") {
		 	return null;
		} else {
		   	return error;
		}
	}

}
