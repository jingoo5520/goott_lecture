package webjingoo.treesetex2;

public class Student implements Comparable<Student> {
	private String stuNo;
	private String name;
	private int score;
	
	public Student(String stuNo, String name, int score) {
		super();
		this.stuNo = stuNo;
		this.name = name;
		this.score = score;
	}
	
	public String getStuNo() {
		return this.stuNo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		return "\n" + "User [stuNo=" + stuNo + ", name=" + name + ", score=" + score + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Student stu = (Student) obj;
		
		if(this.stuNo == stu.getStuNo()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (this.stuNo).hashCode(); 
	}

	@Override
	public int compareTo(Student o) {
		// 기본 정렬 방법: 학번 오름 차순
		return this.stuNo.compareTo(o.getStuNo());
	}
}
