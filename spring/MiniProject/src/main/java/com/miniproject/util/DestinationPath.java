package com.miniproject.util;

import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.util.StringUtils;

// 로그인을 하지 않았을 때 로그인 페이지로 이동되기 전 원래 가려던 페이지 경로를 저장하는 객체
public class DestinationPath {
	
	private String destPath;
	
	public void setDestPath(HttpServletRequest req) {
		System.out.println("req.getQueryString(): " + req.getQueryString());
		destPath = req.getRequestURI();
		
		if(StringUtils.isNullOrEmpty(req.getQueryString())) {
			req.getSession().setAttribute("destPath", destPath);
		} else {
			req.getSession().setAttribute("destPath", destPath + "?" + req.getQueryString());
		}
	}

}
