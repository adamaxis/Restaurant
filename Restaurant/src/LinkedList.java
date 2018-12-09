/**
 * 
 * @author The Dude
 *
 */

public class LinkedList {
	// private variables
	private Link first;

	// constructors
	/**
	 * 
	 * @param elems (Link) link to initialize LinkedList with
	 */
	public LinkedList(Link elem) {
		insert(elem);
	}
	
	LinkedList() {
		first=null;
	}
	
	
	
	public Link getFirst() {
		return first;
	}

	public void setFirst(Link first) {
		this.first = first;
	}

	// countNodes()
	/**
	 * 
	 * @return (Int) returns number of links on linked list
	 */
	public int countNodes() {
		if(isEmpty()) return 0;
		
		Link iterator = first;
		int i=1;
		while(iterator.next != null) {
			i++;
			iterator = iterator.next;
		}
		return i;
	}
	
	/**
	 * 
	 * @param insert (Link) link to insert
	 */
	public boolean insert(Link insert){
		if(isEmpty()) {
			insert.prev = null;
			insert.next = null;
			first = insert;
			return true;
		}
		Link iterator = first;
		while(iterator.next != null) {
			iterator = iterator.next;
		}
		iterator.next = insert;
		insert.prev = iterator;
		insert.next = null;
		return true;
	}
	

	/**
	 * 
	 * @param key (String) Key to delete
	 * @return (boolean) true if successful, false if key not found
	 */
	public boolean delete(String key){
		Link oldLink = search(key);
		if(oldLink == null || isEmpty()) {
			System.out.println("Error during deletion: Key '" + key + "' not found.");
			return false;
		}
		Link oldPrev = oldLink.prev;
		Link oldNext = oldLink.next;
		
		if(oldLink == first) {	// step forward
			first = first.next;
			if(first != null) first.prev = null;
			return true;
		} else {
			if(oldPrev != null) oldPrev.next = oldNext;
			if(oldNext != null) oldNext.prev = oldPrev;
		}
		return true;
	}
	
	/**
	 * 
	 * @param key (String) Key to delete
	 * @return (boolean) true if successful, false if key not found
	 */
	public boolean delete(Link key){
		Link oldLink = this.first;
		while(oldLink != null) {
			if(oldLink == key) break;
			oldLink = oldLink.next;
		}
		if(oldLink==null || key==null || isEmpty()) {
			System.out.println("Error during deletion: Key '" + key + "' not found.");
			return false;
		}
		
		Link oldPrev = oldLink.prev;
		Link oldNext = oldLink.next;
		if(oldLink == first) {	// step forward
			first = first.next;
			if(first != null) first.prev = null;
			return true;
		} else {
			if(oldPrev != null) oldPrev.next = oldNext;
			if(oldNext != null) oldNext.prev = oldPrev;
		}
		oldLink.setData(null);
		oldLink = null;
		return true;
	}
	
	
	// displayList() returns a string of object information via toString()
	/**
	 * 
	 * @return (String) list of items via toString()
	 */
	public String printList() {
		if(isEmpty()) {
			System.out.println("Linked list is empty.");
			return "";
		}
		Link iterator = first;
		String toDisplay = new String();
		int count=1;
		while(iterator != null) {
			if(iterator.getData() instanceof FoodItem) toDisplay += "#" + count + ": " + ((FoodItem) iterator.getData()).getName() + "\n";
			else if(iterator.getData() instanceof Customer) toDisplay += "#" + count + ": " + ((Customer) iterator.getData()) + "\n";
			iterator = iterator.next;
			count++;
		}
		return toDisplay;
	}
	
	// displayList() - displays LinkedList contents to user
	public void displayList() {
		System.out.println(printList());
	}
	
	// search() searches links - returns null when not found
	/**
	 * 
	 * @param toFind (String) Particular key to look for
	 * @return (Link) returns null when not found, otherwise returns Link reference
	 */
	public Link search(String toFind) {
		if(isEmpty()) return null;
		Link iterator = first;
		while(iterator != null) {
			if(iterator.getData() instanceof FoodItem) if(((FoodItem)iterator.getData()).getName().equalsIgnoreCase(toFind)) return iterator;
			else if(iterator.getData() instanceof Customer) System.out.println("Search not implemented for Customers");
			iterator = iterator.next;
		}
		return null;
	}
	
	/**
	 * 
	 * @param itemNum (int) number to return in list
	 * @return (Link) link of item referenced by number
	 */
	public Link getItem(int itemNum) {
		if(isEmpty() || (countNodes() < itemNum)) {
			System.out.println("Null");
			return null;
		}
		Link iterator = first;
		for(int i=1; i < countNodes(); i++) {
			iterator = iterator.next;
			if(iterator == null || i == itemNum) break;
		}
		return iterator;
	}
	

	// isEmpty()
	/**
	 * 
	 * @return (boolean) returns true when list is empty, otherwise false
	 */
	public boolean isEmpty() {
		return(first==null);
	}
}

class Link {
	// link data
	private Object data;
	public Link next;
	public Link prev;
	
	// constructors
	/**
	 * 
	 * @param data of Link
	 */
	public Link(Object data) {
		this.data = data;
		next = null;
		prev = null;
	}
	
	Link() {
		this.data = null;
		next= null;
		prev = null;
	}
	
	// copy data from link to link
	/**
	 * 
	 * @param l (Link) link to copy data from
	 */
	Link(Link copy) {
		this.data = copy.data;
		next= null;
		prev = null;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		if(data !=null) {
			if(data instanceof FoodItem) return ((FoodItem) data).name;
			else if(data instanceof Customer) return data.toString();
		} return null;
	}
	
}