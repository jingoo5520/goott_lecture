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
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/AjaxJSP");
		Connection conn = ds.getConnection();
		return conn;
	}
	
	public static void connClose(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	public static void connClose(Statement stmt, Connection conn) throws SQLException {
		stmt.close();
		conn.close();
	}
}
