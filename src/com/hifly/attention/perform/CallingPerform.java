package com.hifly.attention.perform;

import java.util.HashMap;

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
		HashMap<String, User> users = MessageServer.users;
		User opponent_user = users.get(bodyData);
		
		if (user != null) {
			user.sendUTF(opponent_user.getIp());
		} else {
			user.sendUTF(null);
		}
		opponent_user.sendUTF("callToMe" + Protocol.SPLIT_MESSAGE + user.getName() + Protocol.SPLIT_MESSAGE + user.getIp());
	}
}
