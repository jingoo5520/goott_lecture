package com.webkjg.controller.service;

import java.sql.SQLException;

import com.webkjg.dao.FriendsMgmDAO;
import com.webkjg.dao.FriendsMgmDAOImpl;
import com.webkjg.dto.FriendDTO;
import com.webkjg.view.FriendDBInsert;

public class SaveFriendService implements FriendManagermentService {

	@Override
	public void toDo() {
		System.out.println("친구 저장하기");

		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();

		// 친구정보
		FriendDTO friendDTO = FriendDBInsert.getFriendData();

		// friendNo 값
		try {
			int friendNo = dao.getNextFriendNo();
			if (dao.insertFriend(friendNo, friendDTO) == 1) {
				System.out.println("친구 저장 완료");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
