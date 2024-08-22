package webjingoo.treesetex;

import java.util.Comparator;

public class StuNoComparatorDesc implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		
		return o2.getStuNo().compareTo(o1.getStuNo());
		
	}
	

}
