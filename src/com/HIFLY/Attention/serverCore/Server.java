package com.HIFLY.Attention.serverCore;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import com.HIFLY.Attention.client.User;
import com.HIFLY.Attention.debuger.Debuger;

public class Server {
	private final int SERVER_PORT = 10035;
	public static Vector<User> users;
	private ServerSocket serverSocket;
	public Server() {
		init();
		acceptClient();
	}
	public void init() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			users = new Vector<User>();
		} catch (IOException e) {
			Debuger.printError(e);
		}	
	}
	public void acceptClient() {
		while(true) {
			Socket socket;
			try {
				System.out.println("Listen!");
				socket = serverSocket.accept();				
				System.out.println("Accept! " + socket.getInetAddress().getHostAddress() + "님이 접속하였습니다.");
				ServiceThread serviceThread = new ServiceThread(socket);
				serviceThread.start();
			} catch (IOException e) {
				Debuger.printError(e);
			}		
		}
	}
}
