import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx2 {

	public static void main(String[] args) {
		// 유저에게 n개의 정수를 입력받아 배열에 저장한 후,
		// 최소값, 최대값을 찾아서 출력하는 프로그램 작성
		
		Scanner sc = new Scanner(System.in);
		
		String number;
		int[] numbers = new int[100];
		int count = 0;
		
		System.out.println("정수를 입력하세요. (stop 입력시 입력 중단)");
		while(true) {
			
			number = sc.nextLine();
		
			if(number.equals("stop")) {
				break;
			}
			
			numbers[count] = Integer.parseInt(number);
			count++;
		}
		
		// 배열 출력
		for(int i = 0; i < count; i++) {
			System.out.println(numbers[i]);
		}
		
		int max = numbers[0];
		int min = numbers[0];
		
		for(int i = 1; i < count; i++) {
			if(max < numbers[i]) {
				max = numbers[i];
			}
			
			if(min > numbers[i]) {
				min = numbers[i];
			}
		}
		
		System.out.println("최대값: " + max);
		System.out.println("최소값: " + min);

	}

}
