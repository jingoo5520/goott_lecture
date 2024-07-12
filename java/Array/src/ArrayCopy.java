import java.util.Arrays;

public class ArrayCopy {

	public static void main(String[] args) {
		char[] chArr = new char[] { 'a', 'b', 'c' };
		System.out.println("원본 배열: " + Arrays.toString(chArr));
		
		// 원본 배열 복사 (deep copy)
		char[] copyChArr = new char[chArr.length];
		
		int i = 0;
		for(char c : chArr) {
			copyChArr[i] = c;
			i++;
		}
		System.out.println("복사 배열: " + Arrays.toString(copyChArr));
		
		chArr[1] = 'B';
		
		System.out.println("원본 배열: " + Arrays.toString(chArr));
		System.out.println("복사 배열: " + Arrays.toString(copyChArr));

		System.out.println("원본 배열: " + chArr.hashCode());
		System.out.println("복사 배열: " + copyChArr.hashCode());
		
		System.out.println(chArr == copyChArr);

		// 얕은 복사
		
		String[] heroes = new String[] {"아이언맨", "헐크", "캡틴아메리카노"};
		
		String[] copyHeroes = heroes;
		
		System.out.println("원본 배열: " + Arrays.toString(heroes));
		System.out.println("복사 배열: " + Arrays.toString(copyHeroes));
		
		heroes[2] = "캡틴 아메리카";
		
		System.out.println("원본 배열: " + Arrays.toString(heroes));
		System.out.println("복사 배열: " + Arrays.toString(copyHeroes));
		
		// 원본이든 사본이든 변경하면 함께 바뀜
		copyHeroes[2] = "캡틴짱";
		
		System.out.println("원본 배열: " + Arrays.toString(heroes));
		System.out.println("복사 배열: " + Arrays.toString(copyHeroes));
		
		// String
		// 같은 문자열인 경우 같이 참조하지만,
		// 문자열을 변경시 다른 메모리를 새로 할당
		System.out.println("-----------------------------------------------");
		
		String str1 = "대한민국";
		String str2 = "대한민국";
		
		
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		String str3 = new String("대한민국");
		String str4 = new String("대한민국");
		
		//해쉬코드는 문자열의 내용을 기반으로 동작하기 때문에 정확한 주소값은 볼 수 없음
		
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
		
		// 실제 객체의 메모리 주소를 기반으로 한 해시코드 얻기
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
		
		
		System.out.println(str3 == str4);
		
		str3 = "대한민국";
		
		System.out.println(str3);
		System.out.println(str4);
		
		// 주소값 비교
		System.out.println(str3 == str4);
		
		// 값 비교
		System.out.println(str3.equals(str4));
		
		
		// 깊은 복사
		// 1) new 연산자로 복사객체 생성 후 for 문을 이용해 각 요소 복사
		// 2) Arrays.copyOf()
		// 3) System.arraycopy()
		
		
		// 정렬
		int[] arr5 = { 3, 5, 1, 2, 4};
		Arrays.sort(arr5);
		System.out.println(Arrays.toString(arr5));
		
		// Boxing
		// 기본타입 -> 참조타입
		int intA = 12;
		Integer integerA = new Integer(intA);
		
		// Unboxing
		int intB = integerA.intValue();
		System.out.println(intB);
	}

}
