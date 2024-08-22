package webjingoo.loosecoupling;

public class Washer implements ElectronicDevice {
	private String brandName;
	
	Washer(String brandName){
		this.brandName = brandName;
	}
	
	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("Washer on");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "washer[" + brandName + "]";
	}
}
