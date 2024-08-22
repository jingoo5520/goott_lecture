package jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get")
public class exServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public exServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET 방식 동작");

		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));

		System.out.println(width + " " + height);

		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"UTF-8\">\r\n");
		out.write("<title>Why use Servlet</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("<h1> " + (width * height) / 2 + "</h1>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST 방식 동작");

		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(name + " " + id + " " + pwd);

		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"UTF-8\">\r\n");
		out.write("<title>Why use Servlet</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("<h1> " + name + " " + id + " " + pwd + "</h1>\r\n");
		out.write("<h1>안녕</h1>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

}
