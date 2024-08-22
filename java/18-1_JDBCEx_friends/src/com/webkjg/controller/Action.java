package com.webkjg.controller;

import com.webkjg.controller.service.FriendManagermentService;
import com.webkjg.controller.service.ModifyFriendNameService;
import com.webkjg.controller.service.OutputEntireFriendService;
import com.webkjg.controller.service.SaveFriendService;
import com.webkjg.controller.service.SearchByNameService;

public class Action {

	// 싱글톤
	private static Action instance = null;

	private Action() {}

	public static Action getInstance() {
		if (instance == null) {
			instance = new Action();
		}

		return instance;
	}

	public FriendManagermentService getService(int menu) {

		FriendManagermentService result = null;

		switch (menu) {
		case 1:
			result = new OutputEntireFriendService();
			break;
		case 2:
			result = new SaveFriendService();
			break;
		case 3:
			result = new SearchByNameService();
			break;
		case 4:
			result = new ModifyFriendNameService();
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 9:
			System.exit(0);
			break;
		default:
			break;
		}

		return result;
	}
}
