package webjingoo.carddecex;

public class CardDecMain {

	public static void main(String[] args) {
		// 딜러: 카드덱을 랜덤하게 섞어 플레이어에게 5장씩 나눠준다.
		// 플레이어(2명): 딜러에게 받은 5장의 카드를 정렬딘 순서대로 보여준다.
		// 정렬기준: 숫자는 오름차순(1~13)
		// 숫자가 같으면 스페이드, 하트, 다이아몬드, 클럽 순서로 정렬
		
		// 승패 기준
		// 카드 숫자의 총합이 큰 플레이어가 승리
		// 같으면 무승부
		
		Dealer dealer = new Dealer();
		Player p1 = new Player();
		Player p2 = new Player();
		
		dealer.suffle();
		System.out.println("suffled dec: " + dealer.getDec());
		
		p1.setHands(dealer.handOutCards());
		p2.setHands(dealer.handOutCards());

		p1.sortHands();
		p2.sortHands();

		System.out.println("p1: " + p1.getHands());
		System.out.println("p2: " + p2.getHands());
		
		dealer.openHands(p1, p2);
	}
}
