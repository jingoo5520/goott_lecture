<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/02b6277f46.js" crossorigin="anonymous"></script>
<script>
	let pageNo = 1;

	$(function() {
		getAllComments(pageNo);
		getLikers();
		
		$(".fa-heart").click(function(){
			if($(this).attr("id") == "dislike"){
				$(this).attr("id", "like");
				$(this).removeClass("fa-regular").addClass("fa-solid");
			} else if($(this).attr("id") == "like"){
				$(this).attr("id", "dislike");
				$(this).removeClass("fa-solid").addClass("fa-regular");
			}
			
			sendBoardLike($(this).attr("id"));
		})
		
	});
	
	// 보류
	function getLikers(boardNo){
		$.ajax({
			url : "/cboard/getBoardlikers",
			type : "GET",
			data: {
				"boardNo": boardNo,
			},
			dataType: "text",
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			},
			complete : function() {
			}
		});
	}
	
	// 좋아요 업데이트
	function sendBoardLike(doesLike){
		let who = preAuth(); // 로그인한 유저 id
		
		let boardNo = '${param.boardNo}';
		
		$.ajax({
			url : "/cboard/boardlike",
			type : "POST",
			data: {
				"who": who,
				"boardNo": boardNo,
				"like": doesLike,
			},
			dataType: "text",
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			},
			complete : function() {
			}
		});
	}
	

	// 페이지 로딩 완료 시 댓글 불러오기 요청
	function getAllComments(pageNo) {
		$.ajax({
			url : "/comment/all/${param.boardNo}/" + pageNo,
			type : "GET",
			dataType : "json",
			success : function(data) {
				console.log(data);
				// displayAllComments(data);
				
				if(data.resultCode == 200 || data.resultMessage == 'SUCCESS'){
					outputComments(data);			
					outputPagination(data);
					
				}
			},
			error : function(e) {
				console.log(e);
			},
			complete : function() {
			}
		});
	}
	
	function outputComments(comments) {
	      let output = '<div class="list-group">'; 
	      
	      if (comments.data.list.length == 0) {
	         output += `<div class="empty">`; 
	         output += `<div>댓글이 비었습니다. 첫번째 댓글을 달아주세요.</div>`;
	         output += `</div>`;
	      } else {
	         $.each(comments.data.list, function(i, item) {
	            output += `<div class="list-group-item comment" id="comment_\${item.commentNo}">`;
	               output += `<div class="commentBody">`;
	                  output += `<div class="commenterProfile">`;
	                     output += `<img src='/resources/userImg/\${item.userImg}' width="30px"`;
	                  output += `</div>`;
	                  
	                  output += `<div class="commentBodyArea">`;
	                     output += `<div class="commentHeader">`;
	                        output += `<div class="commentContent">\${item.content}`;
	                           if (item.commenter != '${loginMember.userId}') {
	                              // 작성자와 로그인유저가 같지 않은 경우
	                              output += `<div class="commentBtns"></div>`;
	                           } else if (item.commenter == '${loginMember.userId}') {
	                              output += `<div class="commentBtns">`;
	                              // output += `<img src="/resources/images/modify.png" onclick="modifyComment(\${item.commentNo});" width="25px;"/>`;
	                              output += `<img src="/resources/images/remove.png" onclick="removeComment(\${item.commentNo});" width="25px;"/>`;
	                              output += `</div>`;
	                           }
	                           output += `</div>`;
	                        
	                           output += `<div class="commentInfo">`;
	                              let elapsedTime = processPostDate(item.regDate);
	                              output += `<div><span class="badge bg-secondary">\${elapsedTime}</span></div>`;
	                              output += `<div class="commenter">\${item.commenter}</div>`;
	                           output += `</div>`;
	                        output += `</div>`;
	                     output += `</div>`;
	                  output += `</div>`;
	               output += `</div>`;
	            output += `</div>`;
	         });
	      }
	      output += '</div>'; 
	      
	      
	      $(".commentList").html(output);
	   }
	
	// 댓글 페이징
	function outputPagination(comments){
		console.log(pageNo);
		let pagingInfo = comments.data.pi;
		let output = `<ul class="pagination justify-content-center" style="margin:20px 0">`;
		
		console.log(pagingInfo);
		
		if(pageNo > 1){
			output += `<li class="page-item"><a class="page-link" onclick="getAllComments(--pageNo)">Previous</a></li>`;	
		} else if(pageNo == 1){
			output += `<li class="page-item disabled"><a class="page-link">Previous</a></li>`
		}
		
		for(let i = pagingInfo.startPageNoCurBloack; i <= pagingInfo.endPageNoCurBlock; i++){
				output += `<li class="page-item \${i == pageNo ? 'active' : ''}"><a class="page-link" onclick="getAllComments(\${i})">\${i}</a></li>`	
		}

		if(pageNo < pagingInfo.totalPageCnt){
			output += `<li class="page-item"><a class="page-link" onclick="getAllComments(++pageNo)">Next</a></li>`;
		} else if(pageNo == pagingInfo.totalPageCnt){
			output += `<li class="page-item disabled"><a class="page-link">Next</a></li>`
		}
		
		output += `</ul>`;
		
		$(".commentPagination").html(output);
	}
	
	
	//<ul class="pagination justify-content-center" style="margin:20px 0">
	 // 
	// </ul>
	
	
	

	function displayAllComments(json) {
		let output = `<ul class="list-group">`;
		
		if (json.length > 0) {
			$.each(json, function(i, elt) {
				
				let elapsedTime = processPostDate(elt.regDate);

				output += `<li class="list-group-item">`;
				output += `<div>\${elt.content}</div>`;
				output += `<div><span>\${elt.commenter}</span>`;

				output += `<span>\${elapsedTime}</span>`
				output += `</div>`;
				output += `</li>`;

			})
		}

		output += `</ul>`;

		$(".commentList").html(output);
	}

	function processPostDate(regDate) {
		// 댓글 작성일시를 방금전, n분 전, n시간 전... 의 형식으로 출력

		const date = new Date(regDate);
		let now = new Date();
		let dif = (now.getTime() - date.getTime()) / 1000; 
		
		if(dif < 60) {
			return "방금 전";	
		} else if (dif < 60 * 60) {
			return Math.floor(dif / 60) + "분 전";
		} else if (dif <  60 * 60 * 24) {
			return Math.floor(dif / 60 / 60) + "시간 전";
		} else if (dif <  60 * 60 * 24 * 30) {
			return Math.floor(dif / 60 / 60 / 24) + "일 전";
		} else {
			return Math.floor(dif / 60 / 60 / 24 / 30) + "달 전";
		}
	}
	
	function saveComment(){
		// 댓글 저장		
		let boardNo = $("#boardNo").val();
		let content = $("#commentContent").val();
		// let commenter = '${loginMember.userId}';
		
		let commenter = preAuth();
		
		console.log(boardNo);
		console.log(content);
		console.log(commenter);
		
		const newComment = {
			'boardNo' : boardNo,
			'content' : content,
			'commenter' : commenter
		}
		
		// 내용이 없는 경우
		if(content.length < 1){
			alert("댓글 내용을 입력하세요");
			return;
		} else if(content.length >= 1 && commenter != null) {
			$.ajax({
				url: "/comment/" + boardNo,
				type: "POST",
				data: JSON.stringify(newComment),
				headers: {
					"Content-Type": "application/json"
				},
				dataType : "json",
				success : function(data) {
					console.log(data);
					
					if(data.msg == 'success'){
						$("#commentContent").val("");
						getAllComments(1);
					}
					
					
					
				},
				error : function(e) {
					console.log(e);
				},
				complete : function() {
				}
			});
		}
		
	}

	// 로그인이 된지 확인
	function preAuth(){
		let commenter = '${loginMember.userId}';
		
		if(commenter == ''){
			location.href = `/member/login?redirectUrl=viewBoard&boardNo=${param.boardNo}`;	
		} else {
			return '${loginMember.userId}'; 
		}
	}
</script>
<style>
.commenterProfile {
	display: flex;
	flex-direction: row;
}
</style>
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>
	<div class="container">
		<div>${board }</div>


		<h1>게시글 조회</h1>
		<div class="input-group mb-3">
			<span class="input-group-text">글 번호</span>
			<input type="text" class="form-control" id="boardNo" name="boardNo" readonly="readonly" value="${board.boardNo }">
			<span class="input-group-text">글 제목</span>
			<input type="text" class="form-control" id="title" name="title" readonly="readonly" value="${board.title }">
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">작성자</span>
			<input type="text" class="form-control" name="writer" readonly="readonly" value="${board.writer }">
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">작성자 이메일</span>
			<input type="text" class="form-control" name="writer" readonly="readonly" value="${board.email }">
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">조회수</span>
			<input type="text" class="form-control" name="readCount" value='${board.readCount}' readonly />
		</div>





		<div>${peopleLike.size()}</div>

		<div class="mb-3 mt-3">
			이 글을
			<span>
				<c:if test="${not empty peopleLike}">
					<c:if test="${peopleLike.size() le 3}">
						<c:forEach var="who" items="${peopleLike }" varStatus="status">
							<c:if test="${status.first }">${who }님 </c:if>
							<c:if test="${not status.first }">, ${who }님 </c:if>
						</c:forEach>이 좋아합니다.
					</c:if>

					<c:if test="${peopleLike.size() ge 4}">
						<c:forEach var="who" items="${peopleLike }" begin="1" end="3">
							${who }님
						</c:forEach>
						외 <span>${peopleLike.size() - 3}명이 좋아합니다.</span>
					</c:if>
				</c:if>
			</span>
		</div>

		<div>
			<c:set var="stop" value="false" />
			<c:forEach var="who" items="${peopleLike }">
				<c:if test="${who == loginMember.userId }">
					<i id="like" class="fa-solid fa-heart"></i>
					<c:set var="stop" value="true" />
				</c:if>
			</c:forEach>
			<c:if test="${stop == false }">
				<i id="dislike" class="fa-regular fa-heart"></i>
			</c:if>
		</div>



		<div class="mb-3">
			<label for="content">내용: </label>
			<div id="content" class="content">${board.content }</div>
		</div>

		<button type="button" class="btn btn-primary mb-3" onclick="location.href='/cboard/modifyBoard?boardNo=${board.boardNo}'">수정</button>
		<button type="button" class="btn btn-warning mb-3" data-bs-toggle="modal" data-bs-target="#myModal" onclick="">삭제</button>
		<button type="button" class="btn btn-secondary mb-3" onclick="location.href='cboard/listAll'">목록으로 돌아가기</button>

		<div class="commentInputArea">
			<input type="text" class="form-control" id="commentContent" placeholder="댓글을 입력하세요." />
			<img src="/resources/images/comment.png" width="20px" onclick="saveComment();" />

		</div>

		<div class="commentList"></div>
		<div class="commentPagination"></div>



	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>

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
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="location.href='/cboard/deleteBoard?boardNo=${param.boardNo}'">삭제</button>
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>