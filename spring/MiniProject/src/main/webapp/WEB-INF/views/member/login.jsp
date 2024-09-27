<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#rememberMe").click(function(){
			alert("공공장소 미사용 권장");
		});
	})
</script>
</head>
<body>

	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container mt-3">
		<h1>login.jsp</h1>

		<form action="/member/login" method="POST">
			<div class="mb-3 mt-3">
				<label for="userId" class="form-label">아이디: </label>
				<input type="text" class="form-control" placeholder="아이디를 입력하세요" name="userId" id="userId">
			</div>

			

			<div class="mb-3 mt-3">
				<label for="userPwd1" class="form-label">비밀번호: </label>
				<input type="password" class="form-control" placeholder="비밀번호를 입력하세요" name="userPwd" id="userPwd1">
			</div>
			
			<div><input type="checkBox" id="rememberMe" name="remember" />Remember Me</div>

			<button class="btn btn-success" type="submit">로그인</button>
			<button class="btn btn-secondary" type="reset">취소</button>
		</form>

	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

