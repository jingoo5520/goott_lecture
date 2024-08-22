
public class Employee {
	private int empNo;
	private String empName;
	private String department;
	private int type;
	private int salary;

	public Employee(int empNo, String empName, String department, int type) {
		this.empNo = empNo;
		this.empName = empName;
		this.department = department;
		this.type = type;
	}

	public int getSalary() {
		return salary;
	}
	
	void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "사번: " + empNo + ", 이름: " + empName + ", 급여: " + salary;
	}
}
