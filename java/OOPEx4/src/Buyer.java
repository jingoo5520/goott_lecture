
public class Buyer {
	// 구매품목
	private ElectronicProduct[] basket;
	private int totalPrice;
	private double point;
	private int count;

	public Buyer() {
		this.basket = new ElectronicProduct[3];
		this.totalPrice = 0;
		this.point = 0.0;
		this.count = 0;
	}

	ElectronicProduct[] getBasket() {
		return basket;
	}

	int getTotalPrice() {
		return totalPrice;
	}

	void addTotalPrice(int price) {
		this.totalPrice += price;
	}

	double getPoint() {
		return point;
	}

	void addPoint(double point) {
		this.point += point;
	}

	void buyProduct(ElectronicProduct product) {
		if (count < 3) {
			basket[count] = product;
			addTotalPrice(product.getPrice());
			addPoint(product.getPrice() * 0.1);
			count++;
			System.out.println("제품 구매완료 >> " + product.toString());
			System.out.println("적립 포인트: " + product.getPrice() * 0.1 + ", 누적 금액: " + totalPrice + ", 누적 포인트: " + point);
		} else {
			System.out.println("더이상 구매할 수 없습니다.");
		}
	}

	void display() {
		System.out.println("------------구입상품 목록------------");
		for (int i = 0; i < count; i++) {
			System.out.println(basket[i].toString());
		}
		System.out.println("총 사용 금액: " + totalPrice);
		System.out.println("적립 포인트: " + point);
	}

}
