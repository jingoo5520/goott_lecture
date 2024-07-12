
public class DataTypesAndVariables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 기본 타입
		
		// boolean(1byte)
		boolean bool = true;
		System.out.println(bool);
		
		// char: 문자형, 한 문자를 저장(2byte)
		char character = 'a';
		System.out.println(character);
		
		char korChar = '가';
		System.out.println(korChar);
		
		char character2 = '\u0041';
		System.out.println(character2); // A
		
		// byte: 정수형 (1byte)
		byte b = 127;
		System.out.println(b);
		
		System.out.println("Byte타입의 최대값: " + Byte.MAX_VALUE);
		System.out.println("Byte타입의 최소값: " + Byte.MIN_VALUE);
		
		// byte 타입의 데이터는 -128~ 127 범위를 가짐.
		// 초과시 오버플로우 발생
		// 예시의 130인 경우, 초과한 만큼 최소값에서부터 증가
		byte b1 = (byte)128; 
		System.out.println(b1); // -128
		
		//short: 정수형 (2byte)
		short sInt = 128;
		System.out.println(sInt);
		
		System.out.println("short타입의 최대값: " + Short.MAX_VALUE);
		System.out.println("short타입의 최소값: " + Short.MIN_VALUE);
		
		//int: 정수형 (4byte)
		int i = 35;
		System.out.println(i);
		System.out.println("int타입의 최대값: " + Integer.MAX_VALUE);
		System.out.println("int타입의 최소값: " + Integer.MIN_VALUE);
		
		// long: 정수형 (8byte)
		long l = 35L; // 리터럴 접미사, 데이터 타입을 확실히 지정
		
		System.out.println("long타입의 최대값: " + Long.MAX_VALUE);
		System.out.println("long타입의 최소값: " + Long.MIN_VALUE);
		
		
		// float: 실수형 (4byte)
		float f = 3.14F; // 접미사를 붙이지 않으면 double로 인식, 에러 발생
		
		System.out.println("float타입의 최대값: " + Float.MAX_VALUE);
		System.out.println("float타입의 최소값: " + Float.MIN_VALUE);
		
		
		// double: 실수형 (8byte)
		double d = 3.14;
		System.out.println("double타입의 최대값: " + Double.MAX_VALUE);
		System.out.println("double타입의 최소값: " + Double.MIN_VALUE);
		
		// 참조 타입
		// 데이터 주소값을 저장
		// String: 문자열
		// char 타입 데이터는 작은 따옴표, String 타입 데이터는 큰 따옴표 필요
		String str = "대한민국";
		System.out.println(str);
		
		// 문자열 길이
		System.out.println(str.length());
		
		System.out.println(str.getClass().getName());
		
		String str1 = "";
		// char ch = ''; 에러 발생
		char ch = ' '; // 공백 문자
	}
	
	

}
