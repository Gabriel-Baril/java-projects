package com.tawin.physicEngine.main.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.tawin.physicEngine.debug.Debug;

public class Window implements WindowListener{
	private static Window instance;

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
		
	}
	
	public void windowClosing(WindowEvent e) {
		Debug.successful("WINDOW CLOSED");
	}
	
	public void windowDeactivated(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		
	}
	
	public static Window getInstance(){
		if(instance == null){
			instance = new Window();
		}
		return instance;
	}

}
