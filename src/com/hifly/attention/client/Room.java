package com.hifly.attention.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Room {
	private Vector<User> users;
	public void addUser(User user) {
		users.add(user);
	}
}