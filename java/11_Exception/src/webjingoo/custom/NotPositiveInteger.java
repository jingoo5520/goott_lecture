package webjingoo.custom;

public class NotPositiveInteger extends Exception {
	private int errorCode = 103;
	private String msg;

	// 생성자
	public NotPositiveInteger(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return "에러코드: " + errorCode + ", " + super.getMessage();
	}
}
