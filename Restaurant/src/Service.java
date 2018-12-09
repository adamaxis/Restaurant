// imports
import java.util.Date;

/**
 * Service - handles the kitchen, jobs, and the different
 * food item states
 * @author The Dude
 *
 */
public class Service {
	// kitchen stations
	final static int GRILL = 1;
	final static int FRYER = 2;
	final static int OVEN = 4;
	final static int DRINK = 5;
	final static int SHAKE = 6;
	final static int CASHIER = 7;
	final static int SERVICE = 8;
	final static int MISC = 9;
	
	// food states
	final static int FOOD_STATE_FRESH = 0;
	final static int FOOD_STATE_0 = 1;
	final static int FOOD_STATE_25 = 2;
	final static int FOOD_STATE_50 = 3;
	final static int FOOD_STATE_75 = 4;
	final static int FOOD_STATE_READY = 5;
	final static int FOOD_STATE_SERVED = 6;
	
	// members
	int atGrill;
	int atFryer;
	int atDrink;
	int atShake;
	int atCashier;
	int atMisc;
	int numEmployees;
	
	// employee list
	Employee[] empList;
	
	/**
	 * constructor
	 */
	public Service() {
		numEmployees = 6;	// later, this will be random
		atGrill=0;
		atFryer=0;
		atDrink=0;
		atShake=0;
		atCashier=0;
		atMisc=0;
		// initialize employees
		empList = new Employee[numEmployees];
		for(int i=0; i < numEmployees; i++) {
			empList[i] = new Employee(i+1);
		}
	}
	
	/**
	 * assignJobs() - assigns available employees to a station to work
	 *... unfortunately, I didn't have time to expand this
	 */
	void assignJobs() {
		// loop to assign jobs
		for(int i = 0; i < numEmployees; i++) {
			if(atCashier==0) {
				empList[i].setJob(CASHIER);
				atCashier=1;
				MyGUI.GUI.signal(CASHIER, String.format("Employee#%d moves to the cashier.\n", empList[i].getNum()));
			}
			else if(atGrill==0) {
				empList[i].setJob(GRILL);
				atGrill=1;
				MyGUI.GUI.signal(GRILL, String.format("Employee#%d moves to the grill.\n", empList[i].getNum()));
			}
			else if(atFryer==0) {
				empList[i].setJob(FRYER);
				atFryer=1;
				MyGUI.GUI.signal(FRYER, String.format("Employee#%d moves to the fryer.\n", empList[i].getNum()));
			}
			else if(atDrink==0) {
				empList[i].setJob(DRINK);
				atDrink=1;
				MyGUI.GUI.signal(DRINK, String.format("Employee#%d moves to the drink machine.\n", empList[i].getNum()));
			}
			else if(atShake==0) {
				empList[i].setJob(SHAKE);
				atShake=1;
				MyGUI.GUI.signal(SHAKE, String.format("Employee#%d moves to the shake machine.\n", empList[i].getNum()));
			}
			else if(atMisc==0) {
				empList[i].setJob(MISC);
				atMisc=1;
				MyGUI.GUI.signal(SERVICE, String.format("Employee#%d moves to cleanup.\n", empList[i].getNum()));
			}
		}
	}
	
	/**
	 * doJobs() - handles jobs and the processing of food
	 * @param orderList (LinkedList) List of customers waiting for their orders
	 * @return (LinkedList) List of serviced customers
	 */
	LinkedList doJobs(LinkedList orderList) {
		// initialize variables for loop
		Link iteratorCustomer = orderList.getFirst();
		Customer c;
		boolean orderComplete=true;
		
		// main kitchen loop
		while(iteratorCustomer != null) {
			c = (Customer) iteratorCustomer.getData();
			if(c != null) {
				// if orderComplete stays true, order is complete
				orderComplete = true;
				Link iterator = c.order.getFirst();
				FoodItem fi;
				// loop to check and process every order item
				while(iterator != null) {
					fi = (FoodItem) iterator.getData();
					// order checking
					Date now = new Date();
					// 1000 is seconds to minutes
					long time = (now.getTime() / 1000) - fi.startTime;
					// food state loop - where all the updates happen
					switch(fi.prepState) {
						case FOOD_STATE_FRESH:
							fi.startTime = now.getTime() / 1000;
							fi = food_state_fresh(fi, time);
							orderComplete = false;
							break;					
						case FOOD_STATE_0:
							fi = food_state_0(fi, time);
							orderComplete = false;
							break;
						
						case FOOD_STATE_25:
							fi = food_state_25(fi, time);
							orderComplete = false;
							break;
						
						case FOOD_STATE_50:
							fi = food_state_50(fi, time);
							orderComplete = false;
							break;
						
						case FOOD_STATE_75:
							fi = food_state_75(fi, time);
							orderComplete = false;
							break;
						
						case FOOD_STATE_READY:
							fi = food_state_ready(fi, time);
							break;
						
						case FOOD_STATE_SERVED:
							// didn't get to add this one, but this is maybe
							// where people would eat their food
							break;
						default:
					}
					iterator = iterator.next;
				}

			}
			if(orderComplete == true) {
				// send out for delivery
				MyGUI.GUI.signal(SERVICE, String.format("One of the staff goes into the kitchen and comes out with order #%d.\n", c.orderNumber));
				c.orderDone = true;
				orderComplete = false;
			}
			iteratorCustomer = iteratorCustomer.next;
		}
		return orderList;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_ready(FoodItem fi, long time) {
		switch(fi.type) {
			case FoodItem.TYPE_GRILL:

				break;
			case FoodItem.TYPE_FRYER:

				break;
			case FoodItem.TYPE_OVEN:

				break;
			case FoodItem.TYPE_DRINK:
				MyGUI.GUI.signal(SERVICE, String.format("A staffer brings a drink out and sets it on the order plate\n"));
				fi.prepState = FOOD_STATE_SERVED;
				break;
			case FoodItem.TYPE_SHAKE:
				MyGUI.GUI.signal(SERVICE, String.format("A staffer brings a shake out and sets it on the order plate\n"));
				fi.prepState = FOOD_STATE_SERVED;
				break;
		}
		return fi;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_fresh(FoodItem fi, long time) {
		// transitory state
		// updates kitchen that food is cooking
		fi.prepState = FOOD_STATE_0;
		switch(fi.type) {
			case FoodItem.TYPE_GRILL:
				MyGUI.GUI.signal(GRILL, String.format("A fresh %s is thrown on the grill.\n", fi.getFrozenName()));
				break;
			case FoodItem.TYPE_FRYER:
				MyGUI.GUI.signal(FRYER, String.format("A staffer drops some %s into the fryer.\n", fi.getFrozenName()));
				break;
			case FoodItem.TYPE_OVEN:
				MyGUI.GUI.signal(OVEN, String.format("A staffer throws a %s into the oven\n", fi.getFrozenName()));
				break;
			case FoodItem.TYPE_DRINK:
				MyGUI.GUI.signal(DRINK, String.format("A kitchen staffer brings a %s to the fountain.\n", fi.getFrozenName()));
				break;
			case FoodItem.TYPE_SHAKE:
				MyGUI.GUI.signal(SHAKE, String.format("A kitchen staffer carries a %s over to the shake machine.\n", fi.getFrozenName()));
				break;
		}
		return fi;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_0(FoodItem fi, long time) {
		// check for 25% completion
		if (time / (fi.prepTime / Restaurant.speedMultiplier) > 0.25) {
			fi.prepState = FOOD_STATE_25;
			switch(fi.type) {
				case FoodItem.TYPE_GRILL:
					MyGUI.GUI.signal(GRILL, String.format("One of the %ss is slightly brown.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_FRYER:
					MyGUI.GUI.signal(FRYER, String.format("The scent of thawing %s waft from the fryer.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_OVEN:
					MyGUI.GUI.signal(OVEN, String.format("The %s in the oven is getting hot.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_DRINK:
					MyGUI.GUI.signal(DRINK, String.format("One of the %ss is about half full.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_SHAKE:
					MyGUI.GUI.signal(SHAKE, String.format("The shake machine whizzes and whirrs as it fills the %s.\n", fi.getFrozenName()));
					break;
			}
		}
		return fi;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_25(FoodItem fi, long time) {
		// check for 50% completion
		if (time / (fi.prepTime / Restaurant.speedMultiplier) > 0.50) {
			fi.prepState = FOOD_STATE_50;
			switch(fi.type) {
				case FoodItem.TYPE_GRILL:
					MyGUI.GUI.signal(GRILL, String.format("The %s singes and sizzles on the grill.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_FRYER:
					MyGUI.GUI.signal(FRYER, String.format("The %s are completely thawed out now.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_OVEN:
					MyGUI.GUI.signal(OVEN, String.format("The smell of %s floats through the air.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_DRINK:
					MyGUI.GUI.signal(DRINK, String.format("A %s pops and fizzles as it fills up.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_SHAKE:
					MyGUI.GUI.signal(SHAKE, String.format("One of the %ss is almost full\n", fi.getFrozenName()));
					break;
			}
		}
		return fi;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_50(FoodItem fi, long time) {
		// check for 75% completion
		if (time / (fi.prepTime / Restaurant.speedMultiplier) > 0.75) {
			fi.prepState = FOOD_STATE_75;
			switch(fi.type) {
				case FoodItem.TYPE_GRILL:
					MyGUI.GUI.signal(GRILL, String.format("Kitchen staff flip the %s off of the grill and dally it with condiments.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_FRYER:
					MyGUI.GUI.signal(FRYER, String.format("A staff member fishes some %s out of the fryer and into a fresh cup.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_OVEN:
					MyGUI.GUI.signal(OVEN, String.format("The %s is removed from the oven and taken to the back.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_DRINK:
					MyGUI.GUI.signal(DRINK, String.format("The %s is now full.\n", fi.getFrozenName()));
					break;
				case FoodItem.TYPE_SHAKE:
					MyGUI.GUI.signal(SHAKE, String.format("The frosty machine grinds to a halt, as the %s is full.\n", fi.getFrozenName()));
					break;
			}
		}		
		return fi;
	}
	
	/**
	 * 
	 * @param fi (FoodItem) FoodItem to evaluate
	 * @param time (Time) Time to compare to FoodItem
	 * @return (FoodItem) Modified FoodItem
	 */
	FoodItem food_state_75(FoodItem fi, long time) {
		// check for 100% completion
		if (time / (fi.prepTime / Restaurant.speedMultiplier) > 1) {
			fi.prepState = FOOD_STATE_READY;
			switch(fi.type) {
				case FoodItem.TYPE_GRILL:
					MyGUI.GUI.signal(GRILL, String.format("The %s is boxed and ready to go.\n", fi.getName()));
					break;
				case FoodItem.TYPE_FRYER:
					MyGUI.GUI.signal(FRYER, String.format("With a dash of salt, the %s are ready to go.\n", fi.getName()));
					break;
				case FoodItem.TYPE_OVEN:
					MyGUI.GUI.signal(OVEN, String.format("The %s is wrapped up, ready to go.\n", fi.getName()));
					break;
				case FoodItem.TYPE_DRINK:
					MyGUI.GUI.signal(DRINK, String.format("Staff pop a lid and pluck a straw in the %s.\n", fi.getName()));
					break;
				case FoodItem.TYPE_SHAKE:
					MyGUI.GUI.signal(SHAKE, String.format("Staff pop a lid and dip a straw in the delicious %s.\n", fi.getName()));
					break;
			}
		}
		return fi;
	}
}