<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./get" method="get">
		<div>
			삼각형 밑변: <input type="text" name="width">
		</div>
		<div>
			삼각형 높이: <input type="text" name="height">
		</div>
		<button type="submit">넓이 구하기</button>
	</form>
	
	<form action="./get" method="post">
		<div>
			이름: <input type="text" name="name">
		</div>
		<div>
			아이디: <input type="text" name="id">
		</div>
		<div>
			비밀번호: <input type="text" name="pwd">
		</div>
		<button type="submit">유저정보 입력</button>
	</form>
</body>
</html>