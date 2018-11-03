import java.util.Random;

public class Customer {
	LinkedList order;
	boolean orderReceived;
	static Menu menu;
	
	Customer() {

	}
		
	void GenerateOrder() {
		order = new LinkedList();
		Random rng = new Random();
		int numItems = rng.nextInt(10)+1;
		for(int i = 1; i < numItems; i++) {
			int rndItem = rng.nextInt(Menu.MENU_ITEMS) + 1;
			FoodItem fi = new FoodItem();
			fi = menu.menuToFoodItem(rndItem);
			order.insertFirst(fi);
		}
	}
		
}
