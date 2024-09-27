<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goListAll() {
		location.href = "/hboard/listAll";
	}
</script>
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>
	<div class="container">

		<h1>게시글 조회</h1>
		<c:forEach var="hboard" items="${hboardList }">
			<div class="input-group mb-3">
				<span class="input-group-text">글 번호</span>
				<input type="text" class="form-control" id="boardNo" name="boardNo" readonly="readonly" value="${hboard.boardNo }">
				<span class="input-group-text">글 제목</span>
				<input type="text" class="form-control" id="title" name="title" readonly="readonly" value="${hboard.title }">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span>
				<input type="text" class="form-control" name="writer" readonly="readonly" value="${hboard.writer }">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">작성자 이메일</span>
				<input type="text" class="form-control" name="writer" readonly="readonly" value="${hboard.email }">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">조회수</span>
				<input type="text" class="form-control" name="readCount" value='${hboard.readCount}' readonly />
			</div>

			<div class="mb-3">
				<label for="content"></label>
				<textarea class="form-control" rows="20" id="content" name="content" readonly="readonly">
			${hboard.content }
			</textarea>
			</div>

			<div id="preview">
				<c:forEach var="file" items='${hboard.fileList}'>
					<c:choose>
						<c:when test='${file.thumbFileName != null}'>
							<div>
								<img src='/resources/boardUpFiles${file.thumbFileName}' />
								<p>${file.originFileName}</p>
							</div>
						</c:when>
						<c:when test="${empty file.newFileName }">
							<div></div>
						</c:when>
						<c:otherwise>
							<a href='/resources/boardUpFiles${file.originFileName}'>
								<div>
									<img src='/resources/images/noimg.png' />
									<p>${file.originFileName}</p>
								</div>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</div>

			<button type="button" class="btn btn-danger mb-3" onclick="location.href= '/hboard/showReplyForm?ref=${hboard.ref}&step=${hboard.step}&refOrder=${hboard.refOrder}&boardNo=${hboard.boardNo }'">답글작성</button>
			<button type="button" class="btn btn-primary mb-3" onclick="location.href='/hboard/modifyBoard?boardNo=${hboard.boardNo}'">수정</button>
			<button type="button" class="btn btn-warning mb-3" data-bs-toggle="modal" data-bs-target="#myModal" onclick="">삭제</button>
			<button type="button" class="btn btn-secondary mb-3" onclick="goListAll();">목록으로 돌아가기</button>

		</c:forEach>

		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">삭제</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">삭제하시겠습니까?</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="location.href='/hboard/deleteBoard?boardNo=${param.boardNo}'">삭제</button>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>