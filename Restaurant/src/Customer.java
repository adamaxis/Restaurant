import java.util.Random;

public class Customer {
	LinkedList order;
	long orderNumber;
	boolean orderDone;
	static int orderCounter=1000000000;
	
	Customer() {
		orderNumber = 0;
		order = new LinkedList();
	}
		
	void GenerateOrder(Menu menu) {
		Random rng = new Random();
		int numItems = rng.nextInt(5)+1;
		for(int i = 0; i < numItems; i++) {
			int rndItem = rng.nextInt(menu.itemTotal) + 1;
			FoodItem fi = menu.menuToFoodItem(rndItem);
			if(fi != null) {
				Link l = new Link(fi);
				order.insert(l);
			}
			else System.out.println("Error generating order. Item#" + rndItem);
		}
		this.orderNumber = orderCounter++;
	}
	
	void ShowOrder () {
		System.out.printf("Order #: %d\n", this.orderNumber);
		order.displayList();
		
	}
		
}
