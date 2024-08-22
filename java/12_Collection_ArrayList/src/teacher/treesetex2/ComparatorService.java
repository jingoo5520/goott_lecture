package teacher.treesetex2;

import java.util.Comparator;

public class ComparatorService {

	public Comparator<Student> setComparator(String base) {
		Comparator<Student> comp = null;

		switch(base) {
		case "stuNo":
			comp = new StuNoComparatorAsc();
			break;
		case "name":
			comp = new NameComparatorAsc();
			break;
		case "score":
			comp = new ScoreComparatorDesc(); 
			break;
		default:
			break;
		}
		
		System.out.println(comp);
		return comp;
	}

}
