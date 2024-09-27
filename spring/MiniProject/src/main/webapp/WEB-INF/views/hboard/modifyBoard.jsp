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
<script>
	function goListAll() {
		location.href = "/hboard/listAll";
	}

	function removeFileCheckBx(fileId) {
		console.log(fileId);

		let checkCount = countCheckBoxChecked();

		if (checkCount > 0) {
			$(".removeUpFileBtn").removeAttr("disabled");
			$(".removeUpFileBtn").val(checkCount + "개 파일을 삭제합니다.");
		} else {
			$(".removeUpFileBtn").attr("disabled", true);
			$(".removeUpFileBtn").val("선택된 파일이 없습니다.");
		}
	}

	function countCheckBoxChecked() {
		let result = 0;

		$(".fileCheck").each(function(i, item) {
			// console.log($(item).is(":checked"));

			if ($(item).is(":checked")) {
				result++;
			}
		});

		console.log(result + "개 체크됨");

		return result;
	}

	function removeFile() {
		let removeFileArr = [];

		$(".fileCheck").each(function(i, item) {
			if ($(item).is(":checked")) {
				let tmp = $(item).attr('id');
				removeFileArr.push(tmp);
			}
		})

		console.log("삭제될 파일: " + removeFileArr);

		$.each(removeFileArr, function(i, item) {
			$.ajax({
				url : "/hboard/modifyRemoveFileCheck",
				type : "POST",
				dataType : "json",
				data : {
					"removeFileNo" : item,
				},
				async : false,
				success : function(data) {
					console.log(data);
					if (data.msg == "success") {
						$(`#` + item).parent().parent().css('opacity', 0.2);
					}
				},
				error : function() {
				},
				complete : function() {
				}
			});
		});
	}

	function cancelRemFiles() {
		$.ajax({
			url : "/hboard/cancelRemFiles",
			type : "POST",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == 'success') {
					$(".fileCheck").each(function(i, item) {
						$(item).prop('checked', false);
						$(item).parent().parent().css('opacity', 1);
					});

					$(".removeUpFileBtn").attr("disabled", true);
					$(".removeUpFileBtn").val("선택된 파일이 없습니다.");
				}

			},
			error : function() {
			},
			complete : function() {
			}
		});
	}
	
	// 파일 추가
	function addRows(obj){
		let rowCnt = $('.fileListTable tr').length;
		
		console.log("tr 개수: " + rowCnt);
		
		let row = $(obj).parent().parent();
		
		let inputFileTag = `<tr>
			<td colspan='2'>
				<input type='file' id='newFile_\${rowCnt}' name="modifyNewFile" onchange="showPreview(this);"/>
			</td>
			<td>
				<input type='button' class="btn btn-danger" value="파일저장취소" onclick="cancelAddFile(this);"/>
			</td>
		</tr>`;
		
		$(inputFileTag).insertBefore(row);
	}
	
	function showPreview(obj){
		console.log(obj.files);
		console.log("onchanged");
		
		if(obj.files[0].size > 1024 * 1024 * 10){
			alert("10MB이하의 파일만 업로드할 수 있습니다.");
			obj.value ="";
			return;
		}
		
		let imageType = ["image/jpeg", "image/png", "image/gif", "image/jpg"];
		let fileName = obj.files[0].name;
		let fileType = obj.files[0].type;
		console.log(fileType);
		
		if(imageType.indexOf(fileType) != -1){ // 이미지 파일이라면
			let reader = new FileReader();
			reader.readAsDataURL(obj.files[0]); // 업로드된 파일을 읽어옴
			reader.onload = function(e){
				// reader 객체에 의해 파일 읽기를 완료하면 실행되는 콜백함수
				console.log(e);
				
				let imgTag = `<div>
					<img src='\${e.target.result}' width="50px" />
					<span>\${fileName}</span>
				</div>`;
				$(imgTag).insertAfter(obj);
			}
		} else { // 이미지 파일이 아니라면
			let imgTag = `<div>
					<img src='/resources/images/noimg.png' width="50px" />
					<span>\${fileName}</span>
				</div>`;
			$(imgTag).insertAfter(obj);
		}
	}
	
	function cancelAddFile(obj){
		let fileTag = $(obj).parent().prev().children().eq(0);
		
		$(fileTag).val('');
		$(fileTag).parent().parent().remove();
	}
</script>
<style>
.fileBtns {
	text-align: right;
}
</style>
</head>
<body>
	<jsp:include page="./../header.jsp"></jsp:include>
	<div class="container">

		<h1>게시글 수정</h1>
		<c:forEach var="hboard" items="${hboardList }">
			<form action="/hboard/modifyBoardSave" method="post" enctype="multipart/form-data">
				<div class="input-group mb-3">
					<span class="input-group-text">글 번호</span>
					<input type="text" class="form-control" id="boardNo" name="boardNo" readonly="readonly" value="${hboard.boardNo }">
					<span class="input-group-text">글 제목</span>
					<input type="text" class="form-control" id="title" name="title" value="${hboard.title }">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text">작성자</span>
					<input type="text" class="form-control" name="writer" readonly="readonly" value="${hboard.writer }">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text">작성자 이메일</span>
					<input type="text" class="form-control" readonly="readonly" value="${hboard.email }">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text">조회수</span>
					<input type="text" class="form-control" name="readCount" value='${hboard.readCount}' readonly />
				</div>

				<div class="mb-3">
					<label for="content"></label>
					<textarea class="form-control" rows="20" id="content" name="content">${hboard.content }</textarea>
				</div>

				<div id="preview">
					<table class="table fileListTable">
						<thead>
							<tr>
								<th>#</th>
								<th>uploadFiles</th>
								<th>fileName</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="file" items='${hboard.fileList}'>
								<c:if test="${file.boardUpFileNo != 0 }">
									<tr>
										<td><input class="fileCheck" type="checkBox" id="${file.boardUpFileNo }" onclick="removeFileCheckBx(this.id);" /></td>
										<td><c:choose>
												<c:when test="${file.thumbFileName != null}">
													<img src='/resources/boardUpFiles${file.thumbFileName}' />
												</c:when>
												<c:otherwise>
													<img src='/resources/images/noimg.png' width="100px" height="100px" />
												</c:otherwise>
											</c:choose></td>
										<td>${file.originFileName}</td>
									</tr>
								</c:if>
							</c:forEach>
							<tr>
								<td colspan="3" style="text-align: center"><img src="/resources/images/add.png" width="100px" onclick="addRows(this);" /></td>
							</tr>
						</tbody>
					</table>

					<div class="fileBtns">
						<input type="button" class="btn btn-primary removeUpFileBtn mb-3" value="선택한 파일 삭제" disabled onclick="removeFile();" />
						<input type="button" class="btn btn-primary cancelRemove mb-3" value="파일 삭제 취소" onclick="cancelRemFiles();" />

					</div>
				</div>

				<button type="submit" class="btn btn-secondary mb-3" >저장</button>
				<button type="button" class="btn btn-secondary mb-3" onclick="location.href='/hboard/viewBoard/boardNo=${board.boardNo }'">취소</button>
				<button type="button" class="btn btn-secondary mb-3" onclick="goListAll();">목록으로 돌아가기</button>
			</form>
		</c:forEach>



		<!-- 삭제 모달 -->
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