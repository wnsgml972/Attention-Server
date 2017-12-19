package com.hifly.attention.perform;

import java.util.Iterator;

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
public class BroadcastingPerform implements SignalPerform {
	
	private User user;
	
	public BroadcastingPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String split[] = signalKey.getProtocol().split(Protocol.SPLIT_MESSAGE);
		Debuger.log(this.toString(), split[1] + "   broadcast");
		String messageVal = split[1];
		broadcast(messageVal);
	}
	
	private void broadcast(String message){
		Iterator<String> it = Server.users.keySet().iterator();
		
		while(it.hasNext()){
			User user = Server.users.get(it.next());
			user.sendUTF(message);
		}
	}
}