import java.util.Scanner;

public class ArrayEx4 {

	public static void main(String[] args) {
		// 학생 4명의 국어, 영어, 수학 점수를 받아 2차원 배열에 저장하고 출력
		// 총점, 평균도 함께 출력
		Scanner sc = new Scanner(System.in);

		int stuNum = 1;

		String name;
		int kor;
		int eng;
		int math;
		int total;
		double avg;
		String[] stuInfo = new String[5];
		
		String[][] arr = new String[stuNum][6];

		
		// nextLine()은 줄바꿈 문자 '\n'를 읽기 전까지의 데이터를 입력받음
		// nextInt()는 정수만 읽고 + \n 을 남김
		// nextInt -> nextLine 실행시, nextInt가 남겨놓은 \n로 인해 nextLine이 스킵됨
		
		for (int i = 0; i < stuNum; i++) {
			
			System.out.print("이름: ");
			name = sc.nextLine();
			
			
			System.out.print("국어: ");
			kor = sc.nextInt(); 
			sc.nextLine();

			System.out.print("영어: ");
			eng = sc.nextInt();
			sc.nextLine();

			System.out.print("수학: ");
			math = sc.nextInt();
			sc.nextLine();
			
			total = kor + eng + math;
			avg = total / 3.0;
			
			arr[i][0] = name;
			arr[i][1] = Integer.toString(kor);
			arr[i][2] = Integer.toString(eng);
			arr[i][3] = Integer.toString(math);
			arr[i][4] = Integer.toString(total);
			arr[i][5] = Double.toString(avg);	
		}
		
		// 출력
		System.out.println("이 름\t국어\t영어\t수학\t총점\t평균\t");
		System.out.println("--------------------------------------------------");
		for(int i = 0; i < stuNum; i ++) {
			for(int j = 0; j < arr[i].length; j ++) {
				System.out.print(arr[i][j] + " \t");
			}
			System.out.println();
		}
	}

}
