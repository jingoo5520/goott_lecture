package webjg.ex1.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMain {

	public static void main(String[] args) {
		String id="hr";
		String pwd="1234";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결성공: " + conn.toString());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn != null) {
			int deptNo = 300;
			String dName = "총무부";
			int managerId = 200;
			int locationId = 1700;
			
			String query = "insert into departments values(" + deptNo + ",'" + dName + "', " + managerId + ", " + locationId + ")";
			System.out.println(query);
			
			stmt = null;
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				if(result == 1) {
					System.out.println("저장 완료");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("에러발생: " + e);
//				e.printStackTrace();
			}
		}
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
