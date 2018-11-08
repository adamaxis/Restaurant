public class Employee {
	int num;
	int job;
	
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
}
