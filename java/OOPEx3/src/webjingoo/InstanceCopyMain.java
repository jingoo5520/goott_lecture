package webjingoo;

public class InstanceCopyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student s1 = new Student("24001", "홍길동", 80, 96, 76);
		s1.displayScore();
		
		// 얕은 복사
		// 참조 주소만 가져옴
		Student s2 = s1;
		s2.displayScore();
		
		System.out.println(s1);
		System.out.println(s2);
		
		// 깊은 복사
		Student s3 = new Student(s1);
		
		System.out.println(s1);
		System.out.println(s3);
		
	}

}
