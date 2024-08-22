<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본태그 - scriptlet</title>
</head>
<body>
	<%
	int num1 = 3, num2 = 5;
	int result = num1 + num2;

	out.print("<div>" + result + "</div>");
	%>
</body>
</html>