package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ajaxjsp.etc.OutputJSONForError;
import com.ajaxjsp.vo.JobsVO;

@WebServlet("/jobs")
public class GetJobsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		jo.put("status", "ok");
		jo.put("outputDate", date.format(new Date()));
		try {
			List<JobsVO> list = dao.selectAllJobs();
			jo.put("size", list.size());
			for(JobsVO jobs : list) {
				JSONObject j = new JSONObject();
				j.put("job_id", jobs.getJob_id());
				j.put("job_title", jobs.getJob_title());
				j.put("min_salary", jobs.getMin_salary());
				j.put("max_salary", jobs.getMax_salary());
				ja.add(j);
			}
		jo.put("datas", ja);
		out.print(jo.toJSONString());
		out.close();
		} catch (NamingException | SQLException e) {
			OutputJSONForError.outputJson(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
