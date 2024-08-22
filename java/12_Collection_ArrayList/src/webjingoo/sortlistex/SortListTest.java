package webjingoo.sortlistex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortListTest {

	public static void main(String[] args) {
		String[] strArr = { "a", "b", "e", "q", "E", "W", "B" };

		Arrays.sort(strArr);
		System.out.println(Arrays.toString(strArr));

		// 대소문자 구분x
		Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
		System.out.println(Arrays.toString(strArr));

		List<String> list = new ArrayList<String>();

		list.add("a홍길동");
		list.add("e둘리");
		list.add("b마이콜");
		list.add("z도우너");

		System.out.println("정렬 전");
		System.out.println(list);

		list.sort(null);

		System.out.println("정렬 후");
		System.out.println(list);

		Comparator<String> comp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return o2.compareTo(o1);

			}

		};

		list.sort(comp);
		System.out.println(list);

		List<Integer> intList = new ArrayList<Integer>();

		intList.add(1);
		intList.add(23);
		intList.add(5);
		intList.add(9);
		intList.add(0);

		System.out.println("정렬 전");
		System.out.println(intList);

		intList.sort(null);

		System.out.println("정렬 후");
		System.out.println(intList);

		Comparator<Integer> compara = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				// return o2.compareTo(o1);
//				if (o1 < 02) {
//					return 1;
//				} else if ( 01 == o2) {
//					return 0;
//				} else {
//					return -1;
//				}

				return o1 < o2 ? 1 : (o1 == o2) ? 0 : -1;
			}

		};

		intList.sort(compara);
		System.out.println(intList);

	}

}
