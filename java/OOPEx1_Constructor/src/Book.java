
public class Book {
	String title;
	String author;
	int price;
	
	public Book(){
	}
	
	public Book(String title, String author, int price){
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public Book(String title, String author){
		this(title, author, 0);
	}
	
	public void displayBook() {
		System.out.println("책 제목: " + title + ", 책 저자: " + author + ", 가격: " + price);
	}
	
	public String toString() {
		return "책 제목: " + title + ", 책 저자: " + author + ", 가격: " + price;
	}
}
