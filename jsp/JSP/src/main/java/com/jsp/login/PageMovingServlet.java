package com.jsp.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PageMovingServlet")
public class PageMovingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PageMovingServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 페이지 이동방법
		// 1) javascript의 location.href 이용
//		out.print("<script>");
//		out.print("location.href='mainTest.jsp'");
//		out.print("</script>");
		
		// 2) response.sendRedirect() 이용
		// 출력이 mainTest.jsp가 되고, 실제 주소도 이동
//		response.sendRedirect("mainTest.jsp");
		
		
		// 3) RequestDispatcher 객체를 이용하여 forwarding 하는 방법
		// 출력은 mainTest.jsp가 되지만, 실제 주소 이동이 없음
//		request.getRequestDispatcher("mainTest.jsp").forward(request, response);
		
		
		// 4) meta 태그
		out.print("<html>");
		out.print("<head>");
		out.print("<meta http-equiv='refresh' content='5; URL=mainTest.jsp'>");
		out.print("</head>");
		
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
