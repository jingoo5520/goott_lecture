package com.webkjg.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.webkjg.controller.Action;
import com.webkjg.controller.service.FriendManagermentService;

public class FriendsDBView {

	public static void main(String[] args) {
		while(true) {
			outputMenu();
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();
			
			FriendManagermentService fms = Action.getInstance().getService(menu);
			
			try {
				fms.toDo();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
//				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println(e);
//				e.printStackTrace();
			}
		}
	}

	private static void outputMenu() {
		System.out.println("********************************");
		System.out.println("            친구관리 V1            ");
		System.out.println("********************************");
		System.out.println("** 1. 친구 목록 전체 조회           **");
		System.out.println("** 2. 친구 입력                  **");
		System.out.println("** 3. 친구 조회 (이름 조회)         **");
		System.out.println("** 4. 친구 수정 (이름 수정)         **");
		System.out.println("** 5. 친구 수정 (전화번호 수정)      **");
		System.out.println("** 6. 친구 수정 (주소 수정)         **");
		System.out.println("** 7. 친구 삭제                  **");
		System.out.println("** 9. 종료                      **");
		System.out.println("********************************");
		
		System.out.print("메뉴 번호 입력 >> ");
	}

}
