public class ElectronicProduct {
	private String modelName;
	private int price;
	
	public ElectronicProduct (String modelName, int price) {
		this.modelName = modelName;
		this.price = price;
	}

	String ModelName() {
		return modelName;
	}
	
	int getPrice() {
		return price;
	}
	
	public String toString() {
		return "modelName: " + modelName + ", price: " + price; 
	}
}
