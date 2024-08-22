package webjingoo.tightcoupling;

public class Tv {
	private String brandName;

	Tv(String brandName) {
		this.brandName = brandName;
	}

	public void powerOn() {
		System.out.println("TV on");
	}

	public void powerOff() {
		System.out.println("TV off");
	}
}
