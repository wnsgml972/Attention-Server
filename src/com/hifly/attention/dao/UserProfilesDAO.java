package com.hifly.attention.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hifly.attention.debuger.Debuger;

public class UserProfilesDAO 
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static UserProfilesDAO instance;
		
	synchronized public static UserProfilesDAO getInstance(){
		if(instance == null){
			instance = new UserProfilesDAO();
		}
		return instance;
	}
	
	private UserProfilesDAO()
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

		} catch (ClassNotFoundException e) 
		{
			Debuger.printError(e);
		} catch (SQLException e)
		{
			Debuger.printError(e);
		}
	}
	
	public boolean insertUserProfiles(String uuid, String profile_url)
	{
		String sql = "insert into user_profiles values(?,?)";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			pstmt.setString(2, profile_url);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			Debuger.printError(e);
			return false;
		}
		
		return true;
	}
	
	public void updateUserProfilesURL(String uuid, String profile_url)
	{
		String sql = "update user_profiles set profile_url=? where uuid=?";
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile_url);
			pstmt.setString(2, uuid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			Debuger.printError(e);
		}		
	}
	
	public String getUserProfilesURL(String uuid)
	{
		String sql = "select profile_url from user_profiles where uuid=?";
		
		String result = null;
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				result = rs.getString("profile_url");
			}
		} catch (SQLException e) {
			Debuger.printError(e);
		}
		return result;
	}

	
	public void end()
	{
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			Debuger.printError(e);
		}
	}
}