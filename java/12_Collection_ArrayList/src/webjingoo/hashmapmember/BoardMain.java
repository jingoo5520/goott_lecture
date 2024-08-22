package webjingoo.hashmapmember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webjingoo.vocabularybook.Board;
import webjingoo.vocabularybook.MemberManager;

public class BoardMain {

	public static void main(String[] args) {
		// Member 클래스: id, nickName
		// Board 클래스: boardNo, title, content, writter
		// MemberManage 클래스
		// 회원 가입한 멤버들을 map에 넣어서 관리(key: userId, value: Member) 인스턴스
		// 회원 저장, 검색, 삭제 기능을 가짐
		
		// 회원이 게시글을 몇개를 작성
		// 출력: 게시판(글번호, 글쓴이, 제목, 내용)
		
		MemberManager mm = new MemberManager();
		
		mm.addMember(new Member("0001", "kildong"));
		mm.addMember(new Member("0002", "huidong"));
		mm.addMember(new Member("0003", "ddochi"));
		mm.addMember(new Member("0004", "dongdong"));
		
		mm.delMember("0004");
		
		mm.displayMembers();
		
		// memId로 member 찾기
		System.out.println(mm.getMemberByMemberId("0001"));
		
		// nickName으로 member 찾기
		System.out.println(mm.getMemberByNickName("ddochi"));
		
		
		List<Member> members = new ArrayList<Member>();
		members.add(new Member("0001", "kildong"));
		members.add(new Member("0002", "huidong"));
		
		List<Board> boards = new ArrayList<Board>();
		boards.add(new Board(1, "0001", "제목1", "내용1"));
		boards.add(new Board(2, "0002", "제목2", "내용2"));
		boards.add(new Board(3, "0001", "제목3", "내용3"));
		boards.add(new Board(4, "0002", "제목4", "내용4"));
		
		Map<String, List> memBoard = new HashMap<String, List>();
		memBoard.put("member", members);
		memBoard.put("board", boards);
		
		for(Board board : boards) {
			System.out.println("-----------------------------");
			System.out.println("글번호 : " + board.getBoardNo());
			System.out.println("제목 : " + board.getTitle());
//			System.out.println("글쓴이 : " + memBoard.get("member").;
			
			System.out.println("내용 : " + board.getContent());
		}
		
		
		
		
	}

}
