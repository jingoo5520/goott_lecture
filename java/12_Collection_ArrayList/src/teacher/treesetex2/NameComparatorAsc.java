package teacher.treesetex2;

public class NameComparatorAsc implements Sortable {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}
