package main.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.active.Mob;
import main.Const;

public class Clavier implements KeyListener{
	private static Clavier instance;
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_1: Mob.SHOW_ZONE = (Mob.SHOW_ZONE == true) ? false : true; break;
			case KeyEvent.VK_2: Mob.SHOW_FOOD_SEG = (Mob.SHOW_FOOD_SEG == true) ? false : true; break;
			case KeyEvent.VK_3: Const.foods.clear(); break;
			case KeyEvent.VK_4: Const.updateGame = (Const.updateGame == true) ? false : true; break;
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
