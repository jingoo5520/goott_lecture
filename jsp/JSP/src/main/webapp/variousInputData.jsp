<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	function check () {
		if(document.frm.userId.value == "") {
			alert("아이디를 입력해 주세요.");
			document.frm.userId.focus();
			return false;
		} else if(document.frm.age.value == "") {
			alert("나이를 입력해 주세요.");
			document.frm.age.focus();
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<form action="./VariousInputTypeServlet2" method="post" name="frm">
			<div class="container">
				<h1 style="color: pink;">회원 가입</h1>
				<p>아래 항목을 기입해 주세요.</p>
				<hr />
				<div class="mb-3 mt-3">
					<label for="userId"><b>아이디</b></label> <input type="text"
						class="form-control" placeholder="Enter id" name="userId"
						id="userId" />
				</div>
				<div class="mb-3 mt-3">
					<label for="pwd1"><b>비밀번호</b></label> <input type="password"
						class="form-control" placeholder="Enter Password" name="pwd1"
						id="pwd1" />
				</div>
				<div class="mb-3 mt-3">
					<label for="pwd2"><b>비밀번호 확인</b></label> <input type="password"
						class="form-control" placeholder="Repeat Password" name="pwd2"
						id="pwd2" />
				</div>
				<div class="mb-3 mt-3">
					<label for="email"><b>Email</b></label> <input type="text"
						class="form-control" placeholder="Enter Email" name="email"
						id="email" />
				</div>
				<div class="mb-3 mt-3">
					<label for="mobile"><b>휴대폰 번호</b></label> <input type="text"
						class="form-control" placeholder="Enter mobile" name="mobile"
						id="mobile" />
				</div>
				<div class="mb-3 mt-3">
					<label for="birth"><b>생년월일</b></label> <input type="date"
						class="form-control" name="birth" id="birth" />
				</div>
				<fieldset>
					<legend>성별</legend>
					<div class="form-check">
						<label class="form-check-label" for="radio1">여성</label> <input
							type="radio" class="form-check-input" id="radio1" name="gender"
							value="female" />
					</div>
					<div class="form-check">
						<label class="form-check-label" for="radio2">남성</label> <input
							type="radio" class="form-check-input" id="radio2" name="gender"
							value="male" />
					</div>
				</fieldset>
				<fieldset>
					<legend>나이</legend>
					<div class="form-check">
						<!--                     <label class="form-check-label" for="age">나이</label> -->
						<input type="number" class="form-control" id="age" name="age" />
					</div>
				</fieldset>
				<fieldset>
					<legend>취미</legend>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="check1"
							name="hobby" value="study" /> <label
							class="form-check-label">공부</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="check2"
							name="hobby" value="music" /> <label
							class="form-check-label">음악감상</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="check3"
							name="hobby" value="travel" /> <label
							class="form-check-label">여행</label>
					</div>
				</fieldset>

				<label for="job" class="form-label">직업 (select one):</label> <select
					class="form-select" id="job" name="job" multiple="multiple">
					<option>--직업을 선택하세요--</option>
					<option>학생</option>
					<option>크리에이터</option>
					<option>건물주</option>
					<option>공무원</option>
				</select>

				<div class="mb-3 mt-3">
					<label for="memo">Memo:</label>
					<textarea class="form-control" rows="5" id="memo" name="memo"></textarea>
				</div>

				<label for="agree"> 가입 조항에 동의합니다. </label> <input type="checkbox"
					name="agree" id="agree" value="Y" style="margin-bottom: 15px" />

				<p>
					By creating an account you agree to our <a href="#"
						style="color: dodgerblue">Terms & Privacy</a>.
				</p>

				<div class="clearfix">
					<button type="reset" class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn" onclick="return check();">Sign
						Up</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>