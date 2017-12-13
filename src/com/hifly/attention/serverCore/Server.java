package com.hifly.attention.serverCore;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.UnChangableValues;

public class Server {

	private ServerSocket serverSocket;
	public static HashMap<String, User> users;
	
	public Server() {
		init();
		acceptClient();
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(UnChangableValues.SERVER_PORT);
			users = new HashMap<String, User>();
		} catch (IOException e) {
			Debuger.printError(e);
		}	
	}
	
	public void acceptClient() {
		while(true) {
			Socket socket;
			try {
				Debuger.log(this.toString(), "Listen");
				socket = serverSocket.accept();
				Debuger.log(this.toString(), "Accept! " + socket.getInetAddress().getHostAddress() + "님이 접속하였습니다.");
				ServiceThread serviceThread = new ServiceThread(socket);
				serviceThread.start();
			} catch (IOException e) {
				Debuger.printError(e);
			}		
		}
	}
	
}