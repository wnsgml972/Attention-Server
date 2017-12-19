package com.hifly.attention.perform;

import java.net.Socket;
import java.util.UUID;

import com.hifly.attention.client.User;
import com.hifly.attention.dao.UserFriendsDAO;
import com.hifly.attention.dao.UserProfilesDAO;
import com.hifly.attention.debuger.Debuger;

import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;
import com.hifly.attention.values.Protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInsertPerform implements SignalPerform {
	
	private User user;

	public ProfileInsertPerform(User user) {
		this.user = user;
	}
	
	@Override
	public void performAction(SignalKey signalKey) {
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		
		String uuid = split[0];
		String profile_url = split[1];
		
		//write File
		
		//사진 저장 하는데 여러개 할 수도 있고 사진 하나만 할 수도 있는데 하나는 같은 url이므로 update 시켜줘야함
		if(UserProfilesDAO.getInstance().insertUserProfiles(uuid, profile_url) == false){
			UserProfilesDAO.getInstance().updateUserProfilesURL(uuid, profile_url);
		}
	}
}
