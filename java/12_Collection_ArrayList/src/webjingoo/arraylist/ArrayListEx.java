package webjingoo.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(10);
		list.add(30);
		list.add(1, 20);
		list.add(new Integer(40));
		list.add(new Integer("50"));

		System.out.println(list);

		// get Data
		int a = list.get(2);
		System.out.println(a);

		// length of list
		System.out.println(list.size());

		ArrayList<Integer> list2 = new ArrayList<Integer>(list.subList(1, 3));
		System.out.println(list2);

		display(list, list2);

		ArrayList list3 = new ArrayList();

		list3.add("A");
		list3.add("B");
		list3.add("C");
		// has element?
		System.out.println(list3.contains("B"));

		System.out.println(list.indexOf(50));

		// new Integer(String s)로 추가한 원소는 찾을 때도 이렇게 찾아야함
		// list.add(new Integer("50"));
		System.out.println(list.indexOf(new Integer("50")));

		System.out.println(list);

		// remove element
		// 원소 삭제 후 빈칸 메우기
		list.remove(1);
		System.out.println(list);

		list.remove(new Integer("50"));
		System.out.println(list);

		ArrayList list4 = new ArrayList();

		list4.add(0);
		list4.add(1);
		list4.add(2);
		list4.add(3);
		list4.add(4);

		ArrayList list5 = new ArrayList();

		list5.add(0);
		list5.add(1);
		list5.add("A");
		list5.add("B");
		list5.add("C");

		display(list4, list5);
		
		// 중복 요소만 남기고 모두 삭제
		list4.retainAll(list5);
		
		display(list4, list5);
		
		// 중복 요소만 삭제 - 메서드
//		list5.removeAll(list4);
//		System.out.println(list5);
		
		// 중복 요소만 삭제 - 직접 찾아서 삭제
		
		// size 5 i = 0
		// size 4 i = 1
		// list5의 원소가 지워지면 해당 index를 다음 원소가 채우므로 
		// 정상작동 하지 않음
		
//		for(int i = 0; i < list5.size(); i++) {
//			if(list4.contains(list5.get(i))) {
//				list5.remove(i);
//			}
//		}
//		
//		display(list4, list5);
		
		// 뒤에서부터 지워주는 방법
		for(int i = list5.size() - 1; i >= 0; i--) {
		if(list4.contains(list5.get(i))) {
			list5.remove(i);
		}
	}
	
	display(list4, list5);
		
	}

	public static void display(ArrayList list1, ArrayList list2) {
		System.out.println("display");
		System.out.println(list1);
		System.out.println(list2);
	}

}
