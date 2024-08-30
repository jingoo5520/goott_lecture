package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.HBoardVO;

public interface HBoardDAO {
	List<HBoardVO> selectAllBoard() throws Exception;
}
