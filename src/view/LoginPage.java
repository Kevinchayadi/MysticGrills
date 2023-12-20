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

public class LoginPage extends BorderPane{

	Connect db = Connect.getConnection();
	
	public LoginPage(Main main, Stage PrimaryStage, User user){
		VBox container = new VBox();
		Label title = new Label("Register");
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();
		Label emailLabel = new Label("Email");
		Label PasswordLabel = new Label("Password");
		Button loginButton = new Button("Login");
		Button backButton = new Button("Back");
		
		loginButton.setOnAction(e -> {
			String email = emailField.getText();
			String password = passwordField.getText();
			if(EmailFound(email, password)) {
				if(FindAdmin(email, password)) {
					PrimaryStage.setScene(new Scene(new MenuItemManagament(main, PrimaryStage, user), 600, 600));
				} else {
					PrimaryStage.setScene(new Scene(new ViewMenuItemsCustomer(main, PrimaryStage, user), 600, 600));
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Not Found");
				alert.setContentText("User Not Found");
				alert.show();
			}
		});
		
		backButton.setOnAction(e -> {
			PrimaryStage.setScene(new Scene(new RegisterPage(main, PrimaryStage, user), 600, 600));
		});
		
		container.getChildren().addAll(title, emailLabel, emailField, PasswordLabel, passwordField, loginButton, backButton);		
		setCenter(container);
	}
	
	private boolean EmailFound(String email, String password) {
	    try {
	    	String query = "SELECT * FROM user WHERE Email = ? && Password = ?";
	        PreparedStatement preparedStatement = db.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, password);
	        ResultSet resultSet = preparedStatement.executeQuery();
	    	return resultSet.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private Boolean FindAdmin(String email, String password) {
        try {
            String query = "SELECT username FROM `user` WHERE Role = 'Admin' && Email = ?";
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }

}
