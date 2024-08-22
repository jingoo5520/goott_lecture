// 싱글톤 패턴
public class ConnectServiceMain {

	public static void main(String[] args) {
		
		ConnectService cs1 = ConnectService.getInstance();
		ConnectService cs2 = ConnectService.getInstance();
		
		// 이렇게 새로운 객체를 생성하는 것을 막아야함
		// 생성자 메서드를 private로 돌려버림
		// 에러발생
		// ConnectService test = new ConnectService();
		
		if(cs1 == cs2) {
			System.out.println("같은 ConnectService 객체입니다.");
		} else {
			System.out.println("다른 ConnectService 객체입니다.");
		}
		
	}

}
