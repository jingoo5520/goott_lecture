import java.util.Scanner;

public class TotalOfNumbers {

	public static void main(String[] args) {
		// 유저에게 숫자를 입력받아 1부터 입력된 숫자까지의 총합 출력
		Scanner sc = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요: ");
		int number = sc.nextInt();
		
		int n = 1;
		int sum = 0;
		while(n < number) {
			sum += n;
			
			n++;
		}
		
		System.out.println("1부터 " + number + "까지의 합은 " + sum + "입니다.");
		

	}

}
