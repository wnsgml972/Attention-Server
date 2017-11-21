package com.HIFLY.Attention.client;
import java.util.Vector;

public class Room {
	private Vector<User> users;
	public void addUser(User user) {
		users.add(user);
	}
}
