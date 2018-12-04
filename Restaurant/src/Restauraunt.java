import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Restauraunt {
	// speed settings, tied to sleep(). higher multiplier = faster (speedVar / speedMultiplier)
	final static int speedVar = 500;
	static int speedMultiplier = 10;
	
	public static void main(String[] args) throws InterruptedException {
		Queue serviceLine = new Queue();
		LinkedList orderLine = new LinkedList();
		Menu menu = buildMenu();
		menu.displayList();
		Service service = new Service();
		service.assignJobs();
		Customer c;
		Random rng = new Random();
		boolean open = true;
		while(open) {
			TimeUnit.MILLISECONDS.sleep((long) (speedVar / speedMultiplier));
			if((rng.nextInt(5) + 1) % 5 == 0) { // random chance for customer 
				System.out.println("A customer walks in");
				c = new Customer();
				serviceLine.enqueue(c);
				System.out.printf("There is now %d customer(s) in line\n", serviceLine.size());
			} else if((rng.nextInt(3) + 1) % 3 == 0) { // handling of queue(rng is temporary)
				c = serviceLine.peek();
				if(c==null) {
					System.out.printf("Staff is ready but no one is waiting\n");
				} else {
					c.GenerateOrder(menu);
					//System.out.printf("Customer is ready to order:\n");
					c.ShowOrder();
					// service customer
					orderLine.insert(new Link (serviceLine.dequeue()));
					//System.out.printf("Customer has been serviced.\n");
				}
			}
			orderLine = service.doJobs(orderLine);
			Link iterator = orderLine.first;
			while(iterator != null) {
				c = (Customer) iterator.getData();
				if(c != null && c.orderDone) {
					System.out.printf("Order #%d is claimed and the customer leaves.\n", c.orderNumber);
					orderLine.delete(iterator);
					System.out.printf("There is now %d customer(s) waiting for their order\n", orderLine.countNodes());
					iterator = orderLine.first;
				} else iterator = iterator.next;
			}
		}
		
		
	}
	
	static Menu buildMenu() {
		Menu menu = new Menu();
		FoodItem fi = new FoodItem("Hamburger", 120, 1.00, FoodItem.TYPE_GRILL);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Double Cheeseburger", 150, 2.50, FoodItem.TYPE_GRILL);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chicken Sandwich", 120, 2.00, FoodItem.TYPE_OVEN);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Drink", 60, 1.50, FoodItem.TYPE_DRINK);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Drink", 60, 1.25, FoodItem.TYPE_DRINK);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Drink", 60, 1.00, FoodItem.TYPE_DRINK);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chocolate Shake", 60, 3.99, FoodItem.TYPE_SHAKE);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Vanilla Shake", 60, 3.99, FoodItem.TYPE_SHAKE);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Fries", 100, 1.75, FoodItem.TYPE_FRYER);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Fries", 100, 1.50, FoodItem.TYPE_FRYER);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Fries", 100, 1.00, FoodItem.TYPE_FRYER);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Value Meal", 150, 4.99, FoodItem.TYPE_MEAL);
		menu.insertMenuItem(fi);
		fi = new FoodItem("X-tra Meal", 150, 6.99, FoodItem.TYPE_MEAL);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Apple Pie", 60, 1.50, FoodItem.TYPE_OVEN);
		menu.insertMenuItem(fi);
		return menu;
	}

}
