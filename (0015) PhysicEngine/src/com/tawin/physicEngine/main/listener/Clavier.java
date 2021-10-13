package com.tawin.physicEngine.main.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.tawin.physicEngine.main.Fenetre;



public class Clavier implements KeyListener{
	private static Clavier instance;

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Fenetre.running = (Fenetre.running == true)?false:true;
			System.out.println(Fenetre.running);
		}
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
