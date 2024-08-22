package com.jsp.jstl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionLogoutJSTLServlet")
public class SessionLogoutJSTLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SessionLogoutJSTLServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession ses = request.getSession();

		ses.removeAttribute("loginMemberId");
		ses.removeAttribute("loginMemberPwd");

		ses.invalidate(); // 세션 무효화 -> 세션 갱신

		response.sendRedirect("mainTest.jsp?status=logoutSuccess");
	}

}
