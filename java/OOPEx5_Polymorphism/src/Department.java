
public class Department {
	private Employee[] emps;
	private int count;

	public Department() {
		count = 0;
		emps = new Employee[10];
	}

	void addEmp(Employee employee) {
		if (count < 10) {
			emps[count] = employee;
			count++;
		} else {
			System.out.println("초과");
		}
	}

	void display() {
		for (int i = 0; i < count; i++) {
			System.out.println(emps[i]);
		}
	}
}
