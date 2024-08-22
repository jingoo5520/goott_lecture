package com.webkjg.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.webkjg.controller.service.DuplicateMobileService;
import com.webkjg.dto.FriendDTO;
import com.webkjg.dto.ModifyNameDto;
import com.webkjg.dto.SearchNameDTO;

public class FriendDBInsert {
	public static FriendDTO getFriendData() {

		FriendDTO friend = null;

		Scanner sc = new Scanner(System.in);

		String name = "";

		do {
			System.out.print("친구 이름 입력 >>> ");
			name = sc.nextLine();

		} while (name.equals(""));

		boolean result = false;
		String mobile = "";

		// unique 조건으로 인해 중복체크 필요
		do {
			System.out.print("친구 전화번호 입력 >>> ");
			mobile = sc.nextLine();

			try {
				result = DuplicateMobileService.getInstance().duplicateMobileService(mobile);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} while (result);

		System.out.print("친구 주소 입력 >>> ");
		String addr = sc.nextLine();

		friend = new FriendDTO(name, mobile, addr);
		System.out.println(friend);

		return friend;
	}

	public static SearchNameDTO getSearchByNameData() {
		Scanner sc = new Scanner(System.in);

		String name = "";

		do {
			System.out.print("조회할 친구 이름 검색 >> ");
			name = sc.nextLine();

		} while (name.equals(""));

		return new SearchNameDTO(name);
	}
	
	public static ModifyNameDto getSearchByFriendNoData() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정을 원하는 친구의 friendNo 입력 >> ");
		
		int friendNo = sc.nextInt();
		
		System.out.println(friendNo);
		
		System.out.print("수정할 이름 입력 >> ");
		
		String friendName = sc.nextLine();
		
		return new ModifyNameDto(friendNo, friendName);
	}
}
