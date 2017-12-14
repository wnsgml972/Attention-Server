package com.hifly.attention.serverCore;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.Protocol;

public class ServiceThread extends Thread {
	private User user;
	
	public ServiceThread(Socket socket) {
		user = new User(socket);
		Debuger.log(this.toString(), "Thread : " + Thread.activeCount());
	}
	
	public void run() {
		while (true) {
			
			String message = user.readUTF();
			Debuger.log(this.toString(), "init receive  :  " + message);
			
			if (message == null) {
				//Server.users.remove(user);
				
				user.disConnection(); //단순히 소켓만 끊음
				Debuger.log(this.toString(), "  소켓 끊김!!");
				Debuger.log(this.toString(), "Thread : " + Thread.activeCount());
				Debuger.log(this.toString(), user.getIp() + "나감!");
				return;
				
			} else if (message.startsWith(Protocol.USER_ENROLL_PROTOCOL)) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				String name = split[1];
				String uuid = split[2];
				String stateMessage = split[3];
				String tel = split[4];
				
				if(Server.alreadyExistUser(uuid)){
					Debuger.log(this.toString(), user + " 접속!  start");
					user.sendUTF(Protocol.USER_EXIST_CHECK_PROTOCOL + Protocol.SPLIT_MESSAGE +  Protocol.USER_ENROLL_FAIL_PROTOCOL);
				}
				else{
					user.setName(name);
					user.setUuid(uuid);
					user.setStateMessage(stateMessage);
					user.setTel(tel);
					
					Server.users.put(uuid, user);
					
					user.sendUTF(Protocol.USER_EXIST_CHECK_PROTOCOL + Protocol.SPLIT_MESSAGE  +  Protocol.USER_ENROLL_SUCCESS_PROTOCOL);
					Debuger.log(this.toString(), user + " 접속!  start");
					Debuger.log(this.toString(), " 등록 성공");
				}
				
			} else if (message.startsWith(Protocol.USER_FRIENDS_REQUEST_PROTOCOL)) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				StringBuilder sb = new StringBuilder("");
				sb.append(Protocol.USER_FRIENDS_RESPONSE_PROTOCOL + Protocol.SPLIT_MESSAGE);
				Debuger.log(toString(), Integer.toString(Server.users.size()));				
				for(int i=1; i<split.length; i++) {
					Iterator<String> it = Server.users.keySet().iterator();
					while(it.hasNext()) {
						String key = it.next();
						User muser = Server.users.get(key);
						if(muser.getTel().equals(split[i]) && !user.getTel().equals(split[i])) {
							sb.append(muser.getUuid() + Protocol.SPLIT_MESSAGE + muser.getName() + Protocol.SPLIT_MESSAGE + muser.getTel() + Protocol.SPLIT_MESSAGE + muser.getStateMessage() + Protocol.SPLIT_MESSAGE);
						}
					}
				}
				
				Debuger.log(this.toString(), "리스펀스 메시지   " + sb.toString());
				user.sendUTF(sb.toString());
				
			} else if (message.startsWith(Protocol.ROOM_MESSAGE_PROTOCOL)) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				Debuger.log(this.toString(), split[1] + "  room");
				Debuger.log(this.toString(), split[2] + "  room");
				String roomUuid = split[1];
				String messageVal = split[2];
				
				Server.rooms.get(roomUuid).broadcast(messageVal);
				
			} else if (message.startsWith(Protocol.ROOM_IN_FIRST_PROTOCOL)) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				Debuger.log(this.toString(), split[1] + "  room");
				Debuger.log(this.toString(), split[2] + "  room");
				String roomUuid = split[1];
				String messageVal = split[2];
				
				Server.rooms.get(roomUuid).broadcast(messageVal);
				
			} else if (message.startsWith(Protocol.ROOM_OUT_PROTOCOL)) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				Debuger.log(this.toString(), split[1] + "  roomOut");
				String roomUuid = split[1];
				
				Server.removeRooms(user.getUuid(), roomUuid);
				
			} else if (message.startsWith("calling")) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				HashMap<String, User> users = Server.users;
				User opponent_user = users.get(split[1]);
				if (user != null) {
					user.sendUTF(opponent_user.getIp());
				} else {
					user.sendUTF(null);
				}
				opponent_user.sendUTF("callToMe" + Protocol.SPLIT_MESSAGE + user.getName() + Protocol.SPLIT_MESSAGE + user.getIp());
			} else if (message.startsWith("broadcast")) {
				String split[] = message.split(Protocol.SPLIT_MESSAGE);
				Debuger.log(this.toString(), split[1] + "   broadcast");
				String messageVal = split[1];
				broadcast(messageVal);
				
			}
		}
	}
	
	private void broadcast(String message){
		Iterator<String> it = Server.users.keySet().iterator();
		
		while(it.hasNext()){
			User user = Server.users.get(it.next());
			user.sendUTF(message);
		}
	}
}