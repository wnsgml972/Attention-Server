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
public class RoomInFirstPerform implements SignalPerform {
	
	private User user;
	
	public RoomInFirstPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String split[] = signalKey.getProtocol().split(Protocol.SPLIT_MESSAGE);
		Debuger.log(this.toString(), split[1] + "  room");
		Debuger.log(this.toString(), split[2] + "  room");
		String roomUuid = split[1];
		String messageVal = split[2];
		
		Server.rooms.get(roomUuid).broadcast(messageVal);
		
	}
}
