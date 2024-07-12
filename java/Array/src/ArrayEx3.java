import java.util.Scanner;

public class ArrayEx3 {

	public static void main(String[] args) {
		// 거스름돈을 몇개의 동전으로 지불할 수 있는지 구하는 프로그램을 작성
		// 거스름돈이 2680원인 경우,
		// 500원짜리 5개, 100원짜리 1개, 50원짜리 1개, 10원짜리 3개
		Scanner sc = new Scanner(System.in);
		
		System.out.println("거스름돈을 입력하세요 >> ");
		
		int money = sc.nextInt();
		int hund5 = 0;
		int hund1 = 0;
		int fifteen = 0;
		int ten = 0;
		
		if(money >= 500) {	
			hund5 += money / 500 ;
			money -= 500 * hund5;
		}
		
		if(money >= 100) {
			hund1 += money / 100 ;
			money -= 100 * hund1;
		}
		
		if(money >= 50) {
			fifteen += money / 50 ;
			money -= 50 * fifteen;
		}
		
		if(money >= 10) {
			ten += money / 10 ;
			money -= 10 * ten;
		}
		
		System.out.println("500원짜리 " + hund5 + "개");
		System.out.println("100원짜리 " + hund1 + "개");
		System.out.println("50원짜리 " + fifteen + "개");
		System.out.println("10원짜리 " + ten + "개");
	}

}
