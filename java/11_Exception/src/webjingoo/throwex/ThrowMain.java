package webjingoo.throwex;

public class ThrowMain {

	public static void main(String[] args) {
		
		Student stu1 = null;
		
		try {
			stu1 = new Student("240001", "홍길동", -80, 90, 100);
			
			
			System.out.println(stu1.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		

	}

}
