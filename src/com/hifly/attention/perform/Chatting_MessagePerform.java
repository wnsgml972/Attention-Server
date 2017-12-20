package com.hifly.attention.perform;

import com.hifly.attention.serverCore.MessageServer;
import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.values.Protocol;

import java.util.Vector;

import com.hifly.attention.client.Room;
import com.hifly.attention.client.User;
import com.hifly.attention.dao.RoomDAO;
import com.hifly.attention.dao.RoomUsersDAO;
import com.hifly.attention.dao.UserFriendsDAO;
import com.hifly.attention.debuger.Debuger;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chatting_MessagePerform implements SignalPerform {
	
	private User user;
	
	public Chatting_MessagePerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);

		String senderName = split[0];
		String chatContent = split[1];
		String time = split[2];
		String senderUuid = split[3];
		String roomUuid = split[4];
		
		if(MessageServer.rooms.get(roomUuid) == null) {

			Room room = new Room(roomUuid, time, senderName, chatContent, senderUuid);
			
			String[] muuid = UserFriendsDAO.getInstance().getFirstRoomUuid(roomUuid);
			
			room.addUser(muuid[0]); //안에 있는 사용자 1
			room.addUser(muuid[1]); //안에 있는 사용자 2
			
			MessageServer.rooms.put(roomUuid, room);	//방에 추가
			RoomDAO.getInstance().insertRoom(roomUuid, time, senderName, chatContent, senderUuid);
			RoomUsersDAO.getInstance().insertRoomUsers(roomUuid, muuid[0]); //안에 있는 사용자 1
			RoomUsersDAO.getInstance().insertRoomUsers(roomUuid, muuid[1]); //안에 있는 사용자 2
		}else { //add
			
		}
		
		//온 방식대로 브로드캐스팅
		MessageServer.rooms.get(roomUuid).broadcast(signalKey.getHeaderProtocol() + Protocol.SPLIT_MESSAGE + signalKey.getBodyData());
	}
}