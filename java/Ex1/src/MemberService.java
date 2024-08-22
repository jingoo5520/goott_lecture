import java.util.Scanner;

public class MemberService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Member member1 = new Member("jingoo5520", "1234", "강진구", 29);
		
		System.out.print("ID: ");
		String memberId = sc.nextLine();
		
		System.out.print("PWD: ");
		String memberPwd = sc.nextLine();

		boolean result =  member1.login(memberId, memberPwd);
		
		if(result) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("아이디 혹은 비밀번호가 틀립니다.");
		}
	}

}
