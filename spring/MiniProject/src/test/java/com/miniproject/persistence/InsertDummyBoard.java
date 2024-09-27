package com.miniproject.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.miniproject.domain.HBoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class InsertDummyBoard {

	@Inject
	private HBoardDAO dao;
	
	@Test
	public void insertDummyDataBoard() throws Exception {
		
		for(int i=0; i<300; i++) {
			HBoardDTO dto = new HBoardDTO().builder()
					.title("dummy title" + i + "...")
					.content("dummy content")
					.writer("kildong")
					.build();
		
			if(dao.insertNewBoard(dto) == 1) {
				int newBoardNo = dao.selectMaxBoardNo();
				
				dao.updateBoardRef(newBoardNo);
			}
			
		}
	}
}
