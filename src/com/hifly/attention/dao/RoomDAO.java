package com.hifly.attention.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.hifly.attention.client.Room;
import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;

public class RoomDAO 
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static RoomDAO instance;
		
	synchronized public static RoomDAO getInstance(){
		if(instance == null){
			instance = new RoomDAO();
		}
		return instance;
	}
	
	private RoomDAO()
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

		} catch (ClassNotFoundException e) {
			Debuger.printError(e);
		} catch (SQLException e) {
			Debuger.printError(e);
		}
	}	
	
	public void updateTime(String time, String room_uuid)
	{
		String sql = "update room set time=? where room_uuid=?";
		
		// select를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			pstmt.setString(2, room_uuid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			Debuger.printError(e);
		}
	}
	
	public Room getRoom(String room_uuid)
	{
		String sql = "select * from room where room_uuid=?";
		Room room = new Room();
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_uuid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				room.setRoomUuid(rs.getString("room_uuid"));
				room.setContent(rs.getString("content"));
				room.setTime(rs.getString("time"));
				room.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			Debuger.printError(e);
		}
		return room;
	}
	
	
	public HashMap<String, Room> getRooms(){
		
		String sql = "select * from room";
		HashMap<String, Room> rooms = new HashMap<String, Room>();
			
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Room room = new Room();
				room.setRoomUuid(rs.getString("room_uuid"));
				room.setContent(rs.getString("content"));
				room.setTime(rs.getString("time"));
				room.setTitle(rs.getString("title"));

				rooms.put(room.getRoomUuid(), room);
			}
		} catch (SQLException e) {
			Debuger.printError(e);
		}
		return rooms;
	}
	
	public boolean insertRoom(String room_uuid, String content, String time, String title)
	{
		String sql = "insert into room values(?,?,?,?)";
		
		// select 를 수행하면 데이터정보가 ResultSet 클래스의 인스턴스로 리턴됨.		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_uuid);
			pstmt.setString(2, content);
			pstmt.setString(3, time);
			pstmt.setString(4, title);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			Debuger.printError(e);
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
			Debuger.printError(e);
		}
	}
}