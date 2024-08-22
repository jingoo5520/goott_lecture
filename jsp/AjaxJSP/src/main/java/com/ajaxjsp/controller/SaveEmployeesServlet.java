package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOImpl;
import com.ajaxjsp.dto.EmployeeDTO;

@WebServlet("/saveEmp.do")
public class SaveEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveEmployeesServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("사원저장 서블릿 호출");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		
		String jobId = request.getParameter("jobId");
		String email = request.getParameter("email");
		
		Date hireDate = Date.valueOf(request.getParameter("hireDate"));
		
		float salary = Float.parseFloat(request.getParameter("salary"));
		float commissionPct = Float.parseFloat(request.getParameter("commissionPct"));
		
		int managerId = Integer.parseInt(request.getParameter("manager"));
		int departmentId = Integer.parseInt(request.getParameter("department"));
		
		EmployeeDTO empDto = new EmployeeDTO(0, firstName, lastName, email, phoneNumber, hireDate, jobId, salary, commissionPct, managerId, departmentId);
		
		PrintWriter out = response.getWriter();
		
		// employee_id (pk)
		// max(employee_id) + 1 값을 얻어와 다음 저장될 사원의 employee_id 결정, insert
		
		// 저장 프로시저 사용
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			dao.insertEmp(empDto);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
