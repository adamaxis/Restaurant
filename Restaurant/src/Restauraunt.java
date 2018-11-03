import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Restauraunt {
	public static void main(String[] args) throws InterruptedException {
		Queue serviceLine = new Queue();
		Menu menu = buildMenu();
		menu.displayList();
		Service service = new Service();
		service.assignJobs();
		Random rng = new Random();
		boolean open = true;
		while(open) {
			TimeUnit.SECONDS.sleep(1);
			if((rng.nextInt(10) + 1) % 10 == 0) { // random chance for customer 
				System.out.println("A customer walks in");
				Customer c = new Customer();
				serviceLine.enqueue(c);
				serviceLine.peek();
				System.out.printf("There is now %d customer(s) in line\n", serviceLine.size());
			}
			if((rng.nextInt(10) + 1) % 10 == 0) { // handling of queue(rng is temporary)
				Customer c = serviceLine.peek();
				if(c==null) {
					System.out.printf("Staff is ready but no one is waiting\n");
				} else {
					c.GenerateOrder();
					System.out.printf("Customer is ready to order:\n");
					c.order.displayList();
				}
				
			}
		}
		
		
	}
	
	static Menu buildMenu() {
		Menu menu = new Menu();
		FoodItem fi = new FoodItem("Hamburger", 120, 1.00);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Double Cheeseburger", 240, 2.50);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chicken Sandwich", 180, 2.00);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Drink", 120, 1.50);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Drink", 120, 1.25);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Drink", 120, 1.00);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chocolate Shake", 120, 3.99);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Vanilla Shake", 120, 3.99);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Fries", 120, 1.75);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Fries", 120, 1.50);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Fries", 120, 1.00);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Value Meal", 120, 4.99);
		menu.insertMenuItem(fi);
		fi = new FoodItem("X-tra Meal", 120, 6.99);
		menu.insertMenuItem(fi);
		fi = new FoodItem("Apple Pie", 120, 1.50);
		menu.insertMenuItem(fi);
		return menu;
	}

}
