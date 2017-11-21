package com.HIFLY.Attention.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import com.HIFLY.Attention.debuger.Debuger;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
	private String name;
	private String uuid;//개인 식별자로 사용 예정
	private String ip;
	private boolean isLogin;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public User(Socket socket) {
		this.socket = socket;
		this.ip = socket.getInetAddress().getHostAddress();
		uuid = UUID.randomUUID().toString().replace("-", "");
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			Debuger.printError(e);
		}
		isLogin = true;
	}
	public String getUuid() {
		return uuid;
	}
	public Socket getSocket() {
		return socket;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
}
