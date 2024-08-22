<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function getParameter(paraName) {
		let returnVal = null;
		let url = location.href;
		
		if(url.indexOf("?") != -1){
			// 쿼리 스트링이 있는 경우
			let queryString = url.split("?")[1];
			let queryStringArr = queryString.split("&");
			
			for(let item of queryStringArr) {
				if(item.split("=")[0] == paraName) {
					returnVal = item.split("=")[1];
					break;
				}
			}
		}
		
		return returnVal;
	}
	
	window.onload = function () {
		let status = getParameter("status");
		if(status == "loginSuccess"){
			alert("로그인 성공! 회원님 방가!")	
		} else if(status == "loginFail") {
			
		}
		
	}
</script>
</head>
<body>
	mainTest.jsp
	<div>
		<a href="loginTest1.jsp">로그인 하러가기</a>
		<a href="loginTest2.jsp">로그인 하러가기2</a>
	</div>
	
	<hr/>
	
	<form action="SessionLoginJSTLServlet">
		<input type="submit" value="로그아웃">
	</form>
	
	<div>세션 id: <%=session.getId() %></div>
	<div>로그인한 멤버 id: <%=session.getAttribute("loginMemberId") %></div>
	<div>로그인한 멤버 pwd: <%=session.getAttribute("loginMemberPwd") %></div>

	<%
		if(session.isNew()){
			out.print("새로운 세션이 생성되었습니다. <br>" );
		} else {
			out.print("아니요. 새로운 세션이 아닙니다. <br>");
		}
	
	%>

</body>
</html>