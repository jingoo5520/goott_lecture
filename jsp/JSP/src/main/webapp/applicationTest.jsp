<%@page import="java.util.HashMap"%>
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
	<h1>application 영역</h1>
	
	<%
	Map<String, ProductDTO> maps = new HashMap<String, ProductDTO>();
	maps.put("galaxy", new ProductDTO("갤럭시", 1, 1000, "블루"));
	maps.put("iphone", new ProductDTO("아이폰", 1, 2000, "레드"));
	
	application.setAttribute("phones", maps);
	%>
	
	<div><a href="appPage.jsp">appPage.jsp 이동</a></div>
</body>
</html>