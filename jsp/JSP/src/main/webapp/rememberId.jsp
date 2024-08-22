<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>

<body>
	<h1>로그인</h1>

	<!-- JS 코드로 수정해보기 -->
	<%
	String loginId = "";
	String isChecked = "";
	
	Cookie[] arr = request.getCookies();
	String Cook = "";
	for (Cookie c : arr) {
		if (c.getName().equals("rememberId")) {
			loginId = c.getValue();
			isChecked = "checked";
		}
	}
	%>

	<form action="rememberId.do" method="post">
		<div>
			아이디: <input type="text" name="userId" value="<%=loginId %>"> rememberId: <input
				type="checkbox" name="rememberId" value="Y" <%=isChecked %>>
		</div>
		<div>
			비밀번호: <input type="text" name="userPwd">
		</div>

		<div>
			<input type="submit" value="로그인"> <input type="reset"
				value="취소">
		</div>
	</form>
</body>
</html>