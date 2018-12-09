/**
 * Queue - data structure used for customer line
 * @author The Dude
 *
 */
public class Queue {
	// static private members
	final private int QUEUE_SIZE=100;
	
	// private members
	private Customer[] s; // queue
	private int sz; // size
	private int sp; // pos

	/**
	 * constructors
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
	
	/**
	 * enqueue() - adds customer to end of queue
	 * @param c (Customer) to add to queue
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
	
	
	/**
	 * dequeue() - removes first element from queue
	 * @return (Customer) reference to removed element
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
	
	/**
	 * print() outputs total queue to user
	 */
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
	
	/**
	 * peek() outputs first item in queue
	 * @return (Customer) first item in queue
	 */
	public Customer peek() {
		if(isEmpty()) return null;
		else return s[sp % QUEUE_SIZE];
	}
	

	/**
	 * size()
	 * @return (int) returns real size of queue
	 */
	public int size() {
		return sz;
	}
	
	/**
	 * isFull()
	 * @return (boolean) returns true when queue is full, otherwise false
	 */
	public boolean isFull() {
		if(sz == QUEUE_SIZE) return true;
		return false;
	}

	/**
	 * isEmpty()
	 * @return (boolean) returns true when queue is empty, otherwise false
	 */
	public boolean isEmpty() {
		if(sz == 0) return true;
		return false;
	}
	
}
