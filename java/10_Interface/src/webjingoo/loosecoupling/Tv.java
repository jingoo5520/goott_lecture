package webjingoo.loosecoupling;

public class Tv implements ElectronicDevice {
	private String brandName;
	
	public Tv(String brandName) {
		this.brandName = brandName;
	}
	
	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("Tv on");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Tv[" + brandName + "]";
	}
}
