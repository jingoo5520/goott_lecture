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

@WebServlet("/getEmployee.do")
public class GetEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEmployeeServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empNo = Integer.parseInt(request.getParameter("empNo"));

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		try {
			EmployeeVO emp = dao.selectEmployeeByEmpNo(empNo);

			// 응답 json 만들기
			// JSON-SIMPLE LIB
			JSONObject jo = new JSONObject();
			SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			jo.put("status", "ok");
			jo.put("outputDate", date.format(new Date()));

			JSONObject j = new JSONObject();
			j.put("employee_id", emp.getEmployee_id());
			j.put("first_name", emp.getFirst_name());
			j.put("last_name", emp.getLast_name());
			j.put("email", emp.getEmail());
			j.put("phone_number", emp.getPhone_number());
			Date d = emp.getHire_date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			j.put("hire_date", s.format(d));
			j.put("job_id", emp.getJob_id());
			j.put("salary", emp.getSalary());
			j.put("commission_pct", emp.getCommission_pct());
			j.put("manager_id", emp.getManager_id());
			j.put("department_id", emp.getDepartment_id());
			j.put("department_name", emp.getDepartment_name());

			PrintWriter out = response.getWriter();
			
			jo.put("employee", j);
			out.print(jo.toJSONString());
			out.close();

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
