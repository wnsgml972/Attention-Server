package com.hifly.attention.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import com.hifly.attention.debuger.Debuger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private String uuid;
	private String name;
	private String stateMessage;
	private String tel;
	private final String ip;
	private boolean isLogin;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public User(Socket socket) {
		this.socket = socket;
		this.ip = socket.getInetAddress().getHostAddress();
		//uuid = UUID.randomUUID().toString().replace("-", "");
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			Debuger.printError(e);
		}
		isLogin = true;
	}
	public void sendInt(int port) {
		try {
			dos.writeInt(port);
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
	public String readUTF() {
		try {
			String message = dis.readUTF();
			return message;
		}
		catch (Exception e) {
			Debuger.printError(e);		
		}
		return null;
	}
	public void sendUTF(String message) {
		try {
			dos.writeUTF(message);
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
	
	public void disConnection(){
		try {
			socket.close();
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
}
