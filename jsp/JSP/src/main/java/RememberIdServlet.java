

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rememberId.do")
public class RememberIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RememberIdServlet() {
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String rememberId = request.getParameter("rememberId"); 
		
		if(userId.equals("abcd") && userPwd.equals("1234")) {
			
			if(rememberId != null && rememberId.equals("Y")) {
				Cookie c = new Cookie("rememberId", userId);
				c.setMaxAge(3000);
				c.setPath("/");
				response.addCookie(c);
			} else {
				Cookie c = new Cookie("rememberId", userId);
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			
			response.sendRedirect("homepage.jsp");
			
		} else {
			System.out.println("로그인 실패");
		}
	}

}
