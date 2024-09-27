package com.spring.persistance;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ExTxDAOImpl implements ExTxDAO {

	@Inject
	private SqlSession ses;

	private static String ns = "com.spring.mappers.";
	
	
	@Override
	public int insertDataTblA(String data) {
		return ses.insert(ns + "tableAMapper.insertData", data);
	}

	@Override
	public int insertDataTblB(String data) {
		return ses.insert(ns + "tableBMapper.insertData", data);
	}

}
