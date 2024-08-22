
public class ScoreV2Main {

	public static void main(String[] args) {
		Student s1 = new Student("24001", "홍길동", 90, 96, 85);
		s1.displayScore();

		Student s2 = new Student("24002", "홍길서", 90, 69, 99);
		s2.displayScore();

		System.out.println("전체 총점: " + Student.getTotalTot());
		
		s1.setKor(92);
		
		s1.displayScore();
		System.out.println("전체 총점: " + Student.getTotalTot());
	}
}
