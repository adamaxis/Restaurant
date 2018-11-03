
public class FoodItem extends Link {
	String name;
	double timeToPrepare;
	double cost;
	
	FoodItem(String name, double timeToPrepare, double cost) {
		this.name = name;
		this.timeToPrepare = timeToPrepare;
		this.cost = cost;
		this.data = this;
	}

	FoodItem() {
		this.name = "";
		this.timeToPrepare = 0;
		this.cost = 0;
	}

}
