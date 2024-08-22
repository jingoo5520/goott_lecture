package modifier;

import java.util.Arrays;

public class FinalMain {

	public static void main(String[] args) {
		ConstructInit ci1 = new ConstructInit(10);
		// 재할당 불가
		// ci1.value = 20;
		
		FieldInit fi1 = new FieldInit();
		System.out.println(fi1.value);

		
		
		
		int[] intArr; // 선언
		
		intArr = new int[5]; // 생성
		// int 배열의 초기화 없이 생성시 경우 모든 원소가 0으로 자동 초기화
		System.out.println(Arrays.toString(intArr)); // [0, 0, 0, 0, 0]
		
		
		char[] charArr = new char[5]; // 선언 및 생성
		// char 배열의 초기화 없이 생성시 경우 모든 원소가 '\u0000'(유니코드의 null문자)로 자동 초기화
		System.out.println(Arrays.toString(charArr)); // [ , , , , ]
		
		
		
	}

}
