package webjingoo;

public class TvMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TvData data = new TvData();

		data.isOn = true;
		
		data.volume++;
		System.out.println(data.volume);
		
		if(data.isOn) {
			System.out.println("티비가 켜져있습니다.");
		}
	}
}
