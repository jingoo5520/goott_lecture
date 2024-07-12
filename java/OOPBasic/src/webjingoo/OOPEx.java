package webjingoo;

public class OOPEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();
		
		order1.proName = "오라클";
		order1.price = 20000;
		order1.quantity = 2;
		
		order2.proName = "자바";
		order2.price = 10000;
		order2.quantity = 3;
		
		order3.proName = "스프링";
		order3.price = 25000;
		order3.quantity = 4;
		
		Order[] orders = new Order[3];
		orders[0] = order1;
		orders[1] = order2;
		orders[2] = order3;
		
		int totalPrice = 0;
		
		for(int i = 0; i < orders.length; i++) {
			System.out.println("상품명: " + orders[i].proName + ",\t 가격: " + orders[i].price + "원,\t 수량: " + orders[i].quantity);
			totalPrice += orders[i].price * orders[i].quantity;
		}
		
		
		
		System.out.println("총결제 금액: " + totalPrice);
	}

}
