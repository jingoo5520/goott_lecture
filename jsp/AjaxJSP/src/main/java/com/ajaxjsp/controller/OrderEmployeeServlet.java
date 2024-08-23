package com.ajaxjsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.DBConnection;

@WebServlet("/orderEmp.do")
public class OrderEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderEmployeeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("orderMethod"));
		
		
		/*
		 * Connection conn; try { conn = DBConnection.dbConnect();
		 * 
		 * if(conn != null) { String query = "select e.*, d.department_name\r\n" +
		 * "from employees e inner join departments d\r\n" +
		 * "on e.department_id = d.department_id\r\n" + "order by e.";
		 * 
		 * query += request.getParameter("orderMethod");
		 * 
		 * System.out.println(query);
		 * 
		 * 
		 * } } catch (NamingException | SQLException e) { e.printStackTrace(); }
		 */
		
		
		
		
		
		
	}


}
