<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
int globalCnt = 0;
%>


<%
int localCnt = 0;

out.print("<br> localCnt : ");
out.print(++localCnt);
out.print("<br> globalCnt : ");

out.print(++globalCnt);

%>
</body>
</html>