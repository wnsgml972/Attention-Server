package com.hifly.attention.perform;

import java.util.Iterator;

import com.hifly.attention.client.Room;
import com.hifly.attention.client.User;
import com.hifly.attention.debuger.Debuger;
import com.hifly.attention.serverCore.MessageServer;
import com.hifly.attention.serverCore.SignalKey;
import com.hifly.attention.serverCore.SignalPerform;

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
		String bodyData = signalKey.getBodyData();
		Debuger.log(this.toString(), bodyData);
		
		String roomUuid = bodyData;
		
		removeRooms(user.getUuid(), roomUuid);
	}
	
	/* Use Database */
	private void removeRooms(String userUuid, String roomUuid){ 
		Iterator<String> it = MessageServer.rooms.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Room room = MessageServer.rooms.get(key);
			if(roomUuid.equals(room.getRoomUuid())){
				
				String result = room.removeUser(userUuid);	//유저 제거
				
				if(result.equals("userRemove")){
					MessageServer.rooms.put(roomUuid, room);	//room 최신화
					Debuger.log("Server", "remove User success");
				}
				else if(result.equals("roomRemove")){
					MessageServer.rooms.remove(roomUuid);
					Debuger.log("Server", "remove Room success");
				}else{
					Debuger.log("Server", "remove User fail");
				}
			}
		}
	}
}
