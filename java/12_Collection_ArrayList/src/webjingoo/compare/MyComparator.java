package webjingoo.compare;

import java.util.Comparator;

public class MyComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		o1.getUserId();
		
		o2.getUserId();
		
		return o1.getUserId().compareTo(o2.getUserId());
	}

}
