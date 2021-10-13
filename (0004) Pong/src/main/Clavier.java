package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 38){
			Main.playerPalet.setY(Main.playerPalet.getY() - Main.playerPalet.getSpeed());
		}
		if(e.getKeyCode() == 40){
			Main.playerPalet.setY(Main.playerPalet.getY() + Main.playerPalet.getSpeed());
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
