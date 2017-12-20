package com.hifly.attention.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;


import com.hifly.attention.debuger.Debuger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
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
	
	public void setMessageSocket(Socket messageSocket){
		this.messageSocket = messageSocket;
		this.ip = messageSocket.getInetAddress().getHostAddress();

		try {
			dis = new DataInputStream(messageSocket.getInputStream());
			dos = new DataOutputStream(messageSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}
	
	public void setFileSocket(Socket fileSocket){
		this.fileSocket = fileSocket;
		
		try {
			f_dis = new DataInputStream(fileSocket.getInputStream());
			f_dos = new DataOutputStream(fileSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	public void profileSend(String uuid, String profile_url, String file_name) {
		
		File file = new File(profile_url + "/" + file_name);
		FileInputStream fis;
		BufferedInputStream bis;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			int len = 0;
			
			byte[] data = new byte[1024];
			while((len = bis.read(data)) != -1) {
				f_dos.write(data, 0, len);
			}
			f_dos.flush();
			
		} catch (FileNotFoundException e) {
			Debuger.printError(e);
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

	public void disConnection() {
		try {
			messageSocket.close();
			fileSocket.close();
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
}
