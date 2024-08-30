package com.spring.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.MemberDTO;

//아래의 클래스가 DAO 객체임을 명시
@Repository 
public class MemberDAOImpl implements MemberDAO {

	private static String ns = "com.spring.mappers.memberMapper.";
	
	@Inject
	private SqlSession ses; // sqlSessionTemplate 객체 주입
	
	@Override
	public String getDateTime() {
		
		String result = ses.selectOne(ns + "getDateTime");
		
		return result;
	}

	@Override
	public int insertMember(MemberDTO dto) throws Exception {
		
		int result = ses.insert(ns + "insertMember", dto);
		
		return result;
	}

	@Override
	public int updateMember(MemberDTO dto) throws Exception {
		return ses.update(ns + "updateMember", dto);
	}

	@Override
	public int deleteMember(String userId) throws Exception {
		
//		List<MemberDTO> list = ses.selectList(ns + "viewAllSelector");
		
//		System.out.println(list);
		
		return ses.delete(ns + "deleteMember", userId);
	}

	@Override
	public List<MemberDTO> selectAllMembers() throws Exception {
		
		List<MemberDTO> list = ses.selectList(ns + "viewAllMembers");
		
		return list;
	}

	@Override
	public MemberDTO selectMemberByUserId(String userId) throws Exception {
		return ses.selectOne(ns + "viewMemberByUserId", userId);
	}

	@Override
	public MemberDTO loginMember(String userId, String userPwd) throws Exception {
		
		// 여러개의 파라미터는 Map으로 감싸서 넘길 수 있음
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("userPwd", userPwd);
		
		
		return ses.selectOne(ns+ "loginMember",  param);
	}
	
}
