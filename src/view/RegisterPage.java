package view;

import controller.UserController;
import database.Connect;
import javafx.scene.Scene;
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
		Label title = new Label("Login");
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
		
		
		changePage.setOnMouseClicked(e -> {
			primaryStage.setScene(new Scene (new LoginPage(main, primaryStage, user), 600, 600));
		});		
		registerButton.setOnAction(e -> {
			String Username = usernameField.getText();
			String Email = emailField.getText();
			String Password = passwordField.getText();
			String ConfirmPass = conPassField.getText();
			
			UserController controller = new UserController();
			
			controller.InsertUserToDB(Username, Email, Password, ConfirmPass);
				
			primaryStage.setScene(new Scene (new LoginPage(main, primaryStage, user), 600, 600));
		});
		
		primaryStage.setScene(new Scene(new ViewMenuItemsCustomer(main, primaryStage, user), 600, 600));
		
		container.getChildren().addAll(title, username, usernameField, email, emailField, password, passwordField, conPass, conPassField, changePage, registerButton);
		setCenter(container);
	}
}
