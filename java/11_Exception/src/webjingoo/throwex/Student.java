package webjingoo.throwex;

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

	// 예외를 떠넘김
	public Student(String stuNo, String name, int kor, int eng, int math) throws IllegalArgumentException {
		this.stuNo = stuNo;
		this.name = name;

		if (kor >= 0 && kor <= 100) {
			this.kor = kor;
		} else {
			throw new IllegalArgumentException("잘못된 데이터입니다.(생성자)");
		}

		this.eng = eng;
		this.math = math;
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
		setGrade();
	}

	public void setEng(int eng) {
		this.eng = eng;
		setGrade();
	}

	public void setMath(int math) {
		this.math = math;
		setGrade();
	}

	public void setGrade() {
		totalTot -= tot;
		tot = kor + eng + math;
		totalTot += tot;

		avg = (float) (tot / 3.0);

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

	public String toString() {
		return "stuNo: " + stuNo + ", name: " + name + ", kor: " + kor + ", eng: " + eng + ", math: " + math + ", tot: "
				+ tot + ", avg: " + avg + ", grade: " + grade;
	}

	static int getTotalTot() {
		return totalTot;
	}
}
