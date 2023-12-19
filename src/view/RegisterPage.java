package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Connect;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import models.User;

public class RegisterPage extends BorderPane{

	Connect db = Connect.getConnection();
	
	public RegisterPage(Main main, Stage primaryStage, User user) {
		VBox container = new VBox();
		Label title = new Label("Register");
		TextField usernameField = new TextField();
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();
		PasswordField conPassField = new PasswordField();
		Label username = new Label("Username");
		Label email = new Label("Email");
		Label password = new Label ("Password");
		Label conPass = new Label ("Confirm Password");
		Label changePage = new Label("Already Have an Account?");
		Button registerButton = new Button("Register");
		
		container.getChildren().addAll(title, username, usernameField, email, emailField, password, passwordField, conPass, conPassField, changePage, registerButton);
		
		changePage.setOnMouseClicked(e -> {
			primaryStage.setScene(new Scene (new LoginPage(main, primaryStage, user), 600, 600));
		});
		
		String role = "Customer";
		
		registerButton.setOnAction(e -> {
			String Username = usernameField.getText();
			String Email = emailField.getText();
			String Password = passwordField.getText();
			String ConfirmPass = conPassField.getText();
			
			if(validateRegistration(Username, Email, Password, ConfirmPass) != null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText(validateRegistration(Username, Email, Password, ConfirmPass));
				alert.show();
			} else {
				String query = String.format("INSERT INTO `user` (`Username` , `Email`, `Password`, `Role`) VALUES ('%s', '%s', '%s', '%s')", usernameField.getText() ,emailField.getText(), passwordField.getText(), role);
				db.executeUpdate(query);
			}
		});
		
		primaryStage.setScene(new Scene(new HomePage(main, primaryStage, user), 600, 600));
	
		setCenter(container);
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
