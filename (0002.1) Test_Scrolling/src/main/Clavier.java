package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			Main.player.velX = 1;
		}
		if(e.getKeyCode() == 37) {
			Main.player.velX = -1;
		}
		if(e.getKeyCode() == 40) {
			Main.player.velY = 1;
		}
		if(e.getKeyCode() == 38) {
			Main.player.velY = -1;
		}
		
		if(e.getKeyCode() == 39 && Main.player.blocked) {
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velX = -1;
			}
		}
		if(e.getKeyCode() == 37 && Main.player.blocked) {
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velX = 1;
			}
		}
		if(e.getKeyCode() == 40 && Main.player.blocked) {
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velY = -1;
			}
		}
		if(e.getKeyCode() == 38 && Main.player.blocked) {
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velY = 1;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 39 || e.getKeyCode() == 37) {
			Main.player.velX = 0;
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velX = 0;
			}
		}
		if(e.getKeyCode() == 40 || e.getKeyCode() == 38) {
			Main.player.velY = 0;
			for(int i = 0;i < Main.platforms.size();i++){
				Main.platforms.get(i).velY = 0;
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
}
