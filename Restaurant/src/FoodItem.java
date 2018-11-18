
public class FoodItem {
	String name;
	double timeToPrepare;
	int type=0;
	int prepState=0;
	final int TYPE_DRINK = 1;
	final int TYPE_BURGER = 2;
	final int TYPE_SHAKE = 3;
	final int TYPE_PIE = 4;
	final int TYPE_MEAL = 5;
	double cost;
	
	FoodItem(String name, double timeToPrepare, double cost) {
		this.name = name;
		this.timeToPrepare = timeToPrepare;
		this.cost = cost;
	}

	FoodItem() {
		this.name = "";
		this.timeToPrepare = 0;
		this.cost = 0;
	}
	
	public String getName() {
		return name;
	}

}
