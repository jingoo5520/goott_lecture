<%@page import="java.util.Set"%>
<%@page import="com.jsp.dto.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 영역</title>
</head>
<body>
	<h1>application 영역에서 바인딩한 객체 읽어오기</h1>
	
	<%
		Map<String, ProductDTO> phones = (Map<String, ProductDTO>) application.getAttribute("phones");
	
		Set<String> keys = phones.keySet();
		
		for(String key : keys) {
			out.print(phones.get(key));
		}
	%>
</body>
</html>