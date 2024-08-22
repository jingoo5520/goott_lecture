package webjingoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RawTypeMain {

	public static void main(String[] args) {
		// raw type 경고메세지
		// 어느타입의 데이터를 넣을지 선언을 하지 않은 경우
		ArrayList ar = new ArrayList<>();
		ar.add(10);
		ar.add(3.14f);
		ar.add("대한민국");
		ar.add(new Computer());
		System.out.println(ar.toString());
		
		// Generic 타입 사용 권고
		ArrayList<String> ar2 = new ArrayList<String>();
		ar2.add("스트링만");
		ar2.add("저장가능");
		// 에러발생
		// ar2.add(new Computer());
		
		// 기본 타입을 제네릭으로 사용 불가
		// ArrayList<int> ar3 = new ArrayList<int>();
		
		// 참조타입, wrapper 클래스를 이용
		ArrayList<Integer> ar3 = new ArrayList<Integer>();
		
		// 위 형식보다 아래처럼 인터페이스로 다형성을 구현하여 사용하는 것이 이점이 있음
		List<String> ar4 = new Stack<String>();	
		
	}

}
