package com.hifly.attention.perform;

import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.Server;
import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.userDAO.UserDAO;
import com.hifly.attention.values.Protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComeAgainPerform implements SignalPerform {
	
	private User user;
	
	public ComeAgainPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		String uuid = split[0];

		User tempUser = UserDAO.getInstance().getUser(uuid);		
		user.cloneUser(tempUser);
		
		UserDAO.getInstance().updateTime(uuid);
	}
}