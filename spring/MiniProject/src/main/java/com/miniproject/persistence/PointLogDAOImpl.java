package com.miniproject.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.PointLogDTO;

@Repository
public class PointLogDAOImpl implements PointLogDAO {
	@Autowired
	private SqlSession ses;
	String ns = "com.miniproject.mappers.pointLogMapper.";

	@Override
	public int insertPointLog(PointLogDTO pointLogDTO) throws Exception {

		// return ses.insert(ns + "insertPointLog", pointLogDTO);
		return ses.insert(ns + "insertPointLogSelectKey", pointLogDTO);
	}
	
	

}