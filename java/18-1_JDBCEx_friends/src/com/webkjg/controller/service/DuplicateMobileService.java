package com.webkjg.controller.service;

import java.sql.SQLException;

import com.webkjg.dao.FriendsMgmDAO;
import com.webkjg.dao.FriendsMgmDAOImpl;

public class DuplicateMobileService {
	
	private static DuplicateMobileService instance = null;
	
	public static DuplicateMobileService getInstance() {
		if(instance == null) {
			instance = new DuplicateMobileService();
		}
		
		return instance;
	}
	
	public boolean duplicateMobileService(String mobile) throws ClassNotFoundException, SQLException {
		boolean result = false;
		
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		
		if(dao.isDuplecateMobile(mobile) == 1) {
			result = true;
		}
		
		return result;
	}
	
}
