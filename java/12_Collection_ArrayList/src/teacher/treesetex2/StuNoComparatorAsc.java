package teacher.treesetex2;

import java.util.Comparator;

public class StuNoComparatorAsc implements Sortable {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getStuNo().compareTo(o2.getStuNo());
	}
}
