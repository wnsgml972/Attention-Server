package com.hifly.attention.userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.hifly.attention.client.User;

public class UserDAO 
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static UserDAO instance;
		
	synchronized public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	private UserDAO()
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
	
/*	public User search(String userID, String userPW)
	{
		String sql = "select * from user where userID=? and userPW=?";
		User user;
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(4).equals("true"))	//만약 로그인 중이면 끝남
					return null;
				
				user.setUserID(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setEmail(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return user;
	}
	
		public void login(String userID)
	{
		String sql = "update user set login_check=? where userID=?";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "true");
			pstmt.setString(2, userID);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void logout(String userID)
	{
		String sql = "update user set login_check=? where userID=?";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "false");
			pstmt.setString(2, userID);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	public void updateTime(String uuid)
	{
		String sql = "update user set last_access_time=? where uuid=?";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, uuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(String uuid)
	{
		String sql = "select * from user where uuid=?";
		User user = new User();
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				user.setUuid(rs.getString("uuid"));
				user.setTel(rs.getString("tel"));
				user.setStateMessage(rs.getString("state_message"));
				user.setName(rs.getString("name"));
				user.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public HashMap<String, User> getUsers(){
		
		String sql = "select * from user";
		HashMap<String, User> users = new HashMap<String, User>();
		
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				User user = new User();
				user.setUuid(rs.getString("uuid"));
				user.setTel(rs.getString("tel"));
				user.setStateMessage(rs.getString("state_message"));
				user.setName(rs.getString("name"));
				user.setIp(rs.getString("ip"));

				users.put(user.getUuid(), user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean insertUser(String uuid, String name, String tel, String state_message, String ip)
	{
		String sql = "insert into user values(?,?,?,?,?,?)";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, state_message);
			pstmt.setString(5, ip);
			pstmt.setString(6, null);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
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