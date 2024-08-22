<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	function callServlet() {
		location.href = "./hGET?name=진구2";
	}
	
	function callServletAjax() {
        $.ajax({
            url: "./hGET?name=진구3", // url
            type: "GET", // method
            success: function (data) {
                console.log("ajax success");
            }, // 통신 성공
            error: function () {}, // 통신 실패
            complete: function () {}, // 통신 완료
        });
    }
</script>

</head>
<body>
<h1>HelloServletGet 파일을 GET 방식으로 호출</h1>
<div>
	<a href="./hGET?name=진구">a태그로 서블릿 요청</a>
</div>

<form action="./hGET" method="get">
	<input type="text" name="name" />
	<button type="submit">form 태그로 GET방식 요청</button>
</form>

<button onclick="callServlet();">Location.href 서블릿 요청</button>

<button onclick="callServletAjax();">Ajax 서불릿 요청 (GET)</button>
</body>
</html>