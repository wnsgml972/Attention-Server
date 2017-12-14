package com.hifly.attention.debuger;

import com.hifly.attention.values.Protocol;

public class Debuger {
	private static final boolean debuger = true;
	
	public static void printError(Exception e) {		
		e.getStackTrace();
	}
	
	public static void log(String className, String value){
		if(debuger){
			value = value.replace(Protocol.SPLIT_MESSAGE, " ");
			System.out.println(value + "  (" + className + ")" );
		}
	}
}