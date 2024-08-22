package teacher.treesetex2;

public class SortManager {
	
	public static Sortable getSortMethod(int num) {
		
		Sortable result = null;
		
		switch (num) {
		case 1:
			result = new StuNoComparatorAsc();
			break;
		case 2:
			result = new NameComparatorAsc();
			break;
		case 3:
			result = new ScoreComparatorDesc();
			break;
		default:
			break;
		}
		
		return result;
	}
	
	
	
}
