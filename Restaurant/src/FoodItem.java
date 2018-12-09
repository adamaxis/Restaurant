/**
 * FoodItem - handles different types of foods found on the the menu
 * and in orders and in the kitchen
 * @author The Dude
 *
 */
public class FoodItem {
	// members
	String name;
	String frozenName;
	int type=0;
	int prepState;
	long prepTime;
	long startTime;
	double cost;
	
	// static members
	final static int TYPE_GRILL = 1;
	final static int TYPE_FRYER = 2;
	final static int TYPE_DRINK = 3;
	final static int TYPE_SHAKE = 4;
	final static int TYPE_OVEN = 5;
	final static int TYPE_MEAL = 6;
	
	// constructors
	FoodItem(String name, long prepTime, double cost, int type, String frozenName) {
		this.prepState = Service.FOOD_STATE_FRESH;
		this.name = name;
		this.frozenName = frozenName;
		this.prepTime = prepTime;
		this.cost = cost;
		this.type = type;
		this.startTime = 0;
	}

	FoodItem(FoodItem copy) {
		this.prepState = copy.prepState;
		this.name = copy.name;
		this.frozenName = copy.frozenName;
		this.prepTime = copy.prepTime;
		this.cost = copy.cost;
		this.type = copy.type;
		this.startTime = copy.startTime;
	}
	
	FoodItem() {
		this.prepState = 0;
		this.name = "";
		this.frozenName = "";
		this.prepTime = 0;
		this.cost = 0;
		this.type = 0;
		this.startTime = 0;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrozenName() {
		return frozenName;
	}

	public void setFrozenName(String frozenName) {
		this.frozenName = frozenName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPrepState() {
		return prepState;
	}

	public void setPrepState(int prepState) {
		this.prepState = prepState;
	}

	public long getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(long prepTime) {
		this.prepTime = prepTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
