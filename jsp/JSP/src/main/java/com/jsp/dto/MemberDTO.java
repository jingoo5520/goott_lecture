package com.jsp.dto;

public class MemberDTO {
	private String userId;
	private String pwd1;
	private String email;

	public MemberDTO() {
	}

	public MemberDTO(String userId, String pwd1, String email) {
		super();
		this.userId = userId;
		this.pwd1 = pwd1;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getpwd1() {
		return pwd1;
	}

	public void setpwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", pwd1=" + pwd1 + ", email=" + email + "]";
	}
}
