package com.hifly.attention.serverCore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.hifly.attention.client.Room;
import com.hifly.attention.client.User;
import com.hifly.attention.dao.UserDAO;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.UnChangableValues;

public class MessageServer {
	
	private ServerSocket serverSocket;
	
	/*
	HashMap으로 관리하기 때문에 따로 remove 해주지 않아도 같은 uuid로 들어오면 그대로 똑같은 key에 데이터가 들어오기 때문에
	따로 remove를 관리할 필요가 없다!   개좋음...
	*/
	public static HashMap<String, User> users;
	public static HashMap<String, Room> rooms;
	
	public MessageServer() {
		init();
		acceptClient();
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(UnChangableValues.MESSAGE_SERVER_PORT);
			users = new HashMap<String, User>();
			rooms = new HashMap<String, Room>();
			
			/* Init HashUser */
			users = UserDAO.getInstance().getUsers();
			
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
	
	public void acceptClient() {
		while(true) {
			Socket messageSocket;
			try {
				Debuger.log(this.toString(), "\n\nMessage Listen");
				messageSocket = serverSocket.accept();
				Debuger.log(this.toString(), "Accept! messageSocket" + messageSocket.getInetAddress().getHostAddress() + "님이 접속하였습니다.");
				ServiceThread serviceThread = new ServiceThread(messageSocket);
				serviceThread.start();
			} catch (IOException e) {
				Debuger.printError(e);
			}
		}
	}
}