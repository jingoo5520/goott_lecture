package com.webkjg.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static String id = "hr";
	private static String pwd = "1234";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		return conn;
	}
	
	static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) throws SQLException {
		rs.close();
		pstmt.close();
		conn.close();
	}
	
	static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
		pstmt.close();
		conn.close();
	}
	
}
