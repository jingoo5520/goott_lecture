package com.jsp.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionLoginServlet")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionLoginServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		if(userId.equals("abcd") && userPwd.equals("1234")) {
			// 세션 객체 얻어오기
			HttpSession ses =  request.getSession();
			
			System.out.println("세션 id: " + ses.getId());
			
			ses.setAttribute("loginMemberId", userId);
			ses.setAttribute("loginMemberPwd", userPwd);
			
			response.sendRedirect("mainTest.jsp?status=loginSuccess");
		}
		
	}

}
