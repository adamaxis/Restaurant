import java.util.Random;
import javax.swing.ListModel;

/**
 * 
 * @author The Dude
 *
 */
public class Customer {
	LinkedList order;
	long orderNumber;
	boolean orderDone;
	private double cost;
	static int orderCounter=1000000000;
	
	// constructor
	Customer() {
		orderNumber = 0;
		cost = 0;
		order = new LinkedList();
		orderDone = false;
	}
	
	// getters and setters
	public LinkedList getOrder() {
		return order;
	}

	public void setOrder(LinkedList order) {
		this.order = order;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public boolean isOrderDone() {
		return orderDone;
	}

	public void setOrderDone(boolean orderDone) {
		this.orderDone = orderDone;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * 
	 * @param menu (Menu) Restaurant menu
	 */
	void GenerateOrder(Menu menu) {
		Random rng = new Random();
		int numItems = rng.nextInt(5)+1;
		for(int i = 0; i < numItems; i++) {
			int rndItem = rng.nextInt(menu.getItemTotal()) + 1;
			FoodItem fi = menu.menuToFoodItem(rndItem);
			if(fi != null) {
				Link l = new Link(fi);
				order.insert(l);
				cost += fi.cost;
			}
			else System.out.println("Error generating order. Item#" + rndItem);
		}
		Restaurant.profit += (cost * Restaurant.TAX_RATE);
		this.orderNumber = orderCounter++;
	}
	
	/**
	 * 
	 * @param menu (Menu) Restaurant menu
	 * @param list (ListModel) List of items
	 */
	void ProduceOrder(Menu menu, ListModel list) {
		for(int i = 0; i < list.getSize(); i++) {
			Link iterator = menu.search(list.getElementAt(i).toString());
			if(iterator != null) {
				FoodItem fi = new FoodItem((FoodItem) iterator.getData());
				Link l = new Link(fi);
				order.insert(l);
				cost += fi.cost;
			} else {
				System.out.println("Error generating order.");
			}
		}
		Restaurant.profit += (cost * Restaurant.TAX_RATE);
		this.orderNumber = orderCounter++;
	}
	
	// ShowOrder() - displays order information to user
	void ShowOrder () {
		System.out.printf("Order #: %d\n%sTotal cost: $%.2f\n\n", this.orderNumber, order.printList(), cost);
	}
	
	// PrintOrder() - Returns String of order information
	/**
	 * 
	 * @return (String) String of order-information
	 */
	String PrintOrder () {
		return String.format("Order #: %d\n%sTotal cost: $%.2f\n\n", this.orderNumber, order.printList(), cost);
	}

}
