public class FoodItem {
	String name;
	int type=0;
	int prepState;
	long prepTime;
	long startTime;
	final static int TYPE_GRILL = 1;
	final static int TYPE_FRYER = 2;
	final static int TYPE_DRINK = 3;
	final static int TYPE_SHAKE = 4;
	final static int TYPE_OVEN = 5;
	final static int TYPE_MEAL = 6;
	double cost;
	
	FoodItem(String name, long prepTime, double cost, int type) {
		this.prepState = Service.FOOD_STATE_FRESH;
		this.name = name;
		this.prepTime = prepTime;
		this.cost = cost;
		this.type = type;
		this.startTime = 0;
	}

	FoodItem() {
		this.name = "";
		this.prepTime = 0;
		this.cost = 0;
	}
	
	public String getName() {
		return name;
	}

}
