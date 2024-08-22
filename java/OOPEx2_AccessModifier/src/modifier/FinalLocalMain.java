package modifier;

public class FinalLocalMain {

	public static void main(String[] args) {
		// 지역변수
		final int num;
		num = 10;
		System.out.println(num);
		
		// 수정 불가
		// num = 20;
		System.out.println(num);
		
		method(10);
		
		
		
		float f = 1.5f;
		double d = 2.2;
		
		System.out.println(f + d);
		
		
	}
	
	static void method(final int param) {}

}
