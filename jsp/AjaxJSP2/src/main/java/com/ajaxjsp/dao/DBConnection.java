package com.ajaxjsp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	public static Connection dbConnect() throws NamingException, SQLException {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/webkjg");
			Connection conn = ds.getConnection();
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void dbClose(Connection conn, ResultSet rs, Statement stmt) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	
	public static void dbClose(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}
}
