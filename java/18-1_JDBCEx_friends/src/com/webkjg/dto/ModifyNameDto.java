package com.webkjg.dto;

public class ModifyNameDto {
	private int friendNo;
	private String friendName;
	
	public ModifyNameDto(int friendNo, String friendName) {
		super();
		this.friendNo = friendNo;
		this.friendName = friendName;
	}

	public int getFriendNo() {
		return friendNo;
	}

	public String getFriendName() {
		return friendName;
	}
}
