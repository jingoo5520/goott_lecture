package webjingoo.compare;

import java.util.ArrayList;
import java.util.List;

public class ComparatorMain {

	public static void main(String[] args) {
		User user1 = new User("a", 10);
		User user2 = new User("b", 8);
		User user3 = new User("c", 40);
		
		List<User> ulist = new ArrayList<User>();
		
		ulist.add(user1);
		ulist.add(user2);
		ulist.add(user3);
		
		System.out.println("정렬전");
		System.out.println(ulist);
		
		System.out.println("정렬후");
		ulist.sort(null);
		System.out.println(ulist);
		
		// 커스텀 비교기
		ulist.sort(new MyComparator());
		System.out.println(ulist);
	}

}
