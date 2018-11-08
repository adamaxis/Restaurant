
public class Menu extends LinkedList{
	static final int MENU_ITEMS = 10;
	int itemTotal = 0;
	
	boolean insertMenuItem(FoodItem fi) {
		if(fi == null) return false;
		this.insertFirst(fi);
		itemTotal++;
		return true;
	}
	
	
	FoodItem menuToFoodItem(int itemNum) {
		if(first == null) return null;
		// iterate to menu #
		Link iterator = first;
		int ic=1;
		while(iterator.next != null) {
			ic++;
			iterator = iterator.next;
			if(ic == itemNum) break;
		}
		System.out.println("ic=" + ic);
		if(ic == itemNum) return (FoodItem) iterator.data;
		return null;
	}
	
}
