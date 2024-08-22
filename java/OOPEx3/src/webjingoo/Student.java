package webjingoo;

public class Student {
	private String stuNo;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private float avg;
	private char grade;
	private static int totalTot = 0;
	
	public Student(Student student) {
		this.stuNo = student.stuNo;
		this.name = student.name;
		this.kor = student.kor;
		this.eng = student.eng;
		this.math = student.math;
		this.tot = student.tot;
		this.avg = student.avg;
		this.grade = student.grade;
	};
	
	public Student(String stuNo, String name, int kor, int eng, int math) {
		this.stuNo = stuNo;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		setTot();
		setAvg();
		setGrade();
	}

	// getter
	public String getStuNo() {
		return stuNo;
	}

	public String getName() {
		return name;
	}

	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMath() {
		return math;
	}

	public int getTot() {
		return tot;
	}

	public float getAvg() {
		return avg;
	}

	public char getGrade() {
		return grade;
	}

	// setter
	public void setKor(int kor) {
		this.kor = kor;
		setTot();
		setAvg();
		setGrade();
	}

	public void setEng(int eng) {
		this.eng = eng;
		setTot();
		setAvg();
		setGrade();
	}

	public void setMath(int math) {
		this.math = math;
		setTot();
		setAvg();
		setGrade();
	}

	public void setTot() {
		totalTot -= tot;
		tot = kor + eng + math;
		totalTot += tot;
	}

	public void setAvg() {
		avg = (float) (tot / 3.0);
	}

	public void setGrade() {
		switch ((int) Math.floor(avg / 10)) {
		case 10:
		case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7:
			grade = 'C';
			break;
		case 6:
			grade = 'D';
			break;
		default:
			grade = 'F';
		}
	}

	public void displayScore() {
		System.out.println("ㅡㅡㅡㅡ학생정보ㅡㅡㅡㅡ");
		System.out.println("학번: " + stuNo + ", kor: " + kor + ", eng: " + eng + ", math: " + math + ", tot: " + tot + ", avg: " + avg + ", grade: " + grade);
	}
	
	static int getTotalTot() {
		return totalTot;
	}

}
