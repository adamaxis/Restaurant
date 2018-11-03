
public class Menu extends LinkedList{
	static final int MENU_ITEMS = 10;
	
	boolean insertMenuItem(FoodItem fi) {
		if(fi == null) return false;
		this.insertFirst(fi);
		return true;
	}
	
	
	FoodItem menuToFoodItem(int itemNum) {
		// bounds check
		if(itemNum > this.countNodes()) return null;
		// iterate to menu #
		Link iterator = this.first;
		for(int i = 1; i < itemNum; i++) iterator = iterator.next;
		// return FoodItem
		return iterator.data;
	}
	
}
