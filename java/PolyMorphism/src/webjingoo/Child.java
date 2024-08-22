package webjingoo;

public class Child extends Parent {
	
	public Child() {
		super("A type");
		System.out.println("Child 기본 생성자");
	}
	
	public void childMethod () {
		System.out.println("child!");
	}
	
	
}
