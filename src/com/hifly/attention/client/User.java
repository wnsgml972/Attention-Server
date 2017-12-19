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
	private String tel;
	private String stateMessage;
	private String ip;	
	
	private String P2PChatUUID;  //1:1Àü¿ë uuid

	private Socket messageSocket;
	private DataInputStream dis;
	private DataOutputStream dos;

	private Socket fileSocket;
	private DataInputStream f_dis;
	private DataOutputStream f_dos;

	public User() {
	}

	public User(Socket messageSocket) {
		this.messageSocket = messageSocket;
		this.ip = messageSocket.getInetAddress().getHostAddress();

		try {
			dis = new DataInputStream(messageSocket.getInputStream());
			dos = new DataOutputStream(messageSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
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
		} catch (IOException e) {
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
	
/*	public byte[] readBytes() {
		try {
			byte[] bytes;
			
			return bytes;
		} catch (IOException e) {
			Debuger.printError(e);
		}
		return null;
	}*/

	public void sendByte(byte[] bytes) {
		try {
			f_dos.write(bytes, 0, bytes.length);
			//f_dos.write(b, off, len);
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	public void disConnection() {
		try {
			messageSocket.close();
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	public void cloneUser(User user) {
		uuid = user.getUuid();
		name = user.getName();
		tel = user.getTel();
		stateMessage = user.getStateMessage();
	}

	public void setFileStream(Socket socket) {
		this.fileSocket = socket;
		try {
			f_dis = new DataInputStream(fileSocket.getInputStream());
			f_dos = new DataOutputStream(fileSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
}
