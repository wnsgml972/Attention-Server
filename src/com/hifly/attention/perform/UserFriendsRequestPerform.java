package com.hifly.attention.perform;

import java.util.Iterator;
import java.util.UUID;

import com.hifly.attention.client.User;
import com.hifly.attention.dao.UserDAO;
import com.hifly.attention.dao.UserFriendsDAO;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.MessageServer;
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
		
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String split[] = bodyData.split(Protocol.SPLIT_MESSAGE);
		StringBuilder sb = new StringBuilder("");
		sb.append(Protocol.USER_FRIENDS_RESPONSE_PROTOCOL + Protocol.SPLIT_MESSAGE); //Make header
		Debuger.log(toString(), "친구 수 : " + Integer.toString(MessageServer.users.size()));
		
		for(int i=0; i<split.length; i++) {
			Iterator<String> it = MessageServer.users.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				User muser = MessageServer.users.get(key);

				if(muser.getTel().equals(split[i]) && !user.getTel().equals(split[i])) { //내가 아니구고 친구라면	
					
					String userUuid = user.getUuid();					
					String mUuid = muser.getUuid();
					String mName = muser.getName();
					String mTel = muser.getTel();
					String mStateMessage = muser.getStateMessage();
					String p2pChatUuid = UserFriendsDAO.getInstance().getP2PChatUuid(userUuid, mUuid);
					
					if(p2pChatUuid == null){
						p2pChatUuid = UUID.randomUUID().toString().replace("-", "");
						// 양쪽 다 업데이트
						UserFriendsDAO.getInstance().insertP2PChatUuid(userUuid, mUuid);
					}
					
					sb.append(userUuid + Protocol.SPLIT_MESSAGE + mName + Protocol.SPLIT_MESSAGE + mTel
					+ Protocol.SPLIT_MESSAGE + mStateMessage + Protocol.SPLIT_MESSAGE 
					+ p2pChatUuid + Protocol.SPLIT_MESSAGE);
					
					UserFriendsDAO.getInstance().insertUserFriends(userUuid, mUuid);
				}
			}
		}
		
		Debuger.log(this.toString(), "리스펀스 메시지   " + sb.toString());
		user.sendUTF(sb.toString());
	}
}