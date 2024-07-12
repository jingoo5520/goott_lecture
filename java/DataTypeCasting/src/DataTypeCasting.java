
public class DataTypeCasting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		byte b = 125;
		System.out.println(b);

		// 묵시적(자동) 형 변환(casting): 프로그래머가 형 변환 연산자를 쓰지 않더라도 자동으로 형변환
		// 보통 작은 타입에서 큰 타입으로 변환할 때 묵시적 형변환이 일어남

		int i = b;
		System.out.println(i);

		// int -> float
		float f = 1234;

		float f1 = (float) 1234;

		// float -> int
		int j = (int) 3.14f; // 강제 형변환
		System.out.println(j);

		// int -> byte
		int i2 = 12;
		// byte b2 = i2; 에러발생
		byte b2 = (byte) i2;

		// 명시적 형변환: 프로그래머가 형변환을 하라고 명시(큰 타입 -> 작은 타입)
		// double -> float
		double pi = 3.14;
		// float fpi = pi; 에러 발생
		float fpi = (float) pi;

		// int -> short
		int i3 = 32767;
		short s = (short) i3;

		System.out.println(s);

		// 문자 타입
		char c = 'a';
		System.out.println(c);
		System.out.println((int) c); // 'a' 아스키코드
		System.out.println((char) 98); // 아스키코드 98 문자

		byte b6 = 97;
		
		// char char2 = b6;
		System.out.println(b6);
		
		short s4 = 97;
		// char char3 = s4; 에러발생
		
		boolean bool = true;
		// float f2 = (float)bool; 에러발생
		
		// 연산식에서 자동 형변환
		// 피연산자가 리터럴인 경우
		byte bSum = 10 + 20; // int + int
		
		// 피연산자 변수인 경우
		byte b7 = 10;
		byte b8 = 20;
		
		// byte bResult =  b7 + b8;
		// 에러 발생
		// 자바에서 산술연산 수행시 byte, short, char 타입의 피연산자는 자동으로 int 타입으로 변환
		
		
		long l3 = 50;
		int i6 = 60;
		
		// int result = l3 + i6;
		long result1 = l3 + i6;
		
		System.out.println(result1);
		
		// 실수 연산
		float f3 = 3.14F + 4.31F;
		
		// 중간에 놓침, 노션참고
		
		// 문자열 -> 숫자(정수, 실수)
		int a = Integer.parseInt("1");
		System.out.println(a);
		
		double d2 = Double.parseDouble("3.14");
		System.out.println(d2);
		
		// 숫자 -> 문자열
		String str1 = 3 + "";
		System.out.println(str1);
		
		String str2 = String.valueOf(10);
		System.out.println(str2);
		
		String str3 = String.valueOf(true);
		System.out.println(str3);
	}
	

}
