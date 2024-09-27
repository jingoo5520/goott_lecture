<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
.topHeader {
	background-image: url("/resources/images/backgroundImg.jpg");
	background-size: cover;
	height: 300px;
}
</style>
</head>
<body>
	<div class="p-5 bg-primary text-white text-center topHeader">
		<h1>My First Bootstrap 5 Page</h1>
		<p>Resize this responsive page to see the effect!</p>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active" href="/">HOME</a></li>
				<li class="nav-item"><a class="nav-link" href="/hboard/listAll?pageNo=1&pagingSize=10">계층형 게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="/cboard/listAll">댓글형 게시판</a></li>

				<c:choose>
					<c:when test="${loginMember == null }">
						<li class="nav-item"><a class="nav-link" href="/member/register">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" href="/member/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<a href="/member/myPage" class="nav-link">${loginMember.userName } 님</a>
						<li class="nav-item"><a class="nav-link" href="/member/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>


		</div>
	</nav>
</body>
</html>
