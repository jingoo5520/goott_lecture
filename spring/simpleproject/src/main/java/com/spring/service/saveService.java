package com.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class saveService {
	
	@Inject
	private SaveTableAService tableAService;
	
	@Inject
	private SaveTableBService tableBService;
	
	@Transactional
	public void insertIntoTables() {
		tableAService.insertTableA();
		
		tableBService.insertTableB();
	}
}
