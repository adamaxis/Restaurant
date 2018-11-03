/**
 * @author Daniel Draper
 */


public class LinkedList {
	// private variables
	public Link first;

	// constructors
	/**
	 * 
	 * @param elems (Link) link to initialize LinkedList with
	 */
	public LinkedList(Link elem) {
		insertFirst(elem);
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
		if(isEmpty()) return 0;
		int size = 1;
		Link iterator = first;
		while(iterator.next != null) {
			iterator = iterator.next;
			size++;
		}
		return size;
	}
	
	/**
	 * 
	 * @param insert (Link) link to insert at beginning of linked list
	 */
	public void insertFirst(Link insert){
		insert.next = first;
		first = insert;
	}
	
	/**
	 * 
	 * @param insert (Link) link to insert at end of linked list
	 */
	public void insertLast(Link insert){
		if(isEmpty()) {
			first = insert;
			return;
		}
		Link iterator = first;
		while(iterator.next != null) iterator = iterator.next;

		iterator.next = insert;
	}
	
	/**
	 * 
	 * @return Deletes first link from linked list and returns reference to it
	 */
	public Link deleteFirst(){
		Link oldFirst = first;
		if(oldFirst != null) {
			first = first.next;
			return oldFirst;
		} else {
			first = null;
			return null;
		}
	}
	
	/**
	 * 
	 * @return Deletes last node from linked list and returns reference to it
	 */
	public Link deleteLast(){
		Link iterator = first;
		if(iterator.next != null) {
			Link lastIterator = null;
			while(iterator.next != null) {
				lastIterator = iterator;
				iterator = iterator.next;
			}
			lastIterator.next = null;
			iterator.next = null;
			return iterator;
		} else {
			first=null;
			return null;
		}
		
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
			toDisplay += "#" + count + ": " + iterator.getNode() + "\n";
			iterator = iterator.next;
			count++;
		}
		toDisplay += "Number of nodes: " + countNodes() + "\n";
		System.out.println(toDisplay);
	}
	
	// search() searches links - returns null when not found
	/**
	 * 
	 * @param toFind (String) Particular string to look for
	 * @return (Node) returns null when not found, otherwise returns Node reference
	 */
	public Link search(String toFind) {
		if(isEmpty()) return null;
		Link iterator = first;
		while(iterator != null) {
			if(iterator.getNode().toString().contains(toFind)) return iterator;
			iterator = iterator.next;
		}
		return null;
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
	
	// constructors
	/**
	 * 
	 * @param elem (Can) can to link to list
	 */
	public Link(FoodItem elem) {
		data = elem;
		next = null;
	}
	
	Link() {
		data = null;
		next= null;
	}
	
	public FoodItem getNode() {
		return data;
	}
}