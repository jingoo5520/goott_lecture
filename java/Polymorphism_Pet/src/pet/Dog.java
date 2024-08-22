package pet;

import webjingoo.Pet;

public class Dog extends Pet{
	public Dog(String name, String type, int age) {
		super(name, type, age);
	}

	// 추상메서드
	@Override
	public void doCry() {
		System.out.println("멍멍");
	}
	
	public void doWag() {
		System.out.println(this.getName() + "가 꼬리를 흔듭니다.");
	}
	
	@Override
	public String toString() {
		return "";
	}
}
