<%@page import="com.jsp.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 기본 생성자를 이용해서 id속성 값의 이름으로 Bean(객체)를 생성 -->
	<jsp:useBean id="member1" class="com.jsp.dto.MemberDTO"></jsp:useBean>
	
	<jsp:setProperty property="userId" name="member1" value="hkd"/>
	<jsp:setProperty property="pwd1" name="member1" value="1234"/>
	<jsp:setProperty property="email" name="member1" value="hkd@abc.com"/>
	
	<%
		out.print(member1.toString());
	%>
	
	<div>아이디: <jsp:getProperty property="userId" name="member1"/></div>
	<div>이메일: <jsp:getProperty property="email" name="member1"/></div>
</body>
</html>