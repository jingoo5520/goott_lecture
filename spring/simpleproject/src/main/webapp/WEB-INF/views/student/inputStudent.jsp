<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>inputStudent.jsp</h1>
	
	<!-- 
		post 방식 요청 방법
		ajax, form
	 -->
	<form action="saveStudent" method="post">
		<div>학번: <input type="text" name="stuNo" /></div>
		<div>이름: <input type="text" name="stuName" /></div>
		<div><input type="submit"  value="저장"/></div>
	</form>
</body>
</html>