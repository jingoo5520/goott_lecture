<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본태그 - 선언문</title>
</head>
<body>
	<%!
	// 선언문에서 선언된 변수나 메서드들은 jsp 페이지가 초기화 될 때 초기화됨
	// 페이지 내의 어떠한 스크립트릿이나 표현식에서도 접근해서 사용 가능

	String str = "안녕하세요";
	
	int num = -5;

	public int abs(int n) {
		if (n < 0) {
			n = -n;
		}
		return n;
	}%>


	<%
	
	out.print("<div>" + str + "</div>");
	out.print("<div>" + abs(-4) + "</div>");
	out.print("<div>" + abs(num) + "</div>");
	%>
</body>
</html>