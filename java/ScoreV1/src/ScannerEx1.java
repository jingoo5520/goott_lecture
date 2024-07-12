import java.util.Scanner;
// 필요한 객체 import 단축키: ctrl + shift + o

public class ScannerEx1 {

	public static void main(String[] args) {
		
		// 스캐너 객체 생성
		Scanner sc = new Scanner(System.in); 
		
		// String으로 받기
		System.out.print("이름을 입력하세요: ");
		String str = sc.nextLine();
		System.out.println(str);
		
		// Int로 받기
		System.out.print("이름을 입력하세요: ");
		int age = sc.nextInt();
		System.out.println(age);
		
		// double로 받기
		System.out.print("키를 입력하세요: ");
		double height = sc.nextDouble();
		System.out.println(height);
		
		
	}

}
