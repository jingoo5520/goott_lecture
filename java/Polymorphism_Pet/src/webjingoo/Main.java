package webjingoo;

import pet.Cat;
import pet.Dog;

public class Main {
	// Pet: Cat, Dog
	// PetDoctor
	// 이들의 상속관계를 구현, 다형성 연습
	// 패키지도 나누기
	
	
	
	public static void main(String args[]) {
		Dog myDog = new Dog("뚜비", "포메라니안", 4);
		
		myDog.doCry();
		myDog.doWag();
		
		Cat myCat = new Cat("나비", "샴고양이", 2);
		
		myCat.doCry();
		
		PetDoctor petDoctor = new PetDoctor("닥터", 30);
		
		petDoctor.doCline(myCat);
		
		
		
		Pet myDog2 = new Dog("자바", "불독", 1); // 업캐스팅
		
		
		petDoctor.doCline(myDog2);
		
		// doCline(Pet pet)
		
		myDog2.doCry();
		
		// 업캐스팅시
		// myDog2.doWag();
		
		// 다운캐스팅
		((Dog)myDog2).doWag();
	}
}
