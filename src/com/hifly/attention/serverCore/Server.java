package com.hifly.attention.serverCore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.hifly.attention.client.Room;
import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.UnChangableValues;

public class Server {

	private ServerSocket serverSocket;
	public static HashMap<String, User> users;
	public static HashMap<String, Room> rooms;
	
	public Server() {
		init();
		acceptClient();
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(UnChangableValues.SERVER_PORT);
			users = new HashMap<String, User>();
			
			/*
			 * 데이터 베이스 부분
			 * */
			
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
	
	public void acceptClient() {
		while(true) {
			Socket socket;
			try {
				Debuger.log(this.toString(), "\n\nListen");
				socket = serverSocket.accept();
				Debuger.log(this.toString(), "Accept! " + socket.getInetAddress().getHostAddress() + "님이 접속하였습니다.");
				ServiceThread serviceThread = new ServiceThread(socket);
				serviceThread.start();
			} catch (IOException e) {
				Debuger.printError(e);
			}
		}
	}
	
	public static boolean alreadyExistUser(String uuid){
		Iterator<String> it = users.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			User user = users.get(key);
			if(uuid.equals(user.getUuid())){
				return true;
			}
		}
		return false;
	}
	public static void removeUsers(String uuid){ //need database
		Iterator<String> it = users.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			User user = users.get(key);
			if(uuid.equals(user.getUuid())){
				users.remove(key);
				return;
			}
		}
	}
	public static void removeRooms(String userUuid, String roomUuid){ //need database
		Iterator<String> it = rooms.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Room room = rooms.get(key);
			if(roomUuid.equals(room.getUuid())){
				
				String result = room.removeUser(userUuid);	//유저 제거
				
				if(result.equals("userRemove")){
					rooms.put(roomUuid, room);	//room 최신화
					Debuger.log("Server", "remove User success");
				}
				else if(result.equals("roomRemove")){
					rooms.remove(roomUuid);
					Debuger.log("Server", "remove Room success");
				}else{
					Debuger.log("Server", "remove User fail");					
				}				
			}
		}
	}
}