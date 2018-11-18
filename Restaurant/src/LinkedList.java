/**
 * @author Daniel Draper
 */

public class LinkedList {
	// private variables
	public Link first;
	int size=0;

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
	
	// countNodes()
	/**
	 * 
	 * @return (Int) returns number of links on linked list
	 */
	public int countNodes() {
		return size;
	}
	
	/**
	 * 
	 * @param insert (Link) link to insert
	 */
	public boolean insert(Link insert){
		if(isEmpty()) {
			first = insert;
			System.out.println("Link '" + insert.data.getName() + "'inserted successfully");
			size++;
			return true;
		}
		Link iterator = first;
		while(iterator.next != null) {
			iterator = iterator.next;
		}
		iterator.next = insert;
		System.out.println("Link '" + insert.data.getName() + "'inserted successfully");
		size++;
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
		System.out.println("Key '" + oldLink.getData().getName() + "' deleted");
		
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
	

	
	
	// displayList() outputs object information to user via toString()
	public void displayList() {
		if(isEmpty()) {
			System.out.println("Linked list is empty.");
			return;
		}
		Link iterator = first;
		String toDisplay = new String();
		int count=1;
		while(iterator != null) {
			toDisplay += "#" + count + ": " + iterator.getData().getName() + "\n";
			iterator = iterator.next;
			count++;
		}
		toDisplay += "Number of nodes: " + countNodes() + "\n";
		System.out.println(toDisplay);
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
			if(iterator.getData().getName().equalsIgnoreCase(toFind)) return iterator;
			iterator = iterator.next;
		}
		return null;
	}
	
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
	public FoodItem data;
	public Link next;
	public Link prev;
	
	// constructors
	/**
	 * 
	 * @param elem to link to list
	 */
	public Link(FoodItem data) {
		this.data = data;
		next = null;
		prev = null;
	}
	
	Link() {
		this.data = null;
		next= null;
		prev = null;
	}
	
	
	Link(Link l) {
		this.data = l.data;
		next= null;
		prev = null;
	}
	
	public FoodItem getData() {
		return data;
	}
	
	@Override
	public String toString() {
		if(data !=null) {
			return data.name;
		} return null;
	}
	
}