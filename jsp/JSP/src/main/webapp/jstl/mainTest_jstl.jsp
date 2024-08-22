<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	window.onload = function() {
		let status = getParameter("status");
		if (status == "loginSuccess") {
			alert("로그인 성공! 회원님 방가!")
		} else if (status == "loginFail") {

		}

	}
</script>

<body>
	<h1>mainTest_jstl.jsp</h1>

	<c:choose>
		<c:when test="${sessionScope.loginMemberId != null }">
			<form action="#">
				<input type="submit" value="로그아웃">
			</form>
		</c:when>

		<c:otherwise>
			<button onclick="location.href='loginTest_jstl.jsp'">로그인</button>
		</c:otherwise>
	</c:choose>

	<hr />



	<div>
		세션 id:
		<%=session.getId()%></div>
	<div>
		로그인한 멤버 id:
		<%=session.getAttribute("loginMemberId")%></div>
	<div>
		로그인한 멤버 pwd:
		<%=session.getAttribute("loginMemberPwd")%></div>

	<%-- <%
		if(session.isNew()){
			out.print("새로운 세션이 생성되었습니다. <br>" );
		} else {
			out.print("아니요. 새로운 세션이 아닙니다. <br>");
		}
	%> --%>
</body>
</html>