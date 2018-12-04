
public class Menu extends LinkedList {
	static final int MENU_ITEMS = 10;
	int itemTotal = 0;
	
	boolean insertMenuItem(FoodItem fi) {
		if(fi == null) return false;
		Link l = new Link(fi);
		this.insert(l);
		itemTotal++;
		return true;
	}
	
	
	FoodItem menuToFoodItem(int itemNum) {
		// need to make a copy
		if(isEmpty()) return null;
		
		return (FoodItem) getItem(itemNum).data;
	}
	
}
