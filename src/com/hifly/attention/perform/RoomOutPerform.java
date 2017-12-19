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
public class RoomOutPerform implements SignalPerform {
	
	private User user;
	
	public RoomOutPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String split[] = signalKey.getProtocol().split(Protocol.SPLIT_MESSAGE);
		Debuger.log(this.toString(), split[1] + "  roomOut");
		String roomUuid = split[1];
		
		Server.removeRooms(user.getUuid(), roomUuid);
	}
}
