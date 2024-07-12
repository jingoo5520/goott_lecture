package webjingoo;

public class Tv {
	boolean isOn = false;
	int volume = 0;
	
	void on() {
		isOn = true;
		System.out.println("티비를 켭니다.");
	}
	
	void off() {
		isOn = false;
		System.out.println("티비를 끕니다.");
	}
	
	void volumeUp() {
		volume++;
		System.out.println("볼륨 증가: " + volume);
	}
	
	void volumeDown() {
		volume--;
		System.out.println("볼륨 감소: " + volume);
	}
}
