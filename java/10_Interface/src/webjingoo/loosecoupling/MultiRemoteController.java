package webjingoo.loosecoupling;

public class MultiRemoteController implements Remotable  {
	
	@Override
	public void remoteControl(ElectronicDevice ed){
		System.out.println(ed.toString() + " 제어합니다.");
		ed.powerOn();
	}
	
}
