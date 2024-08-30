package com.miniproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.HBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HBoardDAOImpl implements HBoardDAO {

	@Inject
	private SqlSession ses;

	private static String ns = "com.miniproject.mappers.hboardMapper.";
	
	@Override
	public List<HBoardVO> selectAllBoard() throws Exception {

		
		return ses.selectList(ns + "getAllBoard");
	}

}
