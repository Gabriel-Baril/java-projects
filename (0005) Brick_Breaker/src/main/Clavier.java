package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 37){
			Main.palet.setVelX(-3);
		}
		if(e.getKeyCode() == 39){
			Main.palet.setVelX(3);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 37 || e.getKeyCode() == 39){
			Main.palet.setVelX(0);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

}
