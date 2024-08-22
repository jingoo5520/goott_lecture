
public class MobilePhoneMain {

	public static void main(String[] args) {
		MobilePhone phone = new MobilePhone();
		
		MobilePhone galaxy = new MobilePhone("삼성", "갤럭시S24", 256, "흰색" );
		System.out.println(galaxy.toString());
		
		MobilePhone iphone = new MobilePhone("애플", "아이폰15ProMax");
		System.out.println(iphone.toString());
		
		

	}

}
