package webjingoo.vocabularybook;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import webjingoo.hashmapmember.Member;

public class MemberManager {
	private HashMap<String, Member> members;

	public MemberManager() {
		members = new HashMap<String, Member>();
	}
	
	public void addMember(Member member) {
		members.put(member.getMemId(), member);
	}
	
	public void delMember(String memId) {
		members.remove(memId);
	}
	
	public Member getMemberByMemberId(String memberId) {
		return members.get(memberId);
	}
	
	public Member getMemberByNickName(String name) {
		for(Member member : members.values()) {
			if(member.getNickName().equals(name));
			return member;
		}
		
		return null;
	}
	
	public void displayMembers() {
		for(Map.Entry<String, Member> entry : members.entrySet()) {
			System.out.println("memId: " + entry.getKey() + ", nickName: " + entry.getValue().getNickName());
		}
		
	}
}
