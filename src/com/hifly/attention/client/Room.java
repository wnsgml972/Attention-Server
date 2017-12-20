package com.hifly.attention.client;

import java.util.UUID;
import java.util.Vector;

import com.hifly.attention.serverCore.MessageServer;
import com.hifly.attention.values.Protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Room {
	private String roomUuid;
	private String time;
	private String senderName;
	private String chatContent;
	private String senderUuid;
	
	private Vector<String> users;

	public Room() { }
	
	public Room(String roomUuid, String time, String senderName, String chatContent, String senderUuid) {
		this.roomUuid = roomUuid;
		this.time = time;
		this.senderName = senderName;
		this.chatContent = chatContent;
		this.senderUuid = senderUuid;
		users = new Vector<String>();
	}
	
	public Room(String roomUuid) {
		this.roomUuid = roomUuid;
	}

	public void addUser(String Uuid) {
		users.add(Uuid);
	}

	public void broadcast(String message) {
		for (int i = 0; i < users.size(); i++) {
			User user = MessageServer.users.get(users.get(i));
			user.sendUTF(message);
		}
	}

	public String removeUser(String Uuid) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).equals(Uuid)) {
				users.remove(i);
				return Protocol.USER_REMOVE_PROTOCOL;
			}
		}

		if (users.size() == 0) {
			return Protocol.ROOM_REMOVE_PROTOCOL;
		}
		return Protocol.ROOM_FAIL_PROTOCOL;
	}




}