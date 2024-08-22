<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		당신이 좋아하는 계절은
		<%=request.getParameter("favSeason")%>
		입니다.
	</div>

	<div>
		별명:
		<%=request.getParameter("nickName")%></div>

	<hr>

	<div>${param.nickName }님이좋아하는 계절은 ${paramValues.favSeason[0]}, ${paramValues.favSeason[1]} 입니다.</div>
</body>
</html>