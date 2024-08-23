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
import com.ajaxjsp.vo.EmployeeVO;

@WebServlet("/findEmpByName.do")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchEmployeeServlet() {
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchName = request.getParameter("searchName");
		String orderMethod = request.getParameter("orderMethod");
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			List<EmployeeVO> list = dao.selectByEmpName(searchName, orderMethod);
			
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			jo.put("status", "ok");
			jo.put("outputDate", date.format(new Date()));
			jo.put("size", list.size());
			if(list.size() > 0) {
				for(EmployeeVO e : list) {
					JSONObject j = new JSONObject();
					j.put("employee_id", e.getEmployee_id());
					j.put("first_name", e.getFirst_name());
					j.put("last_name", e.getLast_name());
					j.put("email", e.getEmail());
					j.put("phone_number", e.getPhone_number());
					Date d = e.getHire_date();
					SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
					j.put("hire_date", s.format(d));
					j.put("job_id", e.getJob_id());
					j.put("salary",e.getSalary());
					j.put("commission_pct", e.getCommission_pct());
					j.put("manager_id", e.getManager_id());
					j.put("department_id", e.getDepartment_id());
					j.put("department_name", e.getDepartment_name());
					ja.add(j);
				}
			}
			jo.put("datas", ja);
			out.print(jo.toJSONString());
			out.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
