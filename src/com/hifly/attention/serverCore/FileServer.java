package com.hifly.attention.serverCore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.values.Protocol;
import com.hifly.attention.values.UnChangableValues;

public class FileServer extends Thread {

	private ServerSocket serverSocket;

	public FileServer() {
		init();
		this.start();
	}

	public void init() {
		try {
			serverSocket = new ServerSocket(UnChangableValues.FILE_SERVER_PORT);
		} catch (IOException e) {
			Debuger.printError(e);
		}
	}

	@Override
	public void run() {
		while (true) {
			Socket socket;
			DataInputStream dis;
			DataOutputStream dos;

			try {

				Debuger.log(this.toString(), "\n\nFile Listen");
				socket = serverSocket.accept();

				String ip = socket.getInetAddress().getHostAddress();
				Debuger.log(this.toString(), "Accept! fileSocket" + ip + "님이 접속하였습니다.");
				// 사실 여기까지만 필요함 ㅠㅠㅠ 밑에 주석이랑,  나중에 시간 있을 때 구조화하자
				
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				String message = dis.readUTF();
				Debuger.log(toString(), "Init File message :  " + message);
				String split[] = message.split(Protocol.SPLIT_MESSAGE);

				String protocolHeader = split[0];
				String fileName = split[1];
				byte[] bytes = null;
				Debuger.log(toString(), "Init File protocol header :  " + protocolHeader);
				
				if (protocolHeader.equals(Protocol.PROFILE_INSERT_PROTOCOL)) {
					int fileSize = Integer.parseInt(split[2]);
					bytes = new byte[fileSize];
					dis.readFully(bytes, 0, fileSize);
					File file = new File("C:/Users/hscom-018/git/Attention-Server/profiles" + "/" + fileName + ".jpeg");
					DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
					out.write(bytes, 0, fileSize);
					System.out.println("저장완료" + fileSize);
				} else if(protocolHeader.equals(Protocol.PROFILE_GET_PROTOCOL)) {
					File file = new File("C:/Users/hscom-018/git/Attention-Server/profiles" + "/" + fileName + ".jpeg");
					if(!file.exists()){
						dos.writeUTF(Protocol.PROFILE_GET_PROTOCOL + Protocol.SPLIT_MESSAGE + "0" + Protocol.SPLIT_MESSAGE  + "null" + Protocol.SPLIT_MESSAGE);
						continue;
					}
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int fileSize2 = (int)file.length();
					bytes = new byte[fileSize2];
					dos.writeUTF(Protocol.PROFILE_GET_PROTOCOL + Protocol.SPLIT_MESSAGE + Integer.toString(fileSize2) + Protocol.SPLIT_MESSAGE + fileName + Protocol.SPLIT_MESSAGE);
					in.readFully(bytes, 0, fileSize2);
					dos.write(bytes, 0, fileSize2);
					System.out.println("파일 보내기 완료" + fileSize2);
				} else if(protocolHeader.equals(Protocol.ROOM_FILE_PROTOCOL)) {
					
				}

				/*
				 * 맞는 IP를 찾으면 거기에 파일 소켓을 set stream을 열어줌
				 */
				/*Iterator<String> it = MessageServer.users.keySet().iterator();
				while (it.hasNext()) {
				   String val = it.next();
				   User user = MessageServer.users.get(val);

				   if (ip.equals(user.getIp())) {
				      MessageServer.users.get(val).setFileSocket(socket);
				   }
				}*/
			} catch (IOException e) {
				Debuger.printError(e);
			}

		}
	}
}