
public class RegularEmp extends Employee{
	
	public RegularEmp (int empNo, String empName, String department, int type,int salary) {
		super(empNo, empName, department, type);
		
		super.setSalary(salary);
	}
	
	
	
}
