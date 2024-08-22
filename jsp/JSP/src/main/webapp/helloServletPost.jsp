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
		location.href = "./hPOST";
	}
	
	function callServletAjax() {
        $.ajax({
            url: "./hPOST", // url
            type: "post", // method
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
<h1>HelloServletPOST 파일을 POST 방식으로 호출</h1>

<form action="./hPOST" method="post">
	<div>이름: <input type="text" name="name"></div>
	<div>나이: <input type="text" name="age"></div>
	<button type="submit">버튼</button>
</form>

<button onclick="callServlet();">Location.href 서블릿 요청</button>
<button onclick="callServletAjax();">Ajax 서불릿 요청 (POST)</button>
</body>
</html>