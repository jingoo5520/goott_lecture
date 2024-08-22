<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Why use Servlet</title>
<script>
	function hello() {
		alert("하이");
	}
</script>
<style>
	h1 {
		background-color: yellow;
	}
</style>
</head>
<body>
	<h1 onclick="hello()">왜 Servlet을 사용하는가?</h1>
	<% 
		int num = 3;
		out.print(num);
		
		if(num % 2 == 0) {
		%>
		<div style="color: red">짝수입니다.</div>
		<%
		} else {
			%>
			<div style="color: blue">홀수입니다.</div>
			<%
		}
	%>
</body>
</html>

