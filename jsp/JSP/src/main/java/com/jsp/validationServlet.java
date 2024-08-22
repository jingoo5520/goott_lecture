package com.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validation")
public class validationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public validationServlet() {

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * System.out.println("GET");
		 * 
		 * int kor = Integer.parseInt(req.getParameter("kor")); int eng =
		 * Integer.parseInt(req.getParameter("eng")); int math =
		 * Integer.parseInt(req.getParameter("math"));
		 * 
		 * resp.setContentType("text/html; charset=utf-8");
		 * 
		 * PrintWriter out = resp.getWriter();
		 * 
		 * if (kor < 0 || kor > 100) { out.print("<script>");
		 * out.print("alert('국어점수가 잘못 입력되었습니다!');");
		 * out.print("location.href='validation.jsp';"); out.print("</script>"); }
		 * 
		 * int total = kor + eng + math; double avg = total / 3.0;
		 * 
		 * // PrintWriter out = resp.getWriter(); // out.write("<!DOCTYPE html>\r\n");
		 * // out.write("<html>\r\n"); // out.write("<head>\r\n"); //
		 * out.write("<meta charset=\"UTF-8\">\r\n"); //
		 * out.write("<title>Why use Servlet</title>\r\n"); // out.write("</head>\r\n");
		 * // out.write("<body>\r\n"); // out.write("<h1 onclick=\"hello()\"> " +
		 * "다음페이지" + "</h1>\r\n"); // out.write("\r\n"); // out.write("</body>\r\n"); //
		 * out.write("</html>");
		 * 
		 * // 출력방법 2) // EL 표현식 이용 // 서블릿에서 바인딩을 거쳐 request.setAttribute() 데이터를 //
		 * reg.getRequestDispatcher("./outputScore.jsp") 디스패처 객체에게 전달 // forwarding //
		 * View단에서는 EL표현식을 이용해 출력됨 req.setAttribute("kor", kor); req.setAttribute("eng",
		 * eng); req.setAttribute("math", math); req.setAttribute("total", total);
		 * req.setAttribute("avg", avg);
		 * 
		 * RequestDispatcher rd = req.getRequestDispatcher("./outputScore.jsp");
		 * rd.forward(req, resp);
		 */
	}

}
