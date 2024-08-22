package com.webkjg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webkjg.dto.FriendDTO;
import com.webkjg.dto.ModifyNameDto;
import com.webkjg.dto.SearchNameDTO;
import com.webkjg.vo.Friend;

public class FriendsMgmDAOImpl implements FriendsMgmDAO {

	private static FriendsMgmDAOImpl instance = null;
	
	private FriendsMgmDAOImpl () {}
	
	public static FriendsMgmDAOImpl getInstance() {
		if(instance == null) {
			instance = new FriendsMgmDAOImpl();
		}
		
		return instance;
	}
	
	@Override
	public List<Friend> selectAllFriends() throws ClassNotFoundException, SQLException {
		List<Friend> list = new ArrayList<Friend>();
		
		Connection conn = DBConnection.getConnection();
		
		String query = "select * from friends";

		PreparedStatement pstmt = conn.prepareStatement(query);

		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			list.add(new Friend(rs.getInt("friendNo"), rs.getString("friendName"), rs.getString("mobile"),
					rs.getString("addr")));
		}

		return list;
	}

	@Override
	public int isDuplecateMobile(String mobile) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		if(conn != null) {
			String query = "select count(*) as cnt from friends where mobile = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, mobile);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("cnt");
			}
		}
		
		DBConnection.close(rs, pstmt, conn);
		return result;
	}

	@Override
	public int getNextFriendNo() throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		int result = 0;
		
		
		if(conn != null) {
			String query = "select max(friendNo) from friends";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1) + 1;
			}
		}
		
		return result;
	}

	@Override
	public int insertFriend(int friendNo, FriendDTO friendDTO) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getConnection();
		int result = 0;
		
		
		if(conn != null) {
			String query = "insert into friends values(?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, friendNo);
			pstmt.setString(2, friendDTO.getFriendName());
			pstmt.setString(3, friendDTO.getMobile());
			pstmt.setString(4, friendDTO.getAddr());
			
			result = pstmt.executeUpdate();
			
			DBConnection.close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public List<Friend> selectFriendByName(SearchNameDTO dto) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		List<Friend> list = new ArrayList<Friend>();
		
		if(conn != null) {
			String query = "select * from friends where friendName = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getFriendName());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Friend(rs.getInt("friendNo"), rs.getString("friendName"), rs.getString("mobile"),
						rs.getString("addr")));
			}
		}
		return list;
	}

	@Override
	public int modifyFriendName(ModifyNameDto dto) throws ClassNotFoundException, SQLException {
		System.out.println(dto.getFriendName());
		System.out.println(dto.getFriendNo());
		
		Connection conn = DBConnection.getConnection();
		int result = 0;
		
		if(conn != null) {
			String query = "update friends set friendname = ? where friendNo = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getFriendName());
			pstmt.setInt(2, dto.getFriendNo());
			result = pstmt.executeUpdate();
		}
		
		System.out.println(result);
		return result;
	}
}
