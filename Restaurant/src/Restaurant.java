// imports
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * Restaurant - handles the main loop of the program and contains
 * most of the important data structures, as well as main()
 * @author The Dude
 */
public class Restaurant {
	// speed settings, tied to sleep(). higher multiplier = faster (speedVar / speedMultiplier)
	static int speedVar = 500;
	static int speedMultiplier = 10;
	
	// speed settings tied to RNG
	static int speedCustomer = 10;
	static int speedOrder = 5;
	
	// Thread for GUI
	Thread threadGUI;
	
	// used by myGUI
	static long yourOrder=0;
	
	// restaurant status
	static boolean open = false;
	
	// global variables
	public static Menu menu;
	public static Queue serviceLine;
	public static LinkedList orderLine;
	public static Service service;
	public static double profit;
	
	// can't forget taxes
	static final double TAX_RATE = 1.06;
	
	public static void main(String[] args) throws InterruptedException {
		// initialize GUI
		Thread threadGUI = new MyGUI();
		threadGUI.start();
		
		// initialize globals
		serviceLine = new Queue();
		orderLine = new LinkedList();
		menu = Menu.buildMenu();
		menu.displayList();
		service = new Service();
		service.assignJobs();
		
		// initialize variables used in main loop
		Customer c;
		Random rng = new Random();
		
		// main program loop
		while(true) {
			// variable delay
			TimeUnit.MILLISECONDS.sleep((long) (speedVar / speedMultiplier));
			if(open) {
				if((rng.nextInt(speedCustomer) + 1) % speedCustomer == 0) {			// random chance for customer 
					MyGUI.GUI.signal(Service.SERVICE, String.format("A customer walks in.\n"));
					c = new Customer();
					// add them to queue
					serviceLine.enqueue(c);
					MyGUI.GUI.signal(Service.SERVICE, String.format("There is now %d customer(s) in line\n", serviceLine.size()));
				} else if((rng.nextInt(speedOrder) + 1) % speedOrder == 0) {	// handling of queue
					// check if we have any customers
					c = serviceLine.peek();
					if(c==null) {
						MyGUI.GUI.signal(Service.SERVICE, String.format("Staff is ready but no one is waiting.\n"));
					} else {
						// generate random customer order
						if(c.orderNumber == 0) c.GenerateOrder(menu);
						// print it out
						c.ShowOrder();
						// service customer
						orderLine.insert(new Link (serviceLine.dequeue()));
					}
				}
				// do staff jobs
				orderLine = service.doJobs(orderLine);
				
				// service customers waiting for their orders
				Link iterator = orderLine.getFirst();
				while(iterator != null) {
					c = (Customer) iterator.getData();
					if(c != null && c.orderDone) {
						if(c.orderNumber == yourOrder) {	// you are special
							String toString = "You walk up to the counter and check your finished order:\n";
							toString += c.PrintOrder();
							toString += "Everything seems to be in order. You thank the clerk and exit.";
							MyGUI.GUI.signal(Service.MISC, toString);
							// re-enable order button
							MyGUI.GUI.btnOrder.setEnabled(true);
						} else {							// they are not
							MyGUI.GUI.signal(Service.SERVICE, String.format("Order #%d is claimed and the customer leaves.\n", c.orderNumber));
						}
						// banish hungry customer(with food)
						orderLine.delete(iterator);
						MyGUI.GUI.signal(Service.SERVICE, String.format("There is now %d customer(s) waiting for their order\n", orderLine.countNodes()));
						// line has changed, so start at beginning
						iterator = orderLine.getFirst();
					} else iterator = iterator.next;
				}
			}
		}
	}
}

