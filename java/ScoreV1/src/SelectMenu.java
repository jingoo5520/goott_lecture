import java.util.Scanner;

public class SelectMenu {

	public static void main(String[] args) {
		// 메뉴 선택
		// 1. 도서정보 입력
		// 2. 총 비용
		// 3. 종료

		// 1 선택 -> 도서명, 가격, 수량 -> 정보 출력
		// 2 선택 -> 총 비용 출력
		// 3 선택 -> 종료
		Scanner sc = new Scanner(System.in);
		int totalPrice = 0;

		while (true) {
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1: 도서정보 입력");
			System.out.println("2. 총 비용 확인");
			System.out.println("3. 종료");
			System.out.println("선택>> ");

			int select = sc.nextInt();
			sc.nextLine();
			String title;
			int price;
			int count;
			
			switch (select) {
			case 1:
				System.out.println("도서정보를 입력해주세요.");
				System.out.println("도서명: ");
				title = sc.nextLine();
				System.out.println("가격: ");
				price = sc.nextInt();
				System.out.println("수량: ");
				count = sc.nextInt();
				
				System.out.println("입력하신 정보:");
				System.out.println("도서명: " + title);
				System.out.println("가격: " + price);
				System.out.println("수량: " + count);
				System.out.println("총 가격: " + (price * count) + "\n");
				totalPrice += price * count;
				break;
			case 2:
				System.out.println("총 비용은 " + totalPrice + "입니다.\n");
				break;
			case 3:
				System.out.println("종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.\n");
			}

		}
	}

}
