public class Main {

	public static void main(String[] args) {
		// 어떤 부서에 2 가지 형태의 사원이 근무
		// 모든 사원은 사번, 이름, 소속부서명, 급여를 가지고 있음
		// 정규직 사원에게는 기본급을 지급
		// 알바직 사원에게는 시간당 급여 지급(근무시간 * 시급)
		// 다형성과 상속관계를 이용해 구현해보라

		// 부서 클래스
		// 사원 클래스 - 정규직 클래스, 알바직 클래스
		
		Department department1 = new Department();
		
		RegularEmp emp1 = new RegularEmp(1000, "홍길동", "부서1", 0, 3000000);
		NonRegularEmp emp2 = new NonRegularEmp(1001, "홍길서", "부서1", 1,  8, 11000);
		
		department1.addEmp(emp1);
		department1.addEmp(emp2);
		
		department1.display();
	}

}
