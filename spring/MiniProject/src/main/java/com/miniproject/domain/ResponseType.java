package com.miniproject.domain;

public enum ResponseType {
	SUCCESS(200), FAIL(400);
	
	private int resultCode;
	
	ResponseType(int resultCode) { // 생성자(private)
		this.resultCode = resultCode;
	}
	
	public int getResultCode() {
		return this.resultCode;
	}
	
	public String getResultMessage() {
		return this.name();
	}
}
