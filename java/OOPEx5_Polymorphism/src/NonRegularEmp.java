
public class NonRegularEmp extends Employee{
	private int times; // 근무 시간
	private int hourlyRate; // 시급
	
	public NonRegularEmp (int empNo, String empName, String department, int type, int times, int hourlyRate) {
		super(empNo, empName, department, type);
		this.times = times;
		this.hourlyRate = hourlyRate;
		setSalary(times * hourlyRate * 30);
	}
	
	@Override
	void setSalary(int salary) {
		super.setSalary(salary);
	}	
}
