<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		웹 어플리케이션 컨텍스트 패스
		<%=application.getContextPath()%></div>
	<div>
		웹 어플리케이션 실제 저장 경로
		<%=application.getRealPath("application.jsp")%></div>

	<%!public String callApplicationObj() {
		javax.servlet.ServletContext application = this.getServletContext();
		return application.getContextPath();
	}

	public String callApplicationObj(ServletContext app) {
		return app.getRealPath("application.jsp");
	}%>

	<div>
		callApplication():
		<%=callApplicationObj() %></div>
	<div>
		callApplication(ServletContext app):
		<%=callApplicationObj(application) %></div>

</body>
</html>