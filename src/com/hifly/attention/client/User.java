package com.hifly.attention.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.Protocol;

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

	private String P2PChatUUID; // 1:1전용 uuid

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

	public void setMessageSocket(Socket messageSocket) {
		this.messageSocket = messageSocket;
		this.ip = messageSocket.getInetAddress().getHostAddress();

		try {
			dis = new DataInputStream(messageSocket.getInputStream());
			dos = new DataOutputStream(messageSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	public void setFileSocket(Socket fileSocket) {
		this.fileSocket = fileSocket;

		try {
			f_dis = new DataInputStream(fileSocket.getInputStream());
			f_dos = new DataOutputStream(fileSocket.getOutputStream());
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	public void profileSend(String uuid, String profile_url, String profile_name) {

		File file = new File(profile_url + "/" + profile_name);
		FileOutputStream fos;
		BufferedOutputStream bos;
		FileInputStream fis;
		BufferedInputStream bis;
		int size = 1024;

		Debuger.log(toString(), uuid + " " + profile_url + " " + profile_name);

		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			int len1 = 0;
			f_dos.writeUTF(Protocol.PROFILE_GO); // 프로필 보내!
			// 파일 read 후 send

			byte[] data1 = new byte[size];
			while ((len1 = f_dis.read(data1)) != -1) {
				bos.write(data1, 0, len1);
				Debuger.log(toString(), Integer.toString(len1));
			}
			bos.flush();
			// 여기까지 바이트로 파일 받아서 내 컴퓨터에 저장시킴

			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			int len2;

			byte[] data2 = new byte[size];
			while ((len2 = bis.read(data2)) != -1) {
				f_dos.write(data2, 0, len2);
				Debuger.log(toString(), Integer.toString(len2));
			}

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
