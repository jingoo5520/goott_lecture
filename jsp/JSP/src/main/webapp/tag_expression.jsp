<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 태그</title>
</head>
<body>
	<%!int num = -5;

	public int abs(int n) {
		if (n < 0) {
			n = -n;
		}
		return n;
	}%>

	<!-- 표현식에서는 out.print(str;)로 변환되므로 세미콜론 입력x -->
	<%-- <div><%=str; %></div> --%>

	<!-- 간단한 주석문은 변환됨 -->
	<%-- jsp 기본 태그 주석문 내용은 servlet 코드로 변환되지 않음 --%>

	<div><%=num%></div>
	<div><%=abs(num)%></div>
</body>
</html>