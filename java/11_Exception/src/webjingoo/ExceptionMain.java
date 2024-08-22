package webjingoo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionMain {

	public static void main(String[] args) {
		
		int result = 0;
		
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("정수를 입력하세요");
			

			int num = sc.nextInt();
			
			System.out.println("입력한 수는: " + num);
			
			result = 3 / num;

		} catch (InputMismatchException e) {
			System.out.println(e);
			System.out.println("정수만 입력할 수 있습니다.");
		} catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// try block 에서 예외가 발생하든 안하든 가장 마지막에 실행되는 코드, 생략 가능
			System.out.println("finally 끝");
		}
		
		System.out.println(result);
			
	}
}
