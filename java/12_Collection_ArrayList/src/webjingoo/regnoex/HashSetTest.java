package webjingoo.regnoex;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		// 두 객체는 다른 객체
		// hashCode(), equals()를 override하여 두 객체가 같음을 보여줘야함
		Korean kor1 = new Korean("1234 ","홍길동");
		Korean kor2 = new Korean("1234 ","홍길동");
		
		Set set = new HashSet();
		
		set.add(kor1);
		set.add(kor2);
		
		System.out.println(set);
		
		System.out.println(kor1.hashCode());
		System.out.println(kor2.hashCode());
		System.out.println(kor1.equals(kor2));
		
		System.out.println(kor1 == kor2);
		
		System.out.println(set);
		
//		String str1 = "대한민국";
//		String str2 = "대한민국";
//		
//		System.out.println(str1.hashCode());
//		System.out.println(str2.hashCode());
//		System.out.println(str1 == str2);
//		
//		int[] a = {1, 2, 3};
//		int[] b = {1, 2, 3};
//		
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(a.equals(b));
//		System.out.println(a == b);
	}

}
