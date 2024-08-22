package com.webkjg.dao;

import java.sql.SQLException;
import java.util.List;

import com.webkjg.dto.FriendDTO;
import com.webkjg.dto.ModifyNameDto;
import com.webkjg.dto.SearchNameDTO;
import com.webkjg.vo.Friend;

public interface FriendsMgmDAO {
	List<Friend> selectAllFriends() throws ClassNotFoundException, SQLException;
	
	int isDuplecateMobile(String mobile) throws ClassNotFoundException, SQLException;

	int getNextFriendNo() throws ClassNotFoundException, SQLException;

	int insertFriend(int friendNo, FriendDTO friendDTO) throws ClassNotFoundException, SQLException;

	List<Friend> selectFriendByName(SearchNameDTO dto) throws ClassNotFoundException, SQLException;
	
	int modifyFriendName(ModifyNameDto dto) throws ClassNotFoundException, SQLException;
}
