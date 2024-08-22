package webjg.ex2.preparedstmt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatmentMain {

	public static void main(String[] args) {

		String id = "hr";
		String pwd = "1234";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결성공: " + conn.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (conn != null) {
			// beforeInsert(conn);
			
			int empNo = 104;

			// 1) 쿼리문 준비
			String query = "select * from employees";

			// 2) preparedStatement 객체 준비
			pstmt = null;

			try {
				pstmt = conn.prepareStatement(query);
//				 pstmt.setInt(1, empNo); // 첫 번째 ?에 empNo 값 세팅

				rs = pstmt.executeQuery();
				while (rs.next()) {
					int employee_id = rs.getInt("EMPLOYEE_ID");
					String first_name = rs.getString("FIRST_NAME");
					String last_name = rs.getString("LAST_NAME");
					String email = rs.getString("EMAIL");
					String phone_number = rs.getString("PHONE_NUMBER");
					Date hire_date = rs.getDate("HIRE_DATE");
					String job_id = rs.getString("JOB_ID");
					float salary = rs.getFloat("SALARY");
					float commission_pct = rs.getFloat("COMMISSION_PCT");
					int manager_id = rs.getInt("MANAGER_ID");
					int department_id = rs.getInt("DEPARTMENT_ID");

					System.out.println(employee_id + " " + first_name + " " + last_name + " " + email + " "
							+ phone_number + " " + hire_date + " " + job_id + " " + salary + " " + commission_pct + " "
							+ manager_id + " " + department_id + " ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		try {
			rs.close();
			pstmt.close();
			// 모든 작업 후 연결 끊기
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void beforeInsert(Connection conn) {
		int deptNo = 310;
		String dName = "자재부";
		int managerId = 104;
		int locationId = 1800;
		
		String query = "insert into departments values(?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deptNo);
			pstmt.setString(2, dName);
			pstmt.setInt(3, managerId);
			pstmt.setInt(4, locationId);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("저장 성공");
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
