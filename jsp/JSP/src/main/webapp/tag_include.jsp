<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "header.jsp" %>

hello, jsp
<%
	/* header.jsp에 a 변수가 이미 선언되어 있기 때문에 재 선언 불가 */
	/* int a = 50; */
	
	out.print(Math.floor(3.15));
	
	
%>

<%=a %>

<%@ include file = "footer.jsp" %>
</body>
</html>