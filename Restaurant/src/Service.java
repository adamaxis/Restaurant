public class Service {
	
	final int GRILL = 1;
	final int FRYER = 2;
	final int DRINK = 3;
	final int SHAKE = 4;
	final int CASHIER = 5;
	final int MISC = 6;
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
	
	Queue doJobs(Queue orderLine) {
		for(int i=0; i < orderLine.size(); i++) {
			Customer c = orderLine.peek(i);
			if(c != null) {
				Link iterator = c.order.first;
				FoodItem fi;
				while(iterator != null) {
					fi = iterator.data;
					// order checking
					
					iterator = iterator.next;
				}
			}
		}
		return orderLine;
	}
}