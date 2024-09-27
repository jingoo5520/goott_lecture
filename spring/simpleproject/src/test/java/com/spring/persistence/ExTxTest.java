package com.spring.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.service.ExTxService;
import com.spring.service.saveService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ExTxTest {
	
	@Inject
	private ExTxService service;
	
	@Inject
	private saveService saveService;
	
	
//	@Test
//	public void tranxTest() throws Exception {
//		service.saveData("abcdefg");
//	}
	
	
	@Test
	public void testPropagation() {
		saveService.insertIntoTables();
	}
}
