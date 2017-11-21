package com.HIFLY.Attention.serverCore;
import java.net.Socket;

import com.HIFLY.Attention.client.User;
import com.HIFLY.Attention.voiceCore.VoiceRecvThread;

public class ServiceThread extends Thread {
	private User user;
	private VoiceRecvThread voiceRecvThread;
	private static int SERVER_UDP_PORT = 10036;
	public ServiceThread(Socket socket) {
		user = new User(socket);
		Server.users.add(user);
		System.out.println("Thread : " + Thread.activeCount());
	}

	public void run() {
		while (true) {
			String message = user.readUTF();
			if(message == null) {
				Server.users.remove(user);
				voiceRecvThread.interrupt();
				System.out.println(user.getIp() + "³ª°¨!");
				return;
			}
			else if(message.startsWith("SendMsg")) {
				String split[] = message.split(" ");
				System.out.println(split[1] + "!!!");
				int bufsize = Integer.parseInt(split[1]);
				user.sendInt(SERVER_UDP_PORT);
				voiceRecvThread = new VoiceRecvThread(bufsize, SERVER_UDP_PORT++);
				voiceRecvThread.start();
			}
		}
	}
}
