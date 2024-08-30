<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
				<li class="nav-item"><a class="nav-link active" href="/">webjingoo</a></li>
				<li class="nav-item"><a class="nav-link" href="/hboard/listAll">계층형 게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>