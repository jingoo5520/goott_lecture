package webjingoo;

import java.util.Arrays;

public class StudentMain {

	public static void main(String[] args) {
		// Student 객체 생성
		// 선언과 메모리할당
		Student stu1 = new Student();
		
		stu1.name = "홍길동";
		stu1.score = 80;
		stu1.grade = 'B';
		System.out.println(stu1.name);
		System.out.println(stu1.score);
		System.out.println(stu1.grade);
		
		Student stu2 = new Student();
		stu2.name = "홍길서";
		stu2.score = 70;
		stu2.grade = 'C';
		
		Student[] students = new Student[2];
		
		students[0] = stu1;
		students[1] = stu2;
		
		System.out.println(students[0].name);
		
	}

}
