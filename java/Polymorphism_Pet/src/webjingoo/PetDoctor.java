package webjingoo;

public class PetDoctor {
	private String name;
	private int age;
	
	public PetDoctor(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void doCline(Pet pet) {
		System.out.println(pet.getName() + "을 치료합니다.");
	}
	
}
