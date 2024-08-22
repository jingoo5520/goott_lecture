package teacher.treesetex2;

import java.util.Comparator;

public class NameComparatorDesc implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
//		int nameComparison = o2.getName().compareTo(o1.getName());
//        if (nameComparison != 0) {
//            return nameComparison;
//        } else {
//            return o1.getStuNo().compareTo(o2.getStuNo());  // 학번으로 추가 비교
//        }
		
		return o2.getName().compareTo(o1.getName());
	}
	
}