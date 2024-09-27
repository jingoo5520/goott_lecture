<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(document).ready(
			function() {
				let currentPage = new URLSearchParams(window.location.search)
						.get("pagingSize") || 1;

				$(".sel").val(currentPage);

			});

	function onChange(value) {
		/* console.log(value);
		
		$.ajax({
			url : "/hboard/listAll?pageNo=1&pagingSize=40" ,
			type : "GET",
			success : function(data) {
				console.log("ok");
				

			},
			error : function(e) {
				console.log(e);
			},
			complete : function() {
			}
		}); */

		window.location.href = "/hboard/listAll?pageNo=1&pagingSize=" + value;
	}
</script>

<style>
</style>
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>

	<div class="container mt-3">
		<h2>댓글형 게시판 전체목록</h2>

		<div>
			<select class="form-select sel" onchange="onChange(this.value)">
				<option value="10">10개씩 보기</option>
				<option value="20">20개씩 보기</option>
				<option value="30">30개씩 보기</option>
				<option value="40">40개씩 보기</option>
			</select>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>boardNo</th>
					<th>title</th>
					<th>content</th>
					<th>writer</th>
					<th>ref</th>
					<th>step</th>
					<th>refOrder</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<c:choose>
						<c:when test="${board.isDelete != 'Y'}">
							<tr onclick="location.href='/cboard/viewBoard?boardNo=${board.boardNo}'">

								<td>${board.boardNo}</td>
								<td><c:if test="${board.step > 0}">
										<c:forEach var="i" begin="0" end='${board.step }' varStatus="status">
											<c:if test="${status.last }">
												<img src="/resources/images/arrow.png" width="20px" style="margin-left: calc(20px * ${i})" />
											</c:if>
										</c:forEach>
									</c:if> ${board.title}</td>
								<td>${board.content}</td>
								<td>${board.writer}</td>
								<td>${board.ref}</td>
								<td>${board.step}</td>
								<td>${board.refOrder}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${board.boardNo}</td>
								<td colspan="6" style="color: grey">삭제된 게시글입니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>

		<button type="button" class="btn btn-success" onclick="location.href='/cboard/showSaveBoardForm'">글쓰기</button>

		<!-- 검색 -->
		>
		<form action="/cboard/listAll" method="post">
			<div>
				<select class="form-select" id="searchType" name="searchType">
					<option value="">검색 타입</option>
					<option value="title">제목</option>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" placeholder="Search" id="searchWord" name="searchWord">
				<button class="btn btn-success" type="submit">검색</button>
			</div>
		</form>

		<!-- 페이지네이션 -->
		<div class="paging mt-2">
			<ul class="pagination">
				<c:choose>
					<c:when test="${param.pageNo == 1}">
						<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="#" onclick="location.href='/cboard/listAll?pageNo=${pagingInfo.pageNo - 1}&pagingSize=${pagingInfo.viewPostCntPerPage }&searchType=${search.searchType }&searchWord=${search.searchWord }'">Previous</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="${pagingInfo.startPageNoCurBloack}" end="${pagingInfo.endPageNoCurBlock}">
					<c:choose>
						<c:when test="${param.pageNo == i}">
							<li class="page-item active"><a class="page-link" href="#" onclick="location.href='/cboard/listAll?pageNo=${i}&pagingSize=${pagingInfo.viewPostCntPerPage }&searchType=${search.searchType }&searchWord=${search.searchWord }'">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="#" onclick="location.href='/cboard/listAll?pageNo=${i}&pagingSize=${pagingInfo.viewPostCntPerPage }&searchType=${search.searchType }&searchWord=${search.searchWord }'">${i}</a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>

				<c:choose>
					<c:when test="${param.pageNo == pagingInfo.totalPageCnt}">
						<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="#" onclick="location.href='/cboard/listAll?pageNo=${pagingInfo.pageNo + 1}&pagingSize=${pagingInfo.viewPostCntPerPage }&searchType=${search.searchType }&searchWord=${search.searchWord }'">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>