<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<meta charset="UTF-8">
<title>Save Board.jsp</title>
<script>
	$(function() {
		$('#summernote').summernote({
			maximumImageFileSize : 1024 * 1024 * 10,
			placeholder : 'Hello Bootstrap 5',
			tabsize : 2,
			height : 100
		});
	});

	function invalidTitle() {
		console.log("타이틀검사")
		let result = false;
		let title = $("#title").val();

		if (title == '') {
			console.log(title);
			$("#title").text('제목은 반드시 입력해야 한다.');
			$("#title").focus();

		} else {
			result = true;
		}

		return result;
	}
</script>
<style>
.fileUploadArea {
	width: 100%;
	height: 200px;
	background-color: lightgray;
	text-align: center;
	line-height: 200px;
}
</style>

</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>
	<div class="container">
		<div>${board }</div>
		<h1>게시글 수정</h1>
		<form action="modifyBoardSave" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">글 번호</span>
				<input type="text" class="form-control" id="boardNo" value="${board.boardNo }" name="boardNo" readonly>
			</div>
		
		
			<div class="input-group mb-3">
				<span class="input-group-text">글 제목</span>
				<input type="text" class="form-control" id="title" value="${board.title }" name="title">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span>
				<input type="text" class="form-control" value="${loginMember.userId }" name="writer" value="${sessionScope.loginMember.userId}" readonly>
			</div>

			<div>
				<textarea id="summernote" name="content">${board.content }</textarea>
			</div>

			<button type="submit" class="btn btn-primary mb-3" onclick="return invalidTitle();">저장</button>

			<button type="reset" class="btn btn-warning mb-3" onclick="location.href='/cboard/viewBoard?boardNo=${board.boardNo}'">취소</button>
		</form>
	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>

</body>
</html>