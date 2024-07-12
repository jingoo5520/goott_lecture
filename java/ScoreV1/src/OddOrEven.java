import java.util.Scanner;

public class OddOrEven {

	public static void main(String[] args) {
		// 유저에게 숫자를 입력받아, 짝수인지 홀수인지 출력하는 프로그램
		Scanner sc = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요: ");
		int number = sc.nextInt();
		
		String result;
		
		result = number % 2 == 0 ? "짝수" : "홀수";
		
		System.out.println(number + "는 " + result + "입니다.");
		
		
	}

}
