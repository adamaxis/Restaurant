import java.util.Random;

public class Customer {
	LinkedList order;
	boolean orderReceived;
	
	Customer() {
		order = null;
	}
		
	void GenerateOrder(Menu menu) {
		order = new LinkedList();
		Random rng = new Random();
		int numItems = rng.nextInt(5)+1;
		for(int i = 0; i < numItems; i++) {
			int rndItem = rng.nextInt(menu.itemTotal) + 1;
			FoodItem fi = menu.menuToFoodItem(rndItem);
			if(fi!= null) order.insertFirst(fi);
			else System.out.println("Error generating order. Item#" + rndItem);
		}
	}
		
}
