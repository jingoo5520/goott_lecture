package webjingoo.treesetex;

import java.util.ArrayList;
import java.util.TreeSet;

public class TreeSetExMain {

	public static void main(String[] args) {
		ArrayList<Student> stuArr = new ArrayList<Student>();
		
		Student stu1 = new Student("0001", "홍길동", 95);
		Student stu2 = new Student("0002", "홍길서", 98);
		Student stu3 = new Student("0003", "홍길남", 90);
		Student stu4 = new Student("0004", "홍길북", 91);
		Student stu5 = new Student("0004", "홍길이", 91);
		
		TreeSet<Student> treeSet = new TreeSet<Student>();
		
		stuArr.add(stu1);
		stuArr.add(stu2);
		
		System.out.println(stuArr);
		
		treeSet.add(stu1);
		treeSet.add(stu2);
		treeSet.add(stu3);
		treeSet.add(stu4);
		treeSet.add(stu5);
		
		// 기본 정렬
		System.out.println("기본 정렬(학번순)");
		System.out.println(treeSet);	
		
		
	}
}
