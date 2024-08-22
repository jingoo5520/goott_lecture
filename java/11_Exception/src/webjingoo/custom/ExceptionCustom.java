package webjingoo.custom;

import java.util.Scanner;

public class ExceptionCustom {
	public static void main(String args[]) {
		
		int num = 0;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("양수 입력 >> ");
			num = sc.nextInt();
			
			if(num < 0) {
				throw new NotPositiveInteger("양수가 아닙니다.");
			}
		} catch (NotPositiveInteger e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		System.out.println(num);
	}
}
