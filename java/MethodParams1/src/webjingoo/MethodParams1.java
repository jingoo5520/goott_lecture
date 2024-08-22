package webjingoo;

public class MethodParams1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		System.out.println("메서드 호출 전: " + a);
		
		callPrimitiveParams(a);
		
		System.out.println("메서드 호출 후: " + a);
	}
	
	static void callPrimitiveParams(int x) {
		x = 20;
	}

}
