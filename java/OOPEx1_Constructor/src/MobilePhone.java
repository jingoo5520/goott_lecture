
public class MobilePhone {
	String brandName;
	String modelName;
	int mainMemory;
	String color;
	
	public MobilePhone() {
		System.out.println("기본 생성자");
	}
	
	// 생성자 오버로딩
	public MobilePhone(String brandName, String modelName, int mainMemory, String color) {
		this.brandName = brandName;
		this.modelName = modelName;
		this.mainMemory = mainMemory;
		this.color = color;
	}
	
	// this()
	public MobilePhone(String brandName, String modelName) {
		// 생성자에서 다른 생성자 호출시 첫 문장에 써야함
		this(brandName, modelName, 256, "흰색");
	}
	
	// getter, setter 메서드
	String getBrandName() {
		return this.brandName;
	}
	
	void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
//	public MobilePhone(int mainMemory, String color) {
//		// 생성자에서 다른 생성자 호출시 첫 문장에 써야함
//		this(mainMemory, color);
//		this.brandName = "";
//		this.modelName = "";
//	}
	
	
	
	public String toString() {
		return "[brandName: " + brandName + ", modelName: " + modelName + ", mainMemory: " + mainMemory + ", color: " + color + "]";
	}
}
