package com.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadCookieServlet
 */
@WebServlet("/readCook.do")
public class ReadCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadCookieServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cooks =  request.getCookies();
		PrintWriter out = response.getWriter();
		
		// myCook 쿠키의 값을 출력
		
		
		
		for(Cookie cook : cooks) {
//			if(cook.getName().equals("myCook")) {
				System.out.println(cook.getValue());
				out.print(cook.getName() + ": " + cook.getValue() + "<br>");
//				break;
//			}
		}
		
		// myCook 쿠키 삭제하기
		// 유효기간을 0으로 설정
		for(Cookie cook : cooks) {
			if(cook.getName().equals("myCook")) {
				cook.setMaxAge(0);
				cook.setPath("/");
				response.addCookie(cook);
				break;
			}
		}
	}


}
