package com.jsp.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogoutServlet")
public class sessionLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public sessionLogoutServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그아웃 처리: session 객체에 남아있는 로그인 정보를 지운 후 무효화
		HttpSession ses =  request.getSession();
		
		// 세션 정보 삭제
		ses.removeAttribute("loginMemberId");
		ses.removeAttribute("loginMemberPwd");
		
		ses.invalidate(); // 세션 무효화 -> 세션 갱신
		
		response.sendRedirect("mainTest.jsp?status=logoutSuccess");
	}

	

}
