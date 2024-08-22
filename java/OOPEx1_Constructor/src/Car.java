
public class Car {
	String brandName;
	String modelName;
	int price;
	String color;

	public void displayCar() {
		System.out.println("브랜드명: " + brandName + ", 모델명: " + modelName + ", 가격: " + price + ", 색상: " + color);
	}

	void createCar(String brandName, String modelName, int price, String color) {
		this.brandName = brandName;
		this.modelName = modelName;
		this.price = price;
		this.color = color;
	}

}
