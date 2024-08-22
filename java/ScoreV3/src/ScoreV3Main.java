import java.util.Scanner;

public class ScoreV3Main {

	private static void outputMenu() {
		System.out.println("****************************************************");
		System.out.println("**                  성 적 표 V3                     **");
		System.out.println("****************************************************");
		System.out.println("** 1. 반 생성                                       **");
		System.out.println("** 2. 생성된 반에 학생 입력                             **");
		System.out.println("** 3. 전체 학생 점수 출력                           	  **");
		System.out.println("** 4. 반, 학생 자동 생성                           	  **");
		System.out.println("** 5. 학번으로 검색                           	      **");
		System.out.println("** 9. 종료                                     	  **");
		System.out.println("****************************************************");
		System.out.println("메뉴 번호 입력 >>>> ");
	}

	private static StuClass createClass() {
		Scanner sc = new Scanner(System.in);
		int no;
		String className;

		System.out.println("반을 생성중..");
		System.out.print("반 번호: ");
		no = sc.nextInt();
		sc.nextLine();
		System.out.print("과정 이름: ");
		className = sc.nextLine();

		StuClass stuClass = new StuClass(no, className);
		System.out.print("생성완료: ");
		System.out.println(stuClass.toString());

		return stuClass;
	}

	private static void insertStu(StuClass stuClass) {
		Scanner sc = new Scanner(System.in);
		String stuNo;
		String stuName;
		int kor;
		int eng;
		int math;

		System.out.println("학생 추가중..");
		System.out.print("학생 번호: ");
		stuNo = sc.nextLine();
		System.out.print("학생 이름: ");
		stuName = sc.nextLine();
		System.out.print("국어점수: ");
		kor = sc.nextInt();
		System.out.print("영어점수: ");
		eng = sc.nextInt();
		System.out.print("수학점수: ");
		math = sc.nextInt();

		Student stu1 = new Student(stuNo, stuName, kor, eng, math);

		System.out.print("추가완료: ");
		System.out.println(stu1.toString());
		stuClass.insertStu(stu1);

	}
	
	private static void getScoreBoard(StuClass stuClass) {
		System.out.println(stuClass.getClassName() + " 과정 학생 목록");
		for(int i = 0; i < stuClass.getStuCount(); i++) {
			System.out.println(stuClass.getStuList()[i]);
		}
		
		System.out.println("종점: " + stuClass.getTotalTot());
		System.out.println("종 평균: " + stuClass.getTotalAvg());
	}

	
	private static StuClass autoInputStudent() {
		System.out.println("반, 학생 자동 생성");
		StuClass stuClass = new StuClass(1, "JAVA");
		Student stu1 = new Student("0001", "홍길동", 90, 90, 89);
		Student stu2 = new Student("0002", "홍길서", 70, 100, 95);
		Student stu3 = new Student("0003", "홍길남", 68, 70, 55);
		
		stuClass.insertStu(stu1);
		stuClass.insertStu(stu2);
		stuClass.insertStu(stu3);
		
		System.out.println("추가완료");
		
		return stuClass;
	}
	
	private static void searchStudentByNo(StuClass stuClass) {
		Scanner sc = new Scanner(System.in);
		
		String stuNo;
		
		System.out.println("학번으로 검색합니다...");
		System.out.println("학번 입력 >>> ");
		stuNo = sc.nextLine();
		
		for(int i = 0; i < stuClass.getStuList().length; i++) {
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean runBool = true;

		StuClass stuClass = null;

		while (runBool) {
			outputMenu();
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				stuClass = createClass();
				break;
			case 2:
				insertStu(stuClass);
				break;
			case 3:
				getScoreBoard(stuClass);
				break;
			case 4:
				stuClass = autoInputStudent();
				break;
			case 5:
				searchStudentByNo(stuClass);
				break;
			case 9:
				// 프로그램 종료
				// System.exit(0);
				System.out.println("종료합니다..");
				runBool = false;
				break;
			default:
				System.out.println("올바른 번호를 입력해 주세요.");
			}
		}

	}

}
