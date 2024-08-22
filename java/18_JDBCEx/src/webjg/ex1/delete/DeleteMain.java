package webjg.ex1.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMain {

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
			int deptNo = 290;
			String query = "delete from departments where department_id = " + deptNo;
			System.out.println(query);
			
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				if (result == 1) {
					System.out.println("삭제 완료");
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
