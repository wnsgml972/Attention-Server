package com.hifly.attention.values;

public interface Protocol {

	public static final String USER_ENROLL_PROTOCOL = "USER_ENROLL_PROTOCOL";
	public static final String USER_EXIST_CHECK_PROTOCOL = "USER_EXIST_CHECK_PROTOCOL";
	public static final String USER_ENROLL_SUCCESS_PROTOCOL = "USER_ENROLL_SUCCESS_PROTOCOL";
	public static final String USER_ENROLL_FAIL_PROTOCOL = "USER_ENROLL_FAIL_PROTOCOL";
	public static final String USER_FRIENDS_REQUEST_PROTOCOL = "USER_FRIENDS_REQUEST_PROTOCOL";
	public static final String USER_FRIENDS_RESPONSE_PROTOCOL = "USER_FRIENDS_RESPONSE_PROTOCOL";

	public static final String COME_AGAIN_PROTOCOL = "COME_AGAIN_PROTOCOL";
	public static final String CHATTING_MESSAGE_PROTOCOL = "CHATTING_MESSAGE_PROTOCOL";
	public static final String BROADCAST_PROTOCOL = "BROADCAST_PROTOCOL";
	
	//
	public static final String PROFILE_INSERT_PROTOCOL = "PROFILE_INSERT_PROTOCOL";
	public static final String PROFILE_GET_PROTOCOL = "PROFILE_GET_PROTOCOL";
	
	public static final String USER_REMOVE_PROTOCOL = "USER_REMOVE_PROTOCOL";
	public static final String ROOM_REMOVE_PROTOCOL = "ROOM_REMOVE_PROTOCOL";
	public static final String ROOM_FAIL_PROTOCOL = "ROOM_FAIL_PROTOCOL";
	public static final String ROOM_FILE_PROTOCOL = "ROOM_FILE_PROTOCOL";
	//
	
	public static final String ROOM_OUT_PROTOCOL = "ROOM_OUT_PROTOCOL"; //@@
	public static final String CALLING_PROTOCOL = "CALLING_PROTOCOL"; //@@

	

	public static final String SPLIT_MESSAGE = "@SEGMENT@";

}