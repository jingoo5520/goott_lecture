<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 표현 가능한 데이터</title>
</head>
<body>
	<div>정수형: ${10}</div>
	<div>실수형: ${3.14}</div>
	<div>문자열: ${"오늘은 매우 덥습니다."}</div>
	<div>논리형: ${true}</div>
	<div>null: ${null}</div>

	<!-- EL에서 사용가능한 연산자 -->
	<div>5 + 2 = ${5 + 2}</div>
	<div>5 / 2 = ${5 / 2}</div>
	<div>5 mod 2 = ${5 mod 2}</div>
	<div>5 % 2 = ${5 % 2}</div>
	<div>5 > 2 = ${5 > 2}</div>
	<div>5 gt 2 = ${5 gt 2}</div>
	<div>5 < 2 = ${5 < 2}</div>
	<div>5 lt 2 = ${5 lt 2}</div>
	<div>5 gt 2 and 3 lt 4 = ${5 gt 2 and 3 lt 4}</div>
	<div>5 gt 45 ? "참" : "거짓" = ${5 gt 45 ? "참" : "거짓"}</div>

	<%
	String input = null;
	input = "input";
	pageContext.setAttribute("input", input);
	%>
	
	<div>empty input = ${empty input}</div>
	
</body>
</html>