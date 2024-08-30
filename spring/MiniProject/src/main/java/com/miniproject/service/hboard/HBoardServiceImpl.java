package com.miniproject.service.hboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.miniproject.domain.HBoardVO;
import com.miniproject.persistence.HBoardDAO;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 아래의 클래스가 서비스 객체임을 컴파일러에게 공지
public class HBoardServiceImpl implements HBoardService {

	@Inject
	private HBoardDAO bDao;
	
	@Override
	public List<HBoardVO> getAllBoard() throws Exception {

		System.out.println("HBoardServiceImpl.......");
		
		List<HBoardVO> list = bDao.selectAllBoard();
		
		return list;
	}

}
