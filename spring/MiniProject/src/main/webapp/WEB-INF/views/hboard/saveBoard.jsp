<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Save Board.jsp</title>
<script>
   let upfiles = new Array(); //업로드되는 파일들을 저장하는 배열
   

   $(function() {
      // 업로드 파일 영역에 dragdrop과 관련된 이벤트 (파일의 경우 웹브라우저에서 열림)를 방지
      
      $(".fileUploadArea").on("dragenter dragover", function(event) {
         event.preventDefault();
      });
      $(".fileUploadArea").on("drop",function(event) {
         event.preventDefault();
         
         // console.log(event.originalEvent.dataTransfer.files);
         
         for (let file of event.originalEvent.dataTransfer.files) {
            //10MB 까지 가능
            if(file.size > 1024*1024 *10) {
               alert("파일 용량이 너무 큽니다");
            } else {
               upfiles.push(file);   
               fileUpload(file);
               console.log("upfiles: ");
   				console.log(upfiles);
            }
         }
      }) 
      
      
   });
   
   function showPreview(file, newFileName) {
      
      let imageType  = ["image/jpeg" , "image/png", "image/gif"];
      console.log(file.type);
      let fileType = file.type;
      let output;
      
      console.log("newFileName: " + newFileName);
      
      if(imageType.indexOf(fileType) != -1) {
         // 이미지 파일이라면...
         output = `<div id="\${file.name}"><img src='/resources/boardUpFiles\${newFileName}' /><span>\${file.name}</span>`
         output += `<span><img src='/resources/images/remove.png' width='20px' onclick='remFile(this)' id="\${newFileName}" /></span></div>`
         
      } else {
         output = `<div id="\${file.name}"><img src='/resources/images/noimg.png' width='50px' /><span>\${file.name}</span>`         
         output += `<span><img src='/resources/images/remove.png' width='20px' onclick='remFile(this)' id="\${newFileName}" /></span></div>`
         
      }
      
      $("#preview").append(output);
   }
   
   
   //실제로 유저가 업로드한 파일을 컨트롤러 단에 전송하여 저장되도록 하는 함수
   function fileUpload(file) {
      let fd = new FormData(); // form  태그와 같은 역활의 객체

      fd.append("file", file);
      
      // processData: false -> 데이터를 쿼리스트링 형태로 보내지 않겠다.
	  // contentType: false -> 기본값인 application/x-www-form-urlencoded로 보내지 않겟다.
      $.ajax ({
    	  url: "/hboard/upfiles",
    	  type: "POST", 
    	  dataType: "json",
    	  data: fd,
    	  processData: false,
    	  contentType: false,
    	  success: function(data) {
    		  console.log(data);
    		  
    		  if(data.msg == "success") {
    			  showPreview(file, data.newFileName);
    		  }
    		  
    		  /* makeImgSlider(data); */
    	  },
    	  error: function() {
    	  },
    	  complete: function() {
    	  }
    	}); 
      
   }
   
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
   
   // 파일 삭제
   function remFile(obj) {
	   console.log(obj);
	   console.log("지워야할 파일 이름: " + $(obj).attr('id'));
	   
	   let removeFileName = $(obj).attr('id');
	   
	   for(let i = 0; i < upfiles.length; i++){
		   
		   if(upfiles[i].name == $(obj).parent().prev().html()){
			   $.ajax ({
			    	  url: "/hboard/removefile", 
			    	  type: "POST",
			    	  dataType: "json", 
			    	  data: {
			    		  "removeFileName" : removeFileName
			    	  },
			   
			    	  success: function(data) {
			    		  console.log(data);
			    		  
			    		  if(data.msg == 'success'){
			    			// upfiles 배열에서 삭제
			    			upfiles.splice(i, 1);
			    			console.log("upfiles: ");
			    			console.log(upfiles);
			    		  }
			    		  
			    		/*   for(file of upfiles){
			    			  console.log(file.name);
			    			  console.log(removeFileName.replace("thumb_", ""));
			    			  
			    			  if(file.name == removeFileName.match(/thumb_(.*)/)[1]) {
			    				  let index = upfiles.indexOf(file);
			    				  console.log(index);
			    				  
			    				  upfiles.splice(index, 1);
			    				  
			    				  console.log(removeFileName.match(/thumb_(.*)/)[1] + "파일 삭제 완료");
			    				  console.log(upfiles);
			    				  
			    				  break;
			    			  }
			    		  } */
			    		  
			    		  // 미리보기 태그 삭제
			    		  $(obj).closest('div').remove();
			    		  
			    	  },
			    	  error: function(e) {
			    		  console.log(e)
			    	  },
			    	  complete: function() {
			    	  }
			    	}); 
		   }
	   }
	   
	   
   }
   
   // 취소버튼 클릭시 업로드 파일 모두 삭제
   function cancelBoard() {
	   // 서버에 저장한 해당 글 작성시 업로드한 모든 파일 지우기
	   // view단의 태그 삭제
	   $.ajax ({
	    	  url: "/hboard/cancelBoard",
	    	  type: "POST", 
	    	  dataType: "text",
	    	  success: function(data) {
	    		  console.log(data);
	    		  
	    		  if(data == 'success'){
	    			  location.href = "/hboard/listAll";
	    		  }
	    	  },
	    	  error: function(e) {
	    		
	    	  },
	    	  complete: function() {
	    	  }
	    	}); 
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
		<h1>게시글 작성</h1>
		<form action="saveBoard" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">글 제목</span> <input type="text"
					class="form-control" id="title" placeholder="글 제목을 입력하세요..."
					name="title">
			</div>
			<div class="mb-3">
				<label for="content"></label>
				<textarea class="form-control" rows="20" id="content" name="content"
					placeholder="내용을 입력해주세요..."></textarea>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span> <input type="text"
					class="form-control" placeholder="작성자를 입력하세요..." name="writer"
					value="${sessionScope.loginMember.userId}" readonly>
			</div>


			<div class="fileUploadArea mb-3">
				<p>업로드할 파일을 여기에 드래그드랍하세요</p>
			</div>
			<div id="preview"></div>

			<button type="submit" class="btn btn-primary mb-3"
				onclick="return invalidTitle();">저장</button>

			<button type="reset" class="btn btn-warning mb-3"
				onclick="cancelBoard();">취소</button>
		</form>
	</div>
	<jsp:include page="./../footer.jsp"></jsp:include>

</body>
</html>