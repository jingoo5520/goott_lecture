package webjg.ex1.select;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectMain {

	public static void main(String[] args) {
		String id="hr";
		String pwd="1234";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결성공: " + conn.toString());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		
//		
//		if(conn != null) {
////			System.out.println("조회할 사원 번호 입력 >> ");
////			Scanner sc = new Scanner(System.in);
////			int no = sc.nextInt();
//			
//			// (1) 실행할 쿼리문 준비
//			String query = "select * from employees where employee_id = " + 104;
//
//			
//			Statement stmt = null;
//			ResultSet rs = null;
//			
//			// (2) Statment 객체: Connection 객체가 연결중인 DB 서버로 쿼리문을 전송하고 실행하게 하는 역할
//			try {
//				stmt = conn.createStatement();
//				
//				// (3) ResultSet 객체: 쿼리문 실행 후 
//				rs = stmt.executeQuery(query);
//				System.out.println(rs.toString());
//				
//				while(rs.next()) {
//					int employee_id = rs.getInt("EMPLOYEE_ID");
//					String first_name = rs.getString("FIRST_NAME");
//					String last_name = rs.getString("LAST_NAME");
//					String email = rs.getString("EMAIL");
//					String phone_number = rs.getString("PHONE_NUMBER");
//					Date hire_date = rs.getDate("HIRE_DATE"); 
//					String job_id = rs.getString("JOB_ID");
//					float salary = rs.getFloat("SALARY");
//					float commission_pct = rs.getFloat("COMMISSION_PCT");
//					int manager_id = rs.getInt("MANAGER_ID");
//					int department_id = rs.getInt("DEPARTMENT_ID");
//					
//					System.out.println(
//							employee_id + " " + 
//							first_name + " " + 
//							last_name + " " +
//							email + " " +
//							phone_number + " " +
//							hire_date + " " +
//							job_id + " " +
//							salary + " " +
//							commission_pct + " " +
//							manager_id + " " +
//							department_id + " "
//							);
//					
//				}
//				
//				
//				
//			} catch (Exception e) {
////				e.printStackTrace();
//				if(e instanceof SQLSyntaxErrorException) {
//					System.out.println("쿼리문을 올바르게 작성하시오.");
//				}
//			}
//			
//			
//			try {
//				stmt.close();
//				rs.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//			
//			
//		}
		
		// 연습: 검색할 이름을 입력받아, 쿼리문에서 like연산자를 이용하여 이름을 조회
		
		Statement stmt = null;   
				
		if(conn != null) {
			try {
				stmt = conn.createStatement();
				
				System.out.print("검색할 이름 >> ");
				Scanner sc = new Scanner(System.in);
				
				String name = sc.nextLine();
				String query = "select * from employees where first_name like '" + name + "'";
				
				System.out.println(query);
				
				ResultSet rs = stmt.executeQuery(query);
				
				System.out.println(rs.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
