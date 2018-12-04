import java.util.Date;

public class Service {
	
	final static int GRILL = 1;
	final static int FRYER = 2;
	final static int DRINK = 3;
	final static int SHAKE = 4;
	final static int CASHIER = 5;
	final static int MISC = 6;
	final static int FOOD_STATE_FRESH = 0;
	final static int FOOD_STATE_0 = 1;
	final static int FOOD_STATE_25 = 2;
	final static int FOOD_STATE_50 = 3;
	final static int FOOD_STATE_75 = 4;
	final static int FOOD_STATE_READY = 5;
	final static int FOOD_STATE_SERVED = 6;
	
	int atGrill;
	int atFryer;
	int atDrink;
	int atShake;
	int atCashier;
	int atMisc;
	int numEmployees;
	Employee[] empList;
	
	public Service() {
		//Random rng = new Random();
		numEmployees = 6;//rng.nextInt(3) + 4;
		atGrill=0;
		atFryer=0;
		atDrink=0;
		atShake=0;
		atCashier=0;
		atMisc=0;
		empList = new Employee[numEmployees];
		for(int i=0; i < numEmployees; i++) {
			empList[i] = new Employee(i+1);
		}
	}
	
	void assignJobs() {
		for(int i = 0; i < numEmployees; i++) {
			if(atCashier==0) {
				empList[i].job = CASHIER;
				atCashier=1;
				System.out.printf("Employee#%d moves to the cashier.\n", empList[i].num);
			}
			else if(atGrill==0) {
				empList[i].job = GRILL;
				atGrill=1;
				System.out.printf("Employee#%d moves to the grill.\n", empList[i].num);
			}
			else if(atFryer==0) {
				empList[i].job = FRYER;
				atFryer=1;
				System.out.printf("Employee#%d moves to the fryer.\n", empList[i].num);
			}
			else if(atDrink==0) {
				empList[i].job = DRINK;
				atDrink=1;
				System.out.printf("Employee#%d moves to the drink machine.\n", empList[i].num);
			}
			else if(atShake==0) {
				empList[i].job = SHAKE;
				atShake=1;
				System.out.printf("Employee#%d moves to the shake machine.\n", empList[i].num);
			}
			else if(atMisc==0) {
				empList[i].job = MISC;
				atMisc=1;
				System.out.printf("Employee#%d moves to cleanup.\n", empList[i].num);
			}
		}
	}
	
	LinkedList doJobs(LinkedList orderList) {
		Link iteratorCustomer = orderList.first;
		Customer c;
		while(iteratorCustomer != null) {
			c = (Customer) iteratorCustomer.data;
			if(c != null) {
				Link iterator = c.order.first;
				FoodItem fi;
				boolean orderComplete=true;
				while(iterator != null) {
					fi = (FoodItem) iterator.data;
					// order checking
					Date now = new Date();
					long time = (now.getTime() / 1000) - fi.startTime;
					switch(fi.prepState) {
						case FOOD_STATE_FRESH:
							fi.prepState = FOOD_STATE_0;
							fi.startTime = now.getTime() / 1000;
							switch(fi.type) {
								case FoodItem.TYPE_GRILL:
									System.out.println("Grill state 0.");
									break;
								case FoodItem.TYPE_FRYER:
									System.out.println("Fryer state 0.");
									break;
								case FoodItem.TYPE_OVEN:
									System.out.println("Oven state 0.");
									break;
								case FoodItem.TYPE_DRINK:
									System.out.println("Drink state 0.");
									break;
								case FoodItem.TYPE_SHAKE:
									System.out.println("Shake state 0.");
									break;
							}
							orderComplete = false;
							break;
					
						case FOOD_STATE_0:
							if(time > ((fi.prepTime / 4) / Restauraunt.speedMultiplier)) {
								fi.prepState = FOOD_STATE_25;
								switch(fi.type) {
									case FoodItem.TYPE_GRILL:
										System.out.println("Grill state 25.");
										break;
									case FoodItem.TYPE_FRYER:
										System.out.println("Fryer state 25.");
										break;
									case FoodItem.TYPE_OVEN:
										System.out.println("Oven state 25.");
										break;
									case FoodItem.TYPE_DRINK:
										System.out.println("Drink state 25.");
										break;
									case FoodItem.TYPE_SHAKE:
										System.out.println("Shake state 25.");
										break;
								}
							}
							orderComplete = false;
							break;
						
						case FOOD_STATE_25:
							if(time > ((fi.prepTime / 2) / Restauraunt.speedMultiplier)) {
								fi.prepState = FOOD_STATE_50;
								switch(fi.type) {
									case FoodItem.TYPE_GRILL:
										System.out.println("Grill state 50.");
										break;
									case FoodItem.TYPE_FRYER:
										System.out.println("Fryer state 50.");
										break;
									case FoodItem.TYPE_OVEN:
										System.out.println("Oven state 50.");
										break;
									case FoodItem.TYPE_DRINK:
										System.out.println("Drink state 50.");
										break;
									case FoodItem.TYPE_SHAKE:
										System.out.println("Shake state 50.");
										break;
								}
							}
							orderComplete = false;
							break;
						
						case FOOD_STATE_50:
							if(time > ((fi.prepTime / 4 * 3) / Restauraunt.speedMultiplier)) {
								fi.prepState = FOOD_STATE_75;
								switch(fi.type) {
									case FoodItem.TYPE_GRILL:
										System.out.println("Grill state 75.");
										break;
									case FoodItem.TYPE_FRYER:
										System.out.println("Fryer state 75.");
										break;
									case FoodItem.TYPE_OVEN:
										System.out.println("Oven state 75.");
										break;
									case FoodItem.TYPE_DRINK:
										System.out.println("Drink state 75.");
										break;
									case FoodItem.TYPE_SHAKE:
										System.out.println("Shake state 75.");
										break;
								}
							}
							orderComplete = false;
							break;
						
						case FOOD_STATE_75:
							if(time > (fi.prepTime / Restauraunt.speedMultiplier)) {
								fi.prepState = FOOD_STATE_READY;
								switch(fi.type) {
									case FoodItem.TYPE_GRILL:
										System.out.println("Grill state ready.");
										break;
									case FoodItem.TYPE_FRYER:
										System.out.println("Fryer state ready.");
										break;
									case FoodItem.TYPE_OVEN:
										System.out.println("Oven state ready.");
										break;
									case FoodItem.TYPE_DRINK:
										System.out.println("Drink state ready.");
										break;
									case FoodItem.TYPE_SHAKE:
										System.out.println("Shake state ready.");
										break;
								}
							}
							orderComplete = false;
							break;
						
						case FOOD_STATE_READY:
							switch(fi.type) {
							case FoodItem.TYPE_GRILL:

								break;
							case FoodItem.TYPE_FRYER:

								break;
							case FoodItem.TYPE_OVEN:

								break;
							case FoodItem.TYPE_DRINK:
								System.out.println("A staffer brings the drink out and sets it on the order plate");
								fi.prepState = FOOD_STATE_SERVED;
								break;
							case FoodItem.TYPE_SHAKE:
								System.out.println("A staffer brings the shake out and sets it on the order plate");
								fi.prepState = FOOD_STATE_SERVED;
								break;
						}
							break;
						
						case FOOD_STATE_SERVED:
							
							break;
					}
					iterator = iterator.next;
				}
				if(orderComplete == true) {
					// send out for delivery
					System.out.printf("One of the staff goes into the kitchen and comes out with order #%d.\n", c.orderNumber);
					c.orderDone = true;
				}
			}
			iteratorCustomer = iteratorCustomer.next;				
		}
		return orderList;
	}
}