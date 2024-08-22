package webjingoo.polymorph.instance;

class ParentA {
	
}

class Child extends ParentA {
	
}

public class InstanceOfMain {

	public static void main(String[] args) {
		ParentA parentA = new ParentA();
		Child child = new Child();
		
		System.out.println(parentA instanceof ParentA);
		System.out.println(child instanceof ParentA);
		System.out.println(parentA instanceof Child);
		System.out.println(child instanceof Child);

	}

}
