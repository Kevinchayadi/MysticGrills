package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RegisterPage;
import models.User;

public class Main extends Application{
	
	User user = new User(" ", " ", " ");
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setScene(new Scene(new RegisterPage(this, arg0, user), 600, 600));
		arg0.show();
	}
}
