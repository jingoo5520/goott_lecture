
public class CartMain {

	public static void main(String[] args) {
		Cart cart = new Cart();
		
		Book book1 = new Book("자바", 20000, 2);
		Book book2 = new Book("JSP", 22000, 3);

		cart.addBook(book1);
		cart.addBook(book2);
		
		cart.displayBooks();
		
	}

}
