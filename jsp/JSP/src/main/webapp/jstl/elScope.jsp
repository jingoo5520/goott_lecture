<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>elScope.jsp</h1>
<h2>바인딩 시킨 영역으로 접근하여 출력</h2>
<div>member: ${requestScope.member}</div>
<div>member.userId: ${requestScope.member.userId}</div>
<div>member.pwd1: ${requestScope.member.pwd1}</div>
<div>member.email: ${requestScope.member.email}</div>

<div>Session result: ${sessionScope.result }</div>
<div>request result: ${requestScope.result }</div>
</body>
</html>