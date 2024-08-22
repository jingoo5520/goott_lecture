public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private int memberAge;

	public Member(String memberId, String memberPwd, String memberName, int memberAge) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberAge = memberAge;
	}

	public boolean login(String memberId, String memberPwd) {
		if (this.memberId.equals(memberId) && this.memberPwd.equals(memberPwd)) {
			return true;
		} else {
			return false;
		}
	}
}
