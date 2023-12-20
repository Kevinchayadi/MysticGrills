package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import models.User;

public class ViewOrderedMenuItems extends BorderPane{
	public ViewOrderedMenuItems(Main main, Stage primaryStage, User user) {
		VBox container = new VBox();
        Label title = new Label("View/Update Ordered Menu Items");
        Button updateButton = new Button("Update");
        Button addMoreFood = new Button("Add more Food");
        
        ObservableList<Food> orderedFood = FXCollections.observableArrayList(user.getFood());
        ListView<Food> foodListView = new ListView<>(orderedFood);
        
        updateButton.setOnAction(e -> {
        	Food selectedFood = foodListView.getSelectionModel().getSelectedItem();
        	if(selectedFood != null) {
        		TextInputDialog dialog = new TextInputDialog();
        		dialog.setHeaderText("Update order");
        		dialog.setContentText("type '0' if you want to delete it");
        		dialog.setContentText("Fill new quantity: " + selectedFood.getMenu()+ ": ");
        		dialog.showAndWait().ifPresent(quantityText ->{
        			 int newQuantity = Integer.parseInt(quantityText);
                     if (newQuantity >= 0) {
                         if (newQuantity == 0) {
                             orderedFood.remove(selectedFood);
                         } else {
                             selectedFood.setQuantity(newQuantity);
                         }
                     } else {
                         Alert alert = new Alert(AlertType.ERROR);
                         alert.setHeaderText("Error");
                         alert.setContentText("Quantity must be greater than or equal to 0.");
                         alert.showAndWait();
                     }
        		});
        	} else {
        		Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Please select food");
        	}
        	primaryStage.setScene(new Scene(new ViewOrderedMenuItems(main, primaryStage, user), 600, 600));
        });
        
        addMoreFood.setOnAction( e -> {
        	primaryStage.setScene(new Scene(new ViewMenuItemsCustomer(main, primaryStage, user), 600, 600));
        });
        container.getChildren().addAll(title, foodListView, updateButton, addMoreFood);        
        setCenter(container);
	}
}
