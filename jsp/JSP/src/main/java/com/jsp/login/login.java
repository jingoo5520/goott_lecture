package com.jsp.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post 요청");
		
		PrintWriter out = response.getWriter();
		
		
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		if(userId.equals("abcd") && userPwd.equals("1234")) {
			// 로그인 성공
			response.sendRedirect("mainTest.jsp?status=loginSuccess");
			
//			out.print("<script>alert('성공')</script>");
			
		} else {
			// 로그인 실패
			response.sendRedirect("mainTest.jsp?status=loginFail");
		}
		
	}

}
