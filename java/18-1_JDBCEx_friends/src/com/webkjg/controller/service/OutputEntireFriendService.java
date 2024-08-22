package com.webkjg.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.webkjg.dao.FriendsMgmDAO;
import com.webkjg.dao.FriendsMgmDAOImpl;
import com.webkjg.vo.Friend;

public class OutputEntireFriendService implements FriendManagermentService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {
		System.out.println("전체 친구 조회");
		
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		
		List<Friend> list = dao.selectAllFriends();
		
		for(Friend f : list) {
			System.out.println(f.toString());
		}
		
	}
	
}
