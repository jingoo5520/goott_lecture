import java.util.Scanner;

public class RepetitiveInput {
	public static void main(String[] args) {
		// 반복적으로 입력받는 경우
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("숫자를 입력하세요.(-1을 입력하면 종료) >>> ");

			int userNum = sc.nextInt();
			sc.nextLine();

			if (userNum == -1) {
				System.out.println("종료합니다..");
				break;
			}

			System.out.println("이름을 입력하세요.");

			String name = sc.nextLine();
		}

	}
}
