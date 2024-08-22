package webjingoo.cart;

public class Product {
	private String proName;
	private int price;

	public Product(String proName, int price) {
		this.proName = proName;
		this.price = price;
	}

	public String getProName() {
		return proName;
	}

	public int getPrice() {
		return price;
	}
	
	@Override
	public boolean equals(Object obj) {
		Product otherProduct = (Product) obj;
		if(this.proName.equals(otherProduct.getProName())&& this.price == otherProduct.getPrice()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (this.proName + this.price).hashCode();
	}
	
	

	@Override
	public String toString() {
		return "Product [proName=" + proName + ", price=" + price + "]";
	}
}
