package com.jsp.jstl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin3.do")
public class SessionLoginJSTLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SessionLoginJSTLServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Post 요청");

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		if (userId.equals("abcd") && userPwd.equals("1234")) {
			// 로그인 성공
			HttpSession ses = request.getSession();
			ses.setAttribute("loginMemberId", userId);
			ses.setAttribute("loginMemberPwd", userPwd);
			
			response.sendRedirect("mainTest_jstl.jsp?status=loginSuccess");
		} else {
			// 로그인 실패
			response.sendRedirect("mainTest.jsp?status=loginFail");
		}
		
//		PrintWriter out = response.getWriter();
//
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//

	}

}
