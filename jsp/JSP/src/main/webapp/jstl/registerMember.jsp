<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Bean 객체 생성 -->


<jsp:useBean id="member" class="com.jsp.dto.MemberDTO"></jsp:useBean>
<%-- <jsp:setProperty property="userId" name="member"></jsp:setProperty> --%>

<jsp:setProperty property="*" name="member"/>

<div>아이디: <jsp:getProperty property="userId" name="member"/> </div>


</body>
</html>