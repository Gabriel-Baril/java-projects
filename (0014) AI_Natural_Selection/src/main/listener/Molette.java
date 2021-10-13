package main.listener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Molette implements MouseWheelListener{
	private static  Molette instance;
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	
	public static Molette getInstance(){
		if(instance == null){
			instance = new Molette();
		}
		return instance;
	}
}
