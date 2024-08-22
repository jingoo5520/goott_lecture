import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) {
		Connection conn = null;
		
		String id = "hr";
		String pwd = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pwd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
		
		PreparedStatement pstmt = null;
		
		if(conn != null) {
			String query = "select * from departments where location_id = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, 1700);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(
							rs.getInt("department_id") + " " +
							rs.getString("department_name") + " " +
							rs.getInt("manager_id") + " " +
							rs.getInt("location_id") + " "
					);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
