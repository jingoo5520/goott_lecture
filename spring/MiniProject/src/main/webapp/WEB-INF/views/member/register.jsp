<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#userId").keyup(function(){
			let tmpUserId = $("#userId").val();
			
			if(tmpUserId.length < 4 || tmpUserId.length > 8) {
				outputError("아이디 4~8자 입력 필요", $("#userId"), "red");
				$("#idValid").val("");
			} else {
				// 중복체크
				$.ajax({
					url : "/member/isDuplicate" ,
					type : "POST",
					dataType: 'json',
					data: {
						"tmpUserId": tmpUserId
					},
					success : function(data) {
						if(data.msg == "duplicate"){
							outputError("중복된 아이디 입니다.", $("#userId"), "red");
							$("#idValid").val("");
							$("#userId").focus();
						} else if(data.msg == "not duplicate") {
							outputError("완료", $("#userId"), "green");
							$("#idValid").val("checked");
						}
					},
					error : function(e) {
					},
					complete : function() {
					}
				}); 
			}
		})
		
		$("#userPwd1").blur(function(){
			let tmpPwd = $("#userPwd1").val();
			
			if(tmpPwd.length < 4 || tmpPwd.length > 8) {
				outputError("비밀번호 4~8자 입력 필요", $("#userPwd1"), "red");
				$("#pwdVaild").val("");
			} else {
				outputError("완료", $("#userPwd1"), "green");
			}
		});
		
		$("#userPwd2").blur(function(){
			let tmpPwd2 = $("#userPwd2").val();
			let tmpPwd1 = $("#userPwd1").val();
			
			if(tmpPwd1 != tmpPwd2) {
				outputError("비밀번호가 일치하지 않습니다.", $("#userPwd1"), "red");
				$("#pwdVaild").val("");
				$("#userPwd1").val("");
				$("#userPwd2").val("");
			} else if(tmpPwd1 == tmpPwd2) {
				outputError("일치", $("#userPwd1"), "green");
				$("#pwdValid").val("checked");
			}
		});
		
		$("#email").blur(function(){
			emailVaild();
		}); 
		
		
		
	});

	function isValid(){
		let result = false;
		// 유효성 검사 조건
		// 1) 아이디: 필수, 4-8자, 중복x
		// 2) 비밀번호: 필수, 4-8자, 비밀번호 확인과 동일
		// 3) 
		
		let idCheck = idValid();
		console.log(idCheck);
		let pwdCheck = pwdValid();
		console.log(pwdCheck);
		let genderCheck = genderValid();
		console.log(genderCheck);
		let mobileCheck = mobileValid();
		console.log(mobileCheck);
		let emailCheck = emailVaild();
		console.log(emailCheck);
		let imgCheck = imgValid();
		console.log(imgCheck);
		
		// 동의 항목 체크
		let agreeCheck = $("#agree").is(":checked");
		if(agreeCheck == false){
			alert("동의항목에 체크해 주세요!");
			result = false;
		}
		
		if(idCheck && pwdCheck && genderCheck && mobileCheck && emailCheck && imgCheck && agreeCheck){
			console.log("체크 완료");
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
	function imgValid(){
		let result = false;
		
		let userImg = $("#userImg").val();
		if($("#imgCheck").val() == "checked" || userImg == ''){
			result = true;
		}
		
		return result;
	}
	
	function outputError(errorMsg, tagObj, color) {
		let errTag = $(tagObj).prev();
		$(errTag).html(errorMsg);
		$(errTag).css("color", color);
		$(tagObj).css("border-color", color);
	}
	
	function idValid(){
		let result = false;
		if($("#idValid").val() == "checked"){
			result = true;
		} else {
			outputError("아이디는 필수 항목입니다.", $("#userId"), "red");
		}
		
		return result;
	}
	
	function pwdValid(){
		let result = false;
		
		if($("#pwdValid").val() == "checked"){
			result = true;
		} 
		
		return result;
	}
	
	function genderValid(){
		// 성별은 남성, 여성 중 하나를 반드시 선택해야 한다.
		let genders = document.getElementsByName("gender");
		
		let result = false;

		for(let g of genders){
			if(g.checked){
				result = true;
			}
		}
		
		if(!result){
			outputError("성별은 필수입니다.", $(".genderSpan").next().next(), "red");
		} else {
			outputError("완료", $(".genderSpan").next().next(), "green");
		}
		
		return result;
	}
	
	function mobileValid(){
		let result = false;
		let tmpUserMobile = $("#mobile").val();
		console.log(tmpUserMobile);
		
		let mobileExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		
		if(mobileExp.test(tmpUserMobile)){
			outputError("완료", $("#mobile"), "green");
			result = true;
		} else {
			outputError("휴대폰 번호 형식이 아닙니다.", $("#mobile"), "red");
		}
		
		return result;
	}
	
	function emailVaild(){
		// 이메일 형식 판단
		// 이메일 형식이면 인증번호를 이메일로 전송하고, 검증
		let result = false;
		
		let emailExp = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
		
		let tmpUserEmail = $("#email").val();
		
		if(!emailExp.test(tmpUserEmail)){
			outputError("이메일 형식이 아닙니다.", $("#email"), "red");
					
		} else {
			
			if($("#emailValid").val() == "checked"){
				result = true;		
			} else {
				showAuthenticateDiv(); // 인증번호 입력 div 보여주기
				callSendMail(); // 인증번호 전송
				startTimer(); // 타이머 동작
				outputError("이메일 형식입니다.", $("#email"), "green");
			}
			
		}
		
		return result;
	}
	
	function startTimer() {
		let timer = 10; // 초 단위
		
		let timerInterval = setInterval(displayTime, 1000);
		
		function displayTime(){
			// 3분
			if(timer <= 0){
				// 시간 만료
				clearInterval(timerInterval);
				$("#authBtn").prop("display", true);
				
				// 백엔드에 인증시간 만료를 알려야함
				$.ajax({
					url : "/member/clearAuthCode" ,
					type : "POST",
					dataType: 'text',
					success : function(data) {
						console.log(data);
/* 						if(data.msg == "duplicate"){
							outputError("중복된 아이디 입니다.", $("#userId"), "red");
							$("#idValid").val("");
							$("#userId").focus();
						} else if(data.msg == "not duplicate") {
							outputError("완료", $("#userId"), "green");
							$("#idValid").val("checked");
						} */
						
						if(data == "success"){
							alert("인증 시간 만료");
							$(".authenticateDiv").remove();
							$("#email").val();
						}
					},
					error : function(e) {
						console.log(e);
					},
					complete : function() {
					}
				}); 
			}
			
			let min = Math.floor(timer / 60); // 분단위
			let sec = String(timer % 60).padStart(2, "0");
			let remainTime = min + ":" + sec;
			$(".timer").html(remainTime);
			--timer;			
		}	
	}
	
	
	
	function showAuthenticateDiv(){
		let authDiv = `<div class="authenticateDiv">`;
		authDiv += `<input type="text" class="form-control" placeholder="인증번호를 입력하세요" id="userAuthCode">`;
		authDiv += `<div class="timer">타이머</div>`
		authDiv += `<button type="button" id="authBtn" onclick="checkAuthCode();">인증하기</button>`
		authDiv += `</div>`;
		
		$(authDiv).insertAfter("#email");
	}
	
	function checkAuthCode(){
		let userAuthCode = $("#userAuthCode").val();
		
		$.ajax({
			url : "/member/checkAuthCode",
			type : "POST",
			dataType: 'text',
			data : {
				"tmpUserAuthCode": userAuthCode,
			},
			success : function(data) {
				console.log(data);
				if(data == "success"){
					outputError("인증완료", $("#email"), "green");
					$("#email").attr("readonly", true);
					$(".authenicateDiv").remove();
					$("#emailValid").val("checked");
				} else if (data == "fail") {
					alert("인증 실패");
					$("#emailValid").val("");
				}
			},
			error : function(e) {
			},
			complete : function() {
			}
		}); 
	}
	
	
	function callSendMail(){
		$.ajax({
			url : "/member/callSendMail",
			type : "POST",
			dataType: 'text',
			data: {
				"tmpUserEmail": $("#email").val()
			},
			success : function(data) {
				if(data == "success"){
					alert("이메일로 인증번호를 발송하였습니다. \n인증 코드를 입력해주세요.");
					$("#userAuthCode").focus();
				} 
				
			},
			error : function(e) {
			},
			complete : function() {
			}
		}); 
	}
	
	function showPreview(obj){
		
		
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
				outputError("이미지 파일 확인", obj, "green");	
				$("#imgCheck").val("checked");

			}
		} else { // 이미지 파일이 아니라면
			outputError("이미지 파일만 올릴 수 있습니다.", obj, "red");
			$(obj).val("");
		}
	}
	
</script>
<style>
	.hobbies {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container mt-3">
		<h1>회원가입 페이지</h1>

		<form action="/member/register" method="post" enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="userId" class="form-label">아이디: </label>
				<span></span>
				<input type="text" class="form-control" placeholder="아이디를 입력하세요" name="userId" id="userId" >
				<input type="hidden" id="idValid">
			</div>

			<div class="mb-3 mt-3">
				<label for="userPwd1" class="form-label">비밀번호: </label>
				<span></span>
				<input type="password" class="form-control" placeholder="비밀번호를 입력하세요" name="userPwd" id="userPwd1">
				<input type="hidden" id="pwdValid">
			</div>

			<div class="mb-3 mt-3">
				<label for="userPwd2" class="form-label">비밀번호 확인: </label>
				<input type="password" class="form-control" placeholder="비밀번호를 입력하세요" name="userPwd2" id="userPwd2">
			</div>

			<div class="mb-3 mt-3">
				<label for="userName" class="form-label">이름: </label>
				<input type="text" class="form-control" placeholder="이름을 입력하세요" name="userName" id="userName">
			</div>

			<span class="genderSpan">성별: </span>
			<input type="hidden" id="genderVaild">

			<div class="form-check">
				<label for="userName" class="form-label">
					<input type="radio" class="form-check-input" id="female" name="gender" value="F">
					여성
				</label>
			</div>

			<div class="form-check">
				<label for="gender" class="form-label">
					<input type="radio" class="form-check-input" id="male" name="gender" value="M">
					남성
				</label>
			</div>

			<div class="mb-3 mt-3">
				<label for="mobile" class="form-label">전화번호: </label>
				<input type="text" class="form-control" placeholder="전화번호를 입력하세요" name="mobile" id="mobile">
			</div>

			<div class="mb-3 mt-3">
				<label for="email" class="form-label">이메일: </label>
				<input type="text" class="form-control" placeholder="이메일을 입력하세요" name="email" id="email">
			</div>
			<input type="hidden" id="emailValid" />


			<div class="form-check mb-3 mt-3">
				<div>취미</div>
				<div class="hobbies">
					<span>
						<input class="form-check-input" type="checkbox" name="hobbies" value="sleep">
						낮잠
					</span>
					<span>
						<input class="form-check-input" type="checkbox" name="hobbies" value="reading">
						독서
					</span>
					<span>
						<input class="form-check-input" type="checkbox" name="hobbies" value="coding">
						코딩
					</span>
					<span>
						<input class="form-check-input" type="checkbox" name="hobbies" value="game">
						게임
					</span>
				</div>
			</div>

			<div class="mb-3 mt-3">
				<label class="form-check-label">회원 프로필 사진:</label>
				<input type="file" class="form-control" name="userProfile" id="userImg" onchange="showPreview(this);"/>
				
			</div>
			<input type="hidden" id="imgCheck" />
			
			

			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="agree" name="agree" value="Y">
				<label class="form-check-label">회원가입 조항에 동의합니다.</label>
			</div>

			<input type="submit" class="btn btn-primary" value="회원가입" onclick="isValid();">
			<input type="reset" class="btn btn-danger" value="취소">

		</form>
	</div>


	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>