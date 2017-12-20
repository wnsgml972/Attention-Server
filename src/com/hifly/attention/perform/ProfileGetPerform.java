package com.hifly.attention.perform;

import com.hifly.attention.client.User;
import com.hifly.attention.dao.UserProfilesDAO;
import com.hifly.attention.debuger.Debuger;

import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.values.Protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileGetPerform implements SignalPerform {
	
	private User user;

	public ProfileGetPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		
		String uuid = split[0];
		String profile_url = UserProfilesDAO.getInstance().getUserProfilesURL(uuid);
		
		//read File
		
		
	}
}
