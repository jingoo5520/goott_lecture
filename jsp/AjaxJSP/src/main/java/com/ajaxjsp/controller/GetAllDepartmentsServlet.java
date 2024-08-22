package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOImpl;
import com.ajaxjsp.vo.DepartmentVO;

/**
 * Servlet implementation class GetAllDepartmentsServlet
 */
@WebServlet("/departments")
public class GetAllDepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			List<DepartmentVO> list = dao.selectAllDepartments();
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			jo.put("status", "ok");
			jo.put("outputDate", date.format(new Date()));
			jo.put("size", list.size());
			if(list.size() > 0) {
				for(DepartmentVO d : list) {
					JSONObject j = new JSONObject();
					j.put("department_id", d.getDepartment_id());
					j.put("department_name", d.getDepartment_name());
					j.put("manager_id", d.getManager_id());
					j.put("location_id", d.getLocation_id());
					ja.add(j);
				}
				jo.put("datas", ja);
				out.print(jo.toJSONString());
				out.close();
			}
		} catch(NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
