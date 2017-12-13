package com.hifly.attention.debuger;

public class Debuger {
	public static final boolean debuger = true;
	
	public static void printError(Exception e) {		
		e.getStackTrace();
	}
	
	public static void log(String className, String value){
		if(debuger)
			System.out.println(value = "(" + className + ")" );
	}
}