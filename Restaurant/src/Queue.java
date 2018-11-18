/**
 * @author Daniel Draper
 */


public class Queue {
	// static private variables
	final private int QUEUE_SIZE=100;
	// private variables
	private Customer[] s; // queue
	private int sz; // size
	private int sp; // pos

	// constructors
	/**
	 * 
	 * @param elems (String...) list of items to initialize with queue
	 */
	public Queue(Customer elem) {
		s = new Customer[QUEUE_SIZE];
		sp=0;
		sz=0;
		enqueue(elem);
	}
	
	Queue() {
		s = new Customer[QUEUE_SIZE];
		sp=0;
		sz=0;
	}
	
	// enqueue()
	/**
	 * 
	 * @param c (String...) list of items to add to queue
	 */
	public void enqueue(Customer c) {
			if(isFull()) {
				System.out.println("Error: queue full! Extra element(s) discarded.");
				return;
			}
			int tracker=(sp+sz) % QUEUE_SIZE;
			s[tracker] = c;
			sz++;
	}
	
	
	// dequeue(num)
	/**
	 * 
	 * @param num (int) Number of items to dequeue
	 * @return (String) removes [num] number of items from the queue
	 */
	public Customer dequeue() {
		if(isEmpty()) {
			System.out.println("Error: queue empty! dequeue() operation failed!");
			return null;
		}
		int tracker=(sp) % QUEUE_SIZE;
		Customer dequeued = s[tracker];
		sp++;
		sz--;
		return dequeued;
	}
	
	// print() outputs total queue to user
	public void print() {
		if(isEmpty()) { 
			System.out.println("Error: queue empty! print() operation failed!");
			return;
		}
		String toPrint = new String("Contents:");
		int tracker;
		for(int i = 0; i < sz; i++) {
			tracker=(sp+i) % QUEUE_SIZE;
			if(s[tracker] != null) toPrint += " " + s[tracker];
			else break;
		}
		System.out.println(toPrint);
	}
	
	// peek() outputs first item in queue
	public Customer peek() {
		if(isEmpty()) return null;
		else return s[sp];
	}
	
	// peek() outputs first item in queue
	public Customer peek(int custNumber) {
		if(isEmpty() || size() < custNumber) return null;
		else return s[sp+custNumber % QUEUE_SIZE];
	}
	
	// size()
	/**
	 * 
	 * @return (int) returns real size of queue
	 */
	public int size() {
		return sz;
	}
	
	// isFull()
	/**
	 * 
	 * @return (boolean) returns true when queue is full, otherwise false
	 */
	public boolean isFull() {
		if(sz == QUEUE_SIZE) return true;
		return false;
	}

	// isEmpty()
	/**
	 * 
	 * @return (boolean) returns true when queue is empty, otherwise false
	 */
	public boolean isEmpty() {
		if(sz == 0) return true;
		return false;
	}
	
}
