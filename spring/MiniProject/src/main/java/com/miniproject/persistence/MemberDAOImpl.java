package com.miniproject.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.AutoLoginInfo;
import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession ses;
	
	String ns = "com.miniProject.mappers.memberMapper.";

	@Override
	public int updateUserPoint(String userId) {
		return ses.update(ns + "updateUserPoint", userId);
	}

	@Override
	public int selectDuplicateId(String tmpUserId) throws Exception {
		return ses.selectOne(ns + "selectUserId", tmpUserId);
	}

	@Override
	public int insertMember(MemberDTO registerMember) throws Exception {

		return ses.insert(ns + "insertMember", registerMember);
	}

	@Override
	public MemberDTO login(LoginDTO loginDTO) throws Exception {
		return ses.selectOne(ns + "loginWithLoginDTO", loginDTO);
	}

	@Override
	public int updateAutoLoginInfo(AutoLoginInfo autoLoginInfo) throws Exception {
		return ses.selectOne(ns + "updateAutoLoginInfo", autoLoginInfo);
	}

	@Override
	public MemberDTO checkAutoLogin(String savedCookieSesId) {

		
		return ses.selectOne(ns + "checkAutoLoginUser",savedCookieSesId);
	}

	@Override
	public int updateUserPointComment(String commenter) throws Exception {
		return ses.update(ns + "updateUserPointComment", commenter);
		
	}

}