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
public class UserFriendsRequestPerform implements SignalPerform {
	
	private User user;
	
	public UserFriendsRequestPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String split[] = signalKey.getProtocol().split(Protocol.SPLIT_MESSAGE);
		StringBuilder sb = new StringBuilder("");
		sb.append(Protocol.USER_FRIENDS_RESPONSE_PROTOCOL + Protocol.SPLIT_MESSAGE);
		Debuger.log(toString(), Integer.toString(Server.users.size()));				
		for(int i=1; i<split.length; i++) {
			Iterator<String> it = Server.users.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				User muser = Server.users.get(key);
				if(muser.getTel().equals(split[i]) && !user.getTel().equals(split[i])) {
					sb.append(muser.getUuid() + Protocol.SPLIT_MESSAGE + muser.getName() + Protocol.SPLIT_MESSAGE + muser.getTel() + Protocol.SPLIT_MESSAGE + muser.getStateMessage() + Protocol.SPLIT_MESSAGE);
				}
			}
		}
		
		Debuger.log(this.toString(), "리스펀스 메시지   " + sb.toString());
		user.sendUTF(sb.toString());
	}
}
