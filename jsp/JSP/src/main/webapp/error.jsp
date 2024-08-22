<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page isErrorPage = "true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외처리 페이지(공통 예외 처리 사용)</title>
</head>
<body>
<h1>에러 페이지</h1>
<h2>일시적인 오류 발생</h2>
<div>에러가 지속되면 연락주세요.</div>
<h3><%=exception.getClass() %></h3>
<h4><%=exception.getMessage() %></h4>
<div><%=exception.getStackTrace()[0] %></div>
<div><%=exception.getStackTrace()[1] %></div>
<div><%=exception.getStackTrace()[2] %></div>
</body>
</html>