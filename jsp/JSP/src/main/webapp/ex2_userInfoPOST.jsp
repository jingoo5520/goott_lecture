<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function pwdValidation() {
		let isValid = false;
		let pwd = document.getElementById("userPwd").value;
		console.log(pwd);
		let regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
	
		console.log(regExp.test(pwd));
		
		if(!regExp.test(pwd)) {
			console.log("형식에 맞지 않음")
		}
		
		return isValid;
	}
</script>
</head>
<body>
	<form action="Ex1UserInfoPOST" method="post">
		<div>
			이름: <input type="text" name="userName">
		</div>
		<div>
			아이디: <input type="text" name="userId">
		</div>
		<div>
			비밀번호: <input id="userPwd" type="text" name="userPwd">
		</div>
		<input type="submit" value="전송" onclick="return pwdValidation();">
	</form>
</body>
</html>