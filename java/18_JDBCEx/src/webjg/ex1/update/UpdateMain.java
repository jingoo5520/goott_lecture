package webjg.ex1.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateMain {

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
			String dName = "인사부";
			
			
			String query = "update departments set department_name = '" + dName + "' where department_name = '총무부'";
			System.out.println(query);
			
			stmt = null;
			
			try {
				stmt = conn.createStatement();
				
				int result = stmt.executeUpdate(query);
				
				if(result == 1) {
					System.out.println("수정 완료");
				}
				
			} catch (SQLException e) {
				System.out.println("에러발생: " + e);
//				e.printStackTrace();
			}
		}
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
