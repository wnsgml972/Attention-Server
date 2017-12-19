package com.hifly.attention.userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFriendsDAO 
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static UserFriendsDAO instance;
		
	synchronized public static UserFriendsDAO getInstance(){
		if(instance == null){
			instance = new UserFriendsDAO();
		}
		return instance;
	}
	
	private UserFriendsDAO()
	{
	    String jdbc_driver = "com.mysql.jdbc.Driver";
	    String jdbc_url = "jdbc:mysql://127.0.0.1/attention";
		String user = "root";
		String password = "1234";

		try {
			// JDBC 드라이버 로드
			Class.forName(jdbc_driver);
			// 데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
			conn = DriverManager.getConnection(jdbc_url, user, password);

		} catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void insertUserFriends(String uuid, String friend_uuid)
	{
		String sql = "insert into user_friends values(?,?)";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			pstmt.setString(2, friend_uuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void end()
	{
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}