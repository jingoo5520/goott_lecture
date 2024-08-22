
public class Book {
	private String title;
	private int price;
	private int count;

	public Book(String title, int price, int count) {
		this.title = title;
		this.price = price;
		this.count = count;
	}

	public int getPrirce() {
		return price * count;
	}

	public String toString() {
		return "title: " + title + ", price: " + price + ", count: " + count;
	}
}
