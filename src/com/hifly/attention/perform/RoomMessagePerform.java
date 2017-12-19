package com.hifly.attention.perform;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.Server;
import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.values.Protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomMessagePerform implements SignalPerform {
	
	private User user;
	
	public RoomMessagePerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		Debuger.log(this.toString(), split[0] + "  room");
		Debuger.log(this.toString(), split[1] + "  room");
		String roomUuid = split[0];
		String messageVal = split[1];
		
		Server.rooms.get(roomUuid).broadcast(messageVal);
	}
}
