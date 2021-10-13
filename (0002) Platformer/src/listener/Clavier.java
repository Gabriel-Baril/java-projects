package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.General;

public class Clavier implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if(General.player != null) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				General.player.moveRight();
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				General.player.moveLeft();
			}
			if(e.getKeyCode() == KeyEvent.VK_K){
				General.player.setAlive(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				General.player.jump();
				if(General.player.isAlive()){
					General.audio.playSound("data/jump-03.wav",-40);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(General.player != null) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				General.player.vel.setX(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				General.player.vel.setX(0);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
