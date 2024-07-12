import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		// char[] charArr; // 선언
		// charArr = new char[3]; // 생성, 실제 메모리 할당

		char[] charArr = new char[3];

		// 지역변수는 초기화하지않으면 사용시 에러 발생
//		int a; 
//		System.out.println(a);

		// 객체는 자동 초기화됨 [0, 0, 0]
		int[] scores = new int[3];
		System.out.println(scores); // [I@2a139a55 / 타입@주소 (16진수)

		String[] names = new String[5];
		System.out.println(names);

		boolean isOk[] = new boolean[2];
		System.out.println(isOk);

		// 배열을 생성만
		// char은 빈값, int는 0, String은 null로, boolean은 false로 자동초기화
		
		// 배열 요소에 값 할당 
		scores[0] = 45;
		scores[1] = 23;
		scores[2] = 100;
		
		// runtime error
		// 실행시 컴파일러가 예외 발생시킴
		// 배열의 범위에서 벗어남
		// scores[3] = 50;
		
		// 배열 크기 변경
		// 다른 배열이 되어버림
		// 크기만 변경이 가능하고, 타입은 변경할 수 없음
		scores = new int[4];
		System.out.println(scores.hashCode());
		
		// 선언, 생성, 초기화 한번에
		int[] scores2 = new int[] { 10, 20, 30, 40, 50};
		
		// 배열 출력 방법
		System.out.println(Arrays.toString(scores2));
		
		int[] scores3 = new int[] { 60, 70, 80, 90, 100 };
		System.out.println(Arrays.toString(scores3));
		
		String[] heroes = {"아이언맨", "스파이더맨", "헐크"};
		// 배열 출력 방법 - 일반
		for(int i = 0; i < heroes.length; i++) {
			System.out.println(heroes[i]);
		}
		
		// 배열 출력 방법 - forEach문
		for(String hero : heroes) {
			System.out.println(hero);
		}
		
		// 2차원 배열의 선언과 생성
		int[][] arr = new int[3][4];
		
		int[][] arr1 = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9},
		};
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				System.out.print(arr1[r][c] + " ");
			}
			System.out.println();
		}
		
		// 난수
		double randNum = Math.random();
		System.out.println(randNum);
	}

}
