package com.hifly.attention.values;

public interface Protocol {
	
	public static final String USER_ENROLL_PROTOCOL = "start";
	public static final String USER_EXIST_CHECK_PROTOCOL = "userExistCheck";
	public static final String USER_ENROLL_SUCCESS_PROTOCOL = "success";
	public static final String USER_ENROLL_FAIL_PROTOCOL = "fail";
	public static final String USER_FRIENDS_REQUEST_PROTOCOL = "requestFriends";
	public static final String USER_FRIENDS_RESPONSE_PROTOCOL = "responseFriends";

	public static final String ROOM_MESSAGE_PROTOCOL = "roomMessage";
	public static final String ROOM_IN_FIRST_PROTOCOL = "roomIn";
	public static final String ROOM_OUT_PROTOCOL = "roomOut";
	public static final String SPLIT_MESSAGE = "@segment@";
	
}