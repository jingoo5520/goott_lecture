package com.miniproject.service.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.AutoLoginInfo;
import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;
import com.miniproject.domain.PointLogDTO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

@Service
public class memberServiceImpl implements MemberService {

	@Inject
	private MemberDAO mDao;
	
	@Inject
	private PointLogDAO pDao;
	
	@Override
	public boolean idIsDuplicate(String tmpUserId) throws Exception {
		boolean result = false;
		
		if(mDao.selectDuplicateId(tmpUserId) == 1) {
			result = true; // 중복
			
		} 
		
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean saveMember(MemberDTO registerMember) throws Exception {
		boolean result = false; 
		// hobbies 처리 - 하나의 문자열로 결합하여 처리(추가 테이블 생성 x)
		
		String tmphobby = "";
		
		for(int i = 0; i< registerMember.getHobbies().length; i++) {
			if(i == registerMember.getHobbies().length -1) {
				tmphobby += registerMember.getHobbies()[i];
				
			} else {
				tmphobby += registerMember.getHobbies()[i] + ", ";
			}
		}
		
		registerMember.setHobby(tmphobby);
		
		// 1) 회원 데이터를 DB에 저장(insert)
		if(mDao.insertMember(registerMember) == 1) {
			// 2) 가입한 회원에게 100 포인트 부여 - 로그 기록(insert)
			if(pDao.insertPointLog(new PointLogDTO(registerMember.getUserId(), "회원가입")) == 1) {
				
				result = true;
			}
		}
		
		return result;
	}

	// 로그인
	@Override
	public MemberDTO login(LoginDTO loginDTO) throws Exception {
		
		MemberDTO loginMember = mDao.login(loginDTO);
		
		return loginMember;
	}

	@Override
	public boolean saveAutoLoginInfo(AutoLoginInfo autoLoginInfo) throws Exception {
		boolean result = false;
		
		if(mDao.updateAutoLoginInfo(autoLoginInfo) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public MemberDTO checkAutoLogin(String savedCookieSesId) throws Exception {
		return mDao.checkAutoLogin(savedCookieSesId);
	}

}
