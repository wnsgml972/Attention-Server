package com.hifly.attention.serverCore;

import java.net.Socket;
import java.util.HashMap;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.ChangableValues;

public class ServiceThread extends Thread {
	private User user;
	private static int SERVER_UDP_PORT = 10036;
	
	public ServiceThread(Socket socket) {
		user = new User(socket);
		Debuger.log(this.toString(), "Thread : " + Thread.activeCount());
	}
	
	public void run() {
		while (true) {
			String message = user.readUTF();
			Debuger.log(this.toString(), message + " receivce!");
			if (message == null) {
				Server.users.remove(user);
				Debuger.log(this.toString(), "Thread : " + Thread.activeCount());
				Debuger.log(this.toString(), user.getIp() + "나감!");
				return;
			} else if (message.startsWith("sendName")) {
				String split[] = message.split(" ");
				if (split[1] == null)
					user.setName("이름없음");
				else
					user.setName(split[1]);
				
				user.setUuid(split[2]);
				Server.users.put(split[2], user);
				Debuger.log(this.toString(), user.getIp() + "나감!");
				Debuger.log(this.toString(), user + " 접속!");
				
			} else if (message.startsWith("SendMsg")) {
				String split[] = message.split(" ");
				Debuger.log(this.toString(), split[1] + "!!!");
				int bufsize = Integer.parseInt(split[1]);
				user.sendUTF("VoiceConnection " + Server.users.get(1).getIp());

			} else if (message.startsWith("calling")) {
				String split[] = message.split(" ");
				HashMap<String, User> users = Server.users;
				User opponent_user = users.get(split[1]);
				if (user != null) {
					user.sendUTF(opponent_user.getIp());
				} else {
					user.sendUTF(null);
				}
				opponent_user.sendUTF("callToMe " + user.getName() + " " + user.getIp());
			}
		}
	}
}