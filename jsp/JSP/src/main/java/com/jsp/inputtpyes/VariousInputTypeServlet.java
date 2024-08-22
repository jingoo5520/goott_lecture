package com.jsp.inputtpyes;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.inputtypes.vo.Member;

@WebServlet("/VariousInputTypeServlet")
public class VariousInputTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VariousInputTypeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		System.out.println(request.getParameter("agree"));

		if (request.getParameter("agree") != null && request.getParameter("agree").equals("Y")) {
			String userId = request.getParameter("userId");
			String pwd1 = request.getParameter("pwd1");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			Date birth = Date.valueOf(request.getParameter("birth"));
			String gender = request.getParameter("gender");

			int age = Integer.parseInt(request.getParameter("age"));
			String hobby = "";

			for (String h : request.getParameterValues("hobby")) {
				hobby += h + ", ";
			}
			String job = request.getParameter("job"); // select-option
			String memo = request.getParameter("memo");

			System.out.println("userId: " + userId);
			System.out.println("pwd1: " + pwd1);
			System.out.println("email: " + email);
			System.out.println("mobile: " + mobile);
			System.out.println("birth: " + birth);
			System.out.println("gender: " + gender);
			System.out.println("age: " + age);
			System.out.println("hobby: " + hobby);
			System.out.println("job: " + job);
			System.out.println("memo: " + memo);

			Member member = new Member(userId, pwd1, email, mobile, birth, gender, age, job, memo, hobby);
			
			System.out.println(member.toString());
			
			//member 객체를 request 에 바인딩
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("outputMember.jsp");
			
			rd.forward(request, response);
		}
	}

}
