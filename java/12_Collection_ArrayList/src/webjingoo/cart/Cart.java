package webjingoo.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cart {
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();

	public Cart() {
		// 테스트용 미리 아이템 추가
		testSet();
	}

	public void addProduct() {
		Scanner sc = new Scanner(System.in);

		System.out.print("상품명: ");
		String proName = sc.nextLine();
		System.out.print("가격: ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("수량: ");
		int count = Integer.parseInt(sc.nextLine());

		Product product = new Product(proName, price);

		if (products.containsKey(product)) {
			System.out.println(product.getProName() + "를 " + count + "개 더 추가합니다.");
			// 개수만 늘림
			products.replace(product, products.get(product) + count);
		} else {
			products.put(product, count);
			System.out.println("상품명: " + proName + ", 가격: " + price + ", 개수: " + count + " 추가 완료.");
		}
	}

	public void delProduct() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> delProducts = new ArrayList<Product>();

		System.out.print("제거할 상품명: ");
		String proName = sc.nextLine();
		
		
		System.out.println("해당 제품 목록");
		int number = 0;
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			Product product = entry.getKey();
			String delProName = product.getProName();
			int price = product.getPrice();
			if(proName.equals(product.getProName())) {
				System.out.println(++number + ". " + delProName + "(" + price + ")" + " " + entry.getValue() + "개");
				delProducts.add(product);
			}
		}
		System.out.print("번호 입력 >> ");
		int delNum = Integer.parseInt(sc.nextLine()) - 1; 
		System.out.print("제거 개수(0 입력시 모두 제거): ");
		int count = Integer.parseInt(sc.nextLine());
		
		if(count == 0 || products.get(delProducts.get(delNum)) - count <= 0) {
			products.remove(delProducts.get(delNum));
			System.out.println(delProducts.get(delNum).getProName() + " 을(를) 모두 제거합니다.");
		} else {
			products.replace(delProducts.get(delNum), products.get(delProducts.get(delNum)) - count);
			System.out.println(delProducts.get(delNum).getProName() + " 을(를) " + count + "개 제거합니다.");
		}
	}

	public void displayCart() {
		if (products.size() == 0) {
			System.out.println("등록된 상품이 없습니다.");
			return;
		}

		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			String proName = entry.getKey().getProName();
			int price = entry.getKey().getPrice();
			int count = entry.getValue();

			System.out.println("proName: " + proName + " , price: " + price + ", count: " + count);
		}
	}

	private void testSet() {
		products.put(new Product("자바", 20000), 1);
		products.put(new Product("오라클", 22000), 1);
		products.put(new Product("웹프로그래밍", 18000), 1);
		products.put(new Product("자바", 25000), 1);
	}
}
