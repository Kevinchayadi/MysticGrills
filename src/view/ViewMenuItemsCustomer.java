package view;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import models.User;

public class ViewMenuItemsCustomer extends BorderPane{

	private ComboBox<String> menuItemsComboBox;
    private TextField quantityField;
    private ArrayList<Food> food;    
	
	public ViewMenuItemsCustomer(Main main, Stage primaryStage, User user) {
		VBox container = new VBox();
		Label title = new Label("Add Order");
		ArrayList<String> foodList = new ArrayList<String>(Arrays.asList("Food1", "Food2", "Food3"));
		
		ObservableList<String> menuItems = FXCollections.observableArrayList(foodList);

        menuItemsComboBox = new ComboBox<>(menuItems);
        quantityField = new TextField();
        Button addButton = new Button("Add to Order");
        Button submitButton = new Button("Submit Order");
        food = user.getFood();
        
        addButton.setOnAction(events  -> {
            String menuItem = menuItemsComboBox.getValue();
            String quantityText = quantityField.getText();

            if (menuItem != null && !menuItem.isEmpty() && quantityText != null && !quantityText.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityText);
                    if (quantity >= 1) {
                        System.out.println("Added to order: " + menuItem + " - Quantity: " + quantity);
                        
                        Food newOrder = new Food(menuItem, quantity);
                        food.add(newOrder);
                    } else {
                        System.out.println("Quantity cannot be below 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity format.");
                }
            } else {
                System.out.println("Please choose a menu item and fill in the quantity.");
            }
        });

        submitButton.setOnAction(event -> {
        	user.setFood(food);
            System.out.println("Order submitted with status: Pending");
            primaryStage.setScene(new Scene (new ViewOrderedMenuItems(main, primaryStage, user), 600, 600));
        });
        
        System.out.println(user.getRole());
        container.getChildren().addAll(title, menuItemsComboBox, new Label("Quantity:"), quantityField, addButton,submitButton);
        setCenter(container);
	}

	public ArrayList<Food> getFood() {
		return food;
	}


	public void setFood(ArrayList<Food> food) {
		this.food = food;
	}
}
