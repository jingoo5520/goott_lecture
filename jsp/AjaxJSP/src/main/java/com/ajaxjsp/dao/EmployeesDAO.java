package com.ajaxjsp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.dto.EmployeeDTO;
import com.ajaxjsp.vo.DepartmentVO;
import com.ajaxjsp.vo.EmployeeVO;
import com.ajaxjsp.vo.JobsVO;

public interface EmployeesDAO {
	List<EmployeeVO> selectAllEmp(String orderMethod, String searchName) throws NamingException, SQLException;
	List<JobsVO> selectAllJobs() throws NamingException, SQLException;
	List<DepartmentVO> selectAllDepartments() throws NamingException, SQLException;
	
	// 사원 저장(저장 프로시저)
	String insertEmp(EmployeeDTO emp) throws NamingException, SQLException;
	
	EmployeeVO selectEmployeeByEmpNo(int empNo) throws NamingException, SQLException;
	
	List<EmployeeVO> selectByEmpName(String searchName, String orderMethod) throws NamingException, SQLException;
}
