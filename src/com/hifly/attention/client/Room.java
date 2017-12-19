package com.hifly.attention.client;

import java.util.UUID;
import java.util.Vector;

import com.hifly.attention.serverCore.Server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Room {
	private String roomUuid;
	private String title;
	private String content;
	private String time;
	
	private Vector<String> users;

	public Room() {
		roomUuid = UUID.randomUUID().toString().replace("-", "");
	}

	public void addUser(String Uuid) {
		users.add(Uuid);
	}

	public void broadcast(String message) {
		for (int i = 0; i < users.size(); i++) {
			User user = Server.users.get(users.get(i));
			user.sendUTF(message);
		}
	}

	public String removeUser(String Uuid) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).equals(Uuid)) {
				users.remove(i);
				return "userRemove";
			}
		}

		if (users.size() == 0) {
			return "roomRemove";
		}

		return "fali";
	}
}