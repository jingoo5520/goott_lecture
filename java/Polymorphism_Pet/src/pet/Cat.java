package pet;

import webjingoo.Pet;

public class Cat extends Pet {
	public Cat() {
		super();
	}
	
	public Cat(String name, String type, int age) {
		super(name, type, age);
	}

	// 추상메서드
	@Override
	public void doCry() {
		System.out.println("야옹");
	}

	public void doJump() {
		System.out.println(this.getName() + "가 점프합니다.");
	}

	@Override
	public String toString() {
		return "";
	}
}
