package webjingoo.loosecoupling;

public class Main {

	public static void main(String[] args) {
		
		Tv tv = new Tv("LG");
		
//		tv.powerOn();
		
		MultiRemoteController mrc = new MultiRemoteController();
		
		mrc.remoteControl(tv); // Tv Lg가 켜집니다.
		
		Washer washer = new Washer("삼성");
		
		mrc.remoteControl(washer);

	}

}
