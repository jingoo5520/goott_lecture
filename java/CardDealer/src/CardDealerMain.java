import java.util.Scanner;

public class CardDealerMain {
	
	private static void showMenu() {
		System.out.println("********************************************");
		System.out.println("1. 덱 보기, 2. 카드 섞기, 3. 카드 뽑기, 0. 종료");
		System.out.println("********************************************");
		System.out.print("선택 >>> ");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu;
		// TODO Auto-generated method stub
		CardDealer dealer = new CardDealer();
		while(true) {
			showMenu();
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				dealer.showCard();
				break;
			case 2:
				dealer.shuffle();
				break;
			case 3:
				dealer.pickCard();
				break;
			case 0 :
				System.out.println("딜러가 퇴근합니다.");
				return;
			default:
			}
		}
	}
}
