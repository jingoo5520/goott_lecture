package com.spring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String id = "root";
	private static final String pwd = "1234";
	private static final String url = "jdbc:mysql://localhost:3306/my?serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useSSL=false";

	@Test
	public void testConnection() throws ClassNotFoundException {

		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(url, id, pwd)) {
			
			if(con != null) {
				System.out.println(con.toString());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
