package view;

public class Food{
	private String menu;
	private int quantity;
	
	public Food(String menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
	}
	
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return menu + " - quantity " + quantity;
	}
}