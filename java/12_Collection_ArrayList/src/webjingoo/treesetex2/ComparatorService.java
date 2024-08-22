package webjingoo.treesetex2;

import java.util.Comparator;

public class ComparatorService {

	public Comparator<Student> setComparator(String base) {
		Comparator<Student> comp = null;

		switch(base) {
		case "stuNo":
			comp = new Comparator<Student>() {

				@Override
				public int compare(Student s1, Student s2) {
					return s1.getStuNo().compareTo(s2.getStuNo());
				}
			};
			break;
		case "name":
			comp = new Comparator<Student>() {

				@Override
				public int compare(Student s1, Student s2) {
					return s1.getName().compareTo(s2.getName());
				}
			};
			break;
		case "score":
			comp = new Comparator<Student>() {

				@Override
				public int compare(Student s1, Student s2) {
					return s1.getScore() < s2.getScore() ? 1 : s1.getScore() == s2.getScore() ? 0 : -1;
 				}
			};
			break;
		default:
			System.out.println("");
		}
		
		return comp;
	}

}
