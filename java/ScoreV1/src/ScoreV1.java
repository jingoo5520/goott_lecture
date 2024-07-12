
public class ScoreV1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 성적표 자바버전
		// 국어, 영어, 수학, 총점, 평균, 학점을 계산해서 출력하라
		String name = "홍길동";
		int kor = 80;
		int eng = 75;
		int math = 98;

		int total = kor + eng + math;

		double avg = Math.round(total / 3.0 * 100) / 100.0;

		char grade;

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
		
		
		System.out.println("총점: " + total);
		System.out.println("평균: " + avg);
		System.out.println("학점: " + grade);
		

	}

}
