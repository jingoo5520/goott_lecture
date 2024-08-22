public class Main {

	public static void main(String[] args) {

		Buyer buyer = new Buyer();

		ElectronicProduct tv = new Tv("티비모델1", 200000);
		Monitor moniter = new Monitor("모니터모델1", 100000);
		Computer computer = new Computer("컴퓨터모델1", 300000);
		ElectronicProduct tv2 = new Tv("티비모델2", 180000); // 구매불가

		

		buyer.buyProduct(tv);
		buyer.buyProduct(moniter);
		buyer.buyProduct(computer);
		buyer.buyProduct(tv2);

		tv.chi();
		
		buyer.display();
	}

}

// ElectronicProduct
// Tv, moniter, computer