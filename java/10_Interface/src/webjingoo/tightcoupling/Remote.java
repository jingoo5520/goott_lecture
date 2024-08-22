package webjingoo.tightcoupling;

public class Remote {
	private Tv tv;
	
	public Remote() {
		tv = new Tv("삼성");
	}
	
	public void turnOnTv() {
		tv.powerOn();
	}
	
	public void turnOffTv() {
		tv.powerOff();
	}
}
