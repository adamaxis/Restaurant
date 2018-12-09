// Menu - handles the restaurant's menu items and
// all food conversions
/**
 * 
 * @author The Dude
 *
 */
public class Menu extends LinkedList {
	// static members
	static final int MENU_ITEMS = 10;
	// members
	private int itemTotal = 0;
	
	public int getItemTotal() {
		return itemTotal;
	}

	/**
	 * 
	 * @param fi (FoodItem) food to insert
	 * @return (boolean) true if successful
	 */
	boolean insertMenuItem(FoodItem fi) {
		if(fi == null) return false;
		Link l = new Link(fi);
		this.insert(l);
		itemTotal++;
		return true;
	}
	
	// menuToFoodItem(itemNum) - returns a copy of the foodItem from the menu
	/**
	 * 
	 * @param (int) itemNum # on menu to return
	 * @return (FoodItem) # referenced by ItemNum
	 */
	FoodItem menuToFoodItem(int itemNum) {
		// null check
		if(isEmpty()) return null;
		// fix for problem mentioned in final report
		FoodItem fi = new FoodItem((FoodItem) getItem(itemNum).getData());
		return fi;
	}
	
	// buildMenu() - builds restaurant menu and fills it with food
	/**
	 * 
	 * @return (Menu) built restaurant menu
	 */
	static Menu buildMenu() {
		Menu menu = new Menu();
		FoodItem fi = new FoodItem("Hamburger", 120, 1.00, FoodItem.TYPE_GRILL, "hamburger patty");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Double Cheeseburger", 150, 2.50, FoodItem.TYPE_GRILL, "hamburger patty");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chicken Sandwich", 120, 2.00, FoodItem.TYPE_OVEN, "chicken patty");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Drink", 60, 1.50, FoodItem.TYPE_DRINK, "large drink cup");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Drink", 60, 1.25, FoodItem.TYPE_DRINK, "medium drink cup");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Drink", 60, 1.00, FoodItem.TYPE_DRINK, "small drink cup");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Chocolate Shake", 60, 3.99, FoodItem.TYPE_SHAKE, "chocolate frosty cup");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Vanilla Shake", 60, 3.99, FoodItem.TYPE_SHAKE, "vanilla frosty cup");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Large Fries", 100, 1.75, FoodItem.TYPE_FRYER, "fries");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Medium Fries", 100, 1.50, FoodItem.TYPE_FRYER, "fries");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Small Fries", 100, 1.00, FoodItem.TYPE_FRYER, "fries");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Value Meal", 150, 4.99, FoodItem.TYPE_MEAL, "meal box");
		menu.insertMenuItem(fi);
		fi = new FoodItem("X-tra Meal", 150, 6.99, FoodItem.TYPE_MEAL, "meal box");
		menu.insertMenuItem(fi);
		fi = new FoodItem("Apple Pie", 60, 1.50, FoodItem.TYPE_OVEN, "apple pie");
		menu.insertMenuItem(fi);
		return menu;
	}
}
