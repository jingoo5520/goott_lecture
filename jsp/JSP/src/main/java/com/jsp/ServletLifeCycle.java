package com.jsp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletLifeCycle")
public class ServletLifeCycle extends HttpServlet {
	
	int initCount = 1;
	int destroyCount = 1;
	int doGetCount = 1;

	private static final long serialVersionUID = 1L;
       
    public ServletLifeCycle() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
    	// 현재 서블릿이 실행되기 전에 호출되는 메서드(초기에 한번만 실행됨)
    	System.out.println("init() 메서드는 첫 요청에서만 호출됨" + this.initCount++);
	}

	@Override
	public void destroy() {
		// 톰캣이 종료될 때 호출
		System.out.println("destroy() 메서드는 종료될 때 호출됨" + this.destroyCount++);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메서드가 호출됨" + this.doGetCount++);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
