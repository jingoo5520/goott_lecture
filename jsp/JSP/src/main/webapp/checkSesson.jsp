<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보</title>
</head>
<body>
<h1>세션 정보</h1>

<%
	String userId = (String) session.getAttribute("loginMemberId");

	out.print(userId);
	
	
	Enumeration<String> enm = session.getAttributeNames();
	String names = "";
	String values = "";
	
	while(enm.hasMoreElements()) {
		names = enm.nextElement();
		
		
		values = (String) session.getAttribute(names);
		out.print("속성이름: " + names + "<br>");
		out.print("속성 값: " + values + "<br>");
	}
	
	long createdTime = session.getCreationTime();
	long lastTime = session.getLastAccessedTime();
	
	out.print("세션 아이디:" + session.getId() + "<br>");
	out.print("세션에 머문 시간: " + (lastTime - createdTime) + "<br>");
	out.print("세션 유효 시간: " + session.getMaxInactiveInterval());
	
	
%>
</body>
</html>