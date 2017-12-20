package com.hifly.attention.perform;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.MessageServer;
import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.values.Protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallingPerform implements SignalPerform {
	
	private User user;
	
	public CallingPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		String opponent_uuid = split[0];
		User opponent_user = MessageServer.users.get(opponent_uuid);
		
		if (user != null) {
			user.sendUTF(Protocol.CALLING_PROTOCOL + Protocol.SPLIT_MESSAGE + opponent_user.getIp() + Protocol.SPLIT_MESSAGE);
		} else {
			Debuger.log(toString(), "Why null???");
			user.sendUTF(null);
		}
		
		opponent_user.sendUTF(Protocol.CALLING_PROTOCOL + Protocol.SPLIT_MESSAGE
				+ user.getIp() + Protocol.SPLIT_MESSAGE + user.getName() + Protocol.SPLIT_MESSAGE);
	}
}
