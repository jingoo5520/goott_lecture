package com.jsp.inputtpyes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VariousInputTypeServlet2")
public class VariousInputTypeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VariousInputTypeServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		Enumeration<String> params = request.getParameterNames();
		
//		Map<String, List<String>> ;
		
		while(params.hasMoreElements()) {
			String paraName = params.nextElement();
//			String value = request.getParameter(paraName);
//			System.out.println(paraName + ": " + value);

			List<String> valueList = new ArrayList<String>();
			
			String[] valueArr = request.getParameterValues(paraName);
			
			// 일반 배열을 ArrayList에 담는
			// 방법1) 
//			for(String s : valueArr) {
//				valueList.add(s);
//			}
			
			// 방법2)
			valueList = Arrays.asList(valueArr);
			
			System.out.println("params: " + paraName + ": " + valueList);
		}
		
		Map<String, String[]> paraMap = request.getParameterMap();
		
		for(Map.Entry<String, String[]> entry : paraMap.entrySet()) {
			System.out.println("paraMap " + entry.getKey() + ": " + Arrays.toString(entry.getValue()));
		}
		
	}

}
