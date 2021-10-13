package main.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;



public class Clavier implements KeyListener{
	private static Clavier instance;

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	

	public static Clavier getInstance(){
		if(instance == null){
			instance = new Clavier();
		}
		return instance;
	}
}
