<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
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
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>
	<div class="container">
		<h1>${hboard.boardNo}번 글에 대한 답글 작성</h1>
		
		<form action="saveReply" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">원본 글 번호</span>
				<input type="text" class="form-control" readonly="readonly" value="${hboard.boardNo} ">
				<span class="input-group-text">원본 글 제목</span>
				<input type="text" class="form-control" readonly="readonly"value="${hboard.title}">
			</div>
		
			<div class="input-group mb-3">
				<span class="input-group-text">글 제목</span>
				<input type="text" class="form-control" id="title" placeholder="글 제목을 입력하세요..." name="title">
			</div>
			
			<div class="mb-3">
				<label for="content">내용</label>
				<textarea class="form-control" rows="20" id="content" name="content" placeholder="내용을 입력해주세요..."></textarea>
			</div>
			
			<div>
				<input type="hidden" name="ref" value="${param.ref}" />
				<input type="hidden" name="step" value="${param.step}" />
				<input type="hidden" name="refOrder" value="${param.refOrder}" />
				
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span>
				<input type="text" class="form-control" placeholder="작성자를 입력하세요..." name="writer">
			</div>

			<button type="submit" class="btn btn-primary mb-3" onclick="">저장</button>

			<button type="reset" class="btn btn-warning mb-3" onclick="location.href='/hboard/viewBoard?boardNo=${hboard.boardNo}'">취소</button>
		</form>
	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>