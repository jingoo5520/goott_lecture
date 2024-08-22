
public class MemberCall {

	int iv = 10;
	static int cv = 20;
	
	int iv2 = cv;
	
	// static(클래스) 변수에 인스턴스 변수 사용 불가
	// 클래스 변수는 프로그램 시작과 동시에 생성
	// 인스턴스 변수는 객체 생성과 동시에 생성
	// 즉 아직 생성되지 않은 인스턴스 변수를 사용할 수 없는 것
	// static int cv2 = iv;
	
	// 정적 메서드
	static void staticMethod1 () {
		System.out.println(cv);
		// System.out.println(iv); 정적 메서드 안에서 인스턴스 변수 사용 불가
		// 객체 생성 후 인스턴스 변수 참조 가능
		MemberCall mc = new MemberCall();
		System.out.println(mc.iv);
	}
	
	// 인스턴스 메서드
	void instanceMethod1() {
		System.out.println(cv);
		System.out.println(iv);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberCall.staticMethod1();
		
		MemberCall mc = new MemberCall();
		mc.instanceMethod1();
	}

}
