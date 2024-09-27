package com.miniproject.persistence;

import com.miniproject.domain.AutoLoginInfo;
import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;

public interface MemberDAO {
	// 유저의 userPoint를 수정하는 메서드
	int updateUserPoint(String userId) throws Exception;

	int selectDuplicateId(String tmpUserId) throws Exception;

	int insertMember(MemberDTO member) throws Exception;

	// 로그인
	MemberDTO login(LoginDTO loginDTO) throws Exception;

	// 자동 로그인 정보 저장
	int updateAutoLoginInfo(AutoLoginInfo autoLoginInfo) throws Exception;

	MemberDTO checkAutoLogin(String savedCookieSesId) throws Exception;

	// 댓글작성으로 인한 유저의 point 수정
	int updateUserPointComment(String commenter) throws Exception;
}
