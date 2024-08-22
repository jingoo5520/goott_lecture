package webjingoo.treesetex2;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExMain {

	public static void main(String[] args) {
		Student stu1 = new Student("0001", "홍길동", 95);
		Student stu2 = new Student("0002", "홍길서", 98);
		Student stu3 = new Student("0003", "홍길남", 90);
		Student stu4 = new Student("0004", "홍길북", 91);
		Student stu5 = new Student("0004", "홍길이", 91);

		ComparatorService service = new ComparatorService();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 학번순 정렬(오름차순)");
			System.out.println("2. 이름순 정렬(오름차순)");
			System.out.println("3. 성적순 정렬(내림차순)");

			System.out.print("정렬기준 선택 >>> ");
			int num = sc.nextInt();
			String base = null;

			switch (num) {
			case 1:
				base = "stuNo";
				break;
			case 2:
				base = "name";
				break;
			case 3:
				base = "score";
				break;
			default:
				break;
			}

			Comparator<Student> comp = service.setComparator(base);
			Set<Student> output = new TreeSet<Student>(comp);
			
			output.add(stu1);
			output.add(stu2);
			output.add(stu3);
			output.add(stu4);
			output.add(stu5);
			
			System.out.print(output);
		}

	}
	
	static void displayStudents() {
		
		
	}
}
