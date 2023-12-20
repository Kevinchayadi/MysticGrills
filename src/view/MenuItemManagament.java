package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import models.User;

public class MenuItemManagament extends BorderPane{

	private ArrayList<Food> food;
	private ObservableList<String> foodNames;
    private ComboBox<String> menuComboBox;
	 
	public MenuItemManagament(Main main, Stage primaryStage, User user) {
		VBox container = new VBox();
		Label title = new Label("Item Management");
		foodNames = FXCollections.observableArrayList();
		Button addFood = new Button("Add Food");
		food = user.getFood();
		menuComboBox = new ComboBox<>(foodNames);
        updateMenuComboBox();

		
		addFood.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Add Food");
            dialog.setContentText("Enter the name of the food:");

            dialog.showAndWait().ifPresent(foodName -> {
                if (!foodName.isEmpty()) {
                    Food newFood = new Food(foodName, 0);
                    food.add(newFood);
                    System.out.println("Food added: " + foodName);
                    updateMenuComboBox();
                } else {
                    System.out.println("Food name cannot be empty.");
                }
            });
		});		
		container.getChildren().addAll(title, menuComboBox, addFood);
		setCenter(container);
	}

	private void updateMenuComboBox() {
		foodNames.clear();
        for (Food f : food) {
            foodNames.add(f.getMenu());
        }
        menuComboBox.setItems(foodNames);
	}

}
