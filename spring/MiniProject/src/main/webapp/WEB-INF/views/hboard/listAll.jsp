<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</style>
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>

	<%-- <div>${boardList }</div> --%>

	<div class="container mt-3">
	<h1>listAll.jsp</h1>
		<h2>계층형 게시판 전체목록</h2>
		<p>The .table-striped class adds zebra-stripes to a table:</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>boardNo</th>
					<th>title</th>
					<th>content</th>
					<th>writer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.boardNo}</td>
						<td>${board.title}</td>
						<td>${board.content}</td>
						<td>${board.writer}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<button type="button" class="btn btn-success">글쓰기</button>
	</div>
	
	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>