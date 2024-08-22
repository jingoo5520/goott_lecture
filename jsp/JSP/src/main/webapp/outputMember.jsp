<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>${member}</div>
	<hr/>
	
	<div>아이디: ${member.userId}</div>
	<div>생년월일: ${member.birth}</div>
	<div>취미: ${member.hobby}</div>
</body>
</html>