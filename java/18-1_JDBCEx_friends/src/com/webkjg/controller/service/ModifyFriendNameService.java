package com.webkjg.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.webkjg.dao.FriendsMgmDAO;
import com.webkjg.dao.FriendsMgmDAOImpl;
import com.webkjg.dto.ModifyNameDto;
import com.webkjg.dto.SearchNameDTO;
import com.webkjg.view.FriendDBInsert;
import com.webkjg.vo.Friend;

public class ModifyFriendNameService implements FriendManagermentService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {
		System.out.println("친구 이름 수정하기");

		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();

		SearchNameDTO dto = FriendDBInsert.getSearchByNameData();

		List<Friend> list = dao.selectFriendByName(dto);

		for(Friend f : list) {
			System.out.println(f);
		}
		
		ModifyNameDto mDto = FriendDBInsert.getSearchByFriendNoData();
		
		if(dao.modifyFriendName(mDto) == 1) {
			System.out.println("친구 이름 수정 완료");
		}
		
	}

}
