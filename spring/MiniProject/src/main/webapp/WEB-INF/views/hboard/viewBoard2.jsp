<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

		<div class="mb-3">
			<label for="content"></label>
			<textarea class="form-control" rows="20" id="content" name="content" readonly="readonly">
			${hboard.content }
			</textarea>
		</div>

		<div id="preview">
			<c:forEach var="file" items="${hboard.fileList}">
				
				<%-- ${fn:substring(file.thumbFileName, fn:lastIndexOf(file.thumbFileName, '\\') + 1, fn:length(file.thumbFileName))} --%>
				
				<c:if test="${file.thumbFileName == null}">
					<span>${file.newFileName}</span>
					<img src="/resources/images/noimg.png" width='200px' />
				</c:if>
				
				<c:if test="${file.thumbFileName != null}">
					<div>
						<span>${file.newFileName}</span>
						<img src="/resources/boardUpFiles${file.newFileName}" width='200px'  />
					</div>
				</c:if>



			</c:forEach>

			<%-- <c:when test="${not empty file.newFileName}">
            <!-- newFileName이 null이 아닌 경우 -->
            <div>
                /resources/boardUpFiles${file.newFileName}
                <img src="/resources/boardUpFiles${file.newFileName}" />
            </div>
        </c:when> --%>


		</div>

		<button type="button" class="btn btn-primary mb-3" onclick="">수정</button>
		<button type="button" class="btn btn-warning mb-3" onclick="">삭제</button>
		<button type="button" class="btn btn-secondary mb-3" onclick="goListAll();">목록으로 돌아가기</button>
	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>