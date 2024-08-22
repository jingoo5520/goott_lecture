package webjingoo;

public class MethodCreationEx {

	public static int add(int a, int b) {
		return a + b;
	}
	
	public float add(float a, float b) {
		float c = 0.0f;
		return a + b;
	}
	
	public void outputNTims(String str, int count) {
		for(int i = 0; i < count; i ++) {
			System.out.println(str + (i + 1) + "번째");
		}
	}
	
	public static void main(String[] args) {
		// 메서드
		// [접근제한자] [static] 반환값타입|void 메서드 이름 ([매개변수타입 매개변수이름]) {
			// body
			// [return 반환값;]
		// }
		int result = MethodCreationEx.add(300, 500);
		System.out.println(result);
		
		MethodCreationEx mce = new MethodCreationEx();
		
		// 스태틱 메서드는 이렇게 객체를 만들어서 사용하지 말것
		float result1= mce.add(3.0f, 5.0f);

		// 이름이 outputNTims이고, 매개변수는 String 타입 변수 하나와 int타입 변수 n을 받아
		// 받은 문자열을 화면에 n번 출력하는 메서드(인스턴스 메서드)를 만들고 호출
		
		mce.outputNTims("안녕하세요", 10);
		
		// 메서드 오버로딩
		
		
	}

}
