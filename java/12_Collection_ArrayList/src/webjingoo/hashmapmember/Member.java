package webjingoo.hashmapmember;

public class Member {
	private String memId;
	private String nickName;
	
	public Member(String memId, String nickName) {
		super();
		this.memId = memId;
		this.nickName = nickName;
	}

	public String getMemId() {
		return memId;
	}

	public String getNickName() {
		return nickName;
	}
	
	
	@Override
	public String toString() {
		return "Member [memId=" + memId + ", nickName=" + nickName + "]";
	}
}
