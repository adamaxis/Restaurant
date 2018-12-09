/**
 * Employee - handles employees and some basic things
 * @author The Dude
 *
 */
public class Employee {
	// private members
	private int num;
	private int job;
	
	// constructors
	/**
	 * 
	 * @param num (int) Number assigned to employee
	 * @param job (int) Job employee should do
	 */
	Employee(int num, int job) {
		this.num = num;
		this.job = job;
	}
	
	Employee(int num) {
		this.num = num;
		this.job = 0;
	}
	
	Employee() {
		this.num = 0;
		this.job = 0;
	}

	// getters and setters
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}
	
	
}
