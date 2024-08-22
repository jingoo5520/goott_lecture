
public class StuClass {
	private int no; // 반 번호
	private String className; // 과정 명
	private Student[] stuList = null;
	private int totalTot;
	private float totalAvg;
	private int stuCount;
	
	public static final int STUDENT_CONST = 3;
	
	public StuClass(int no, String className, Student[] stuList) {
		this.no = no;
		this.className = className;
		this.stuList = stuList;
	}
	
	public StuClass(int no, String className) {
		this(no, className, new Student[STUDENT_CONST]);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Student[] getStuList() {
		return stuList;
	}

	public void setStuList(Student[] stuList) {
		this.stuList = stuList;
	}

	public int getTotalTot() {
		return totalTot;
	}

	public void setTotalTot(int totalTot) {
		this.totalTot = totalTot;
	}

	public float getTotalAvg() {
		return totalAvg;
	}

	public void setTotalAvg(float totalAvg) {
		this.totalAvg = totalAvg;
	}
	
	public String toString() {
		return "반 번호: " + no + ", 과정 명: " + className;
	}
	
	public int getStuCount() {
		return stuCount;
	}
	
	public void insertStu(Student student) {
		if(stuCount != STUDENT_CONST) {
			stuList[stuCount++] = student;
			totalTot += student.getTot();
			totalAvg = totalTot / stuCount / 3f;
		} else {
			System.out.println("정원초과");
			return;
		}
	}
	
	
}
