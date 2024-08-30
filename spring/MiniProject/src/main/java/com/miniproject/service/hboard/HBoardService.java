package com.miniproject.service.hboard;

import java.util.List;

import com.miniproject.domain.HBoardVO;

public interface HBoardService {
	// 게시판 전체 리스트 조회
	List<HBoardVO> getAllBoard() throws Exception;
}
