package webjingoo.cart;

import java.util.Scanner;

public class CartMain {

	public static void main(String[] args) {
		// Cart 장바구니
		// 상품을 cart에 추가 add()
		// 상품을 cart에서 제거 delete()

		// Cart cart = new Cart();

		// cart.add(new Product("자바", 20000), 1);
		//cart.add(new Product("오라클", 22000), 1);

		// 같은 상품 추가시 개수만 증가
		// cart.add(new Product("자바", 20000), 1);

		// 카트에 담긴 상품 목록
		// cart.display();
		// =======================
		// 책: "자바", 가격: 20000, 수량: 2
		// 책: "오라클", 가격: 22000, 수량: 1

		Cart cart = new Cart();
		Scanner sc = new Scanner(System.in);

		while (true) {
			showMenu();

			int num = 0;

			try {
				num = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(e);
			}

			switch (num) {
			case 1:
				cart.addProduct();
				break;
			case 2:
				cart.delProduct();
				break;
			case 3:
				cart.displayCart();
				break;
			case 9:
				System.out.println("종료합니다..");
				System.exit(0);
			default:
				System.out.println("다시 입력해주세요.");
			}

		}

	}

	private static void showMenu() {
		System.out.println("-------------------------------------Cart-------------------------------------");
		System.out.print("1. addProduct / ");
		System.out.print("2. delProduct / ");
		System.out.print("3. displayProducts / ");
		System.out.println("9. 종료");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("메뉴 선택 >> ");
	}

}
