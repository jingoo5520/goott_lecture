package com.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.persistance.ExTxDAO;

@Service
public class ExTxServiceImpl implements ExTxService {

	@Inject
	private ExTxDAO dao;
	
	@Override
	@Transactional(rollbackFor = Exception.class) 
	// runtimeException 발생시 rollack 자동 실행, 타 Exception 발생시 rollback 미실행
	// (rollbackFor = Exception.class) 어노테이션 추가를 통해 어떤 Exception 발생시에도 rollback 실행
	public void saveData(String data) throws Exception {
		if(dao.insertDataTblA(data) == 1) {
			System.out.println("table A에 저장 성공");
		} else {
			System.out.println("table A에 저장 실패");
		}
		
//		if(dao.insertDataTblB(data) == 1) {
//			System.out.println("table B에 저장 성공");
//		} else {
//			System.out.println("table b에 저장 실패");
//		}
		
		
//		System.out.println("RuntimeException 예외 발생!");
//		throw new RuntimeException();
		// rollback; 실행
		
		
//		System.out.println("Exception 예외 발생!");
//		throw new Exception();
		// rollback; 미실행
		
	}
	
	
	
	
}
