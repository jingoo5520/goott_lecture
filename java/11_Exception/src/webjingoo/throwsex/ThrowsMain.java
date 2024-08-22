package webjingoo.throwsex;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ThrowsMain {

	public static void main(String[] args) {
		System.out.print("숫자를 입력하세요 >> ");
		int num = 0;
		
		try {
			num = getPositiveNumber();
		} catch (InputMismatchException e) {
			System.out.println("숫자가 아닙니다.");
		} catch (NumberFormatException e) {
			System.out.println();
		}
		
		
		
		

	}
	
	private static int getPositiveNumber() throws InputMismatchException, NumberFormatException {
		Scanner sc = new Scanner(System.in);
		int tmp = sc.nextInt();
		// 예외 발생
		sc.nextLine();
		
		int temp2 = Integer.parseInt("3.141592");
		
		
		
		
		return tmp;
	}

}
