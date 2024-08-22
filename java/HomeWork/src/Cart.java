import java.util.Arrays;

public class Cart {
	private Book[] books = new Book[10];
	private int totalCount;
	
	public void addBook(Book book){
		books[totalCount] = book;
		totalCount++;
	}
	
	private int calculateTotalPrice() {
		return 1;
	}
	
	public void displayBooks() {
		int totalPrice = 0;
		System.out.println("-------장바구니상품-------");
		for(int i = 0; i < totalCount; i ++) {
			System.out.println( books[i].toString());
			totalPrice += books[i].getPrirce();
		}
		
		
		System.out.println("-----------------------");
		System.out.println("totalPrice: " + totalPrice);
	}
}
