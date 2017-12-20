package com.hifly.attention.perform;

import com.hifly.attention.client.User;
import com.hifly.attention.dao.UserProfilesDAO;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.MessageServer;
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
		
		
		//File Socket 세팅 + 스트림 세팅
		user.setFileSocket(MessageServer.users.get(user.getUuid()).getFileSocket());
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		String uuid = split[0];
		String profile_url = UserProfilesDAO.getInstance().getUserProfilesURL(uuid);
		String profile_name = UserProfilesDAO.getInstance().getUserProfilesName(uuid);

		//read File
		Debuger.log(this.toString(), user.toString());
		user.profileSend(uuid, profile_url, profile_name);
		Debuger.log(this.toString(), "서버에서 유저에게 프로필 보내주기");
		
	}
}
