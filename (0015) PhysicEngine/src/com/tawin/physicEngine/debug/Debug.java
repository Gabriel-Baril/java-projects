package com.tawin.physicEngine.debug;

public class Debug {
	public static void alert(String message){
		System.out.println("[ALERT] : " + message);
	}
	
	public static void critical(String message){
		System.out.println("[CRITICAL] : " + message);	
	}
	
	public static void bug(String message){
		System.out.println("[BUG] : " + message);
	}
	
	public static void successful(String message){
		System.out.println("[SUCCESSFUL] : " + message);
	}
	
	public static void print(String message){
		System.out.println("[PRINT] : " + message);
	}
}
