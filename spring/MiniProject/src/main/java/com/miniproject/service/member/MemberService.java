package com.miniproject.service.member;

import com.miniproject.domain.AutoLoginInfo;
import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;

public interface MemberService {
	// 아이디 중복 검사
	boolean idIsDuplicate(String tmpUserId) throws Exception;

	// 회원가입
	boolean saveMember(MemberDTO registerMember) throws Exception;

	// 로그인
	MemberDTO login(LoginDTO loginDTO) throws Exception;

	boolean saveAutoLoginInfo(AutoLoginInfo autoLoginInfo) throws Exception;

	// 자동 로그인 체크 유저 확인
	MemberDTO checkAutoLogin(String savedCookieSesId) throws Exception;;
}
