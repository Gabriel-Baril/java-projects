package input;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import math.Vector2;
import player.Player;
import player.Projectile;

public class Clavier implements KeyListener{
	private static Clavier instance;

	private Clavier() {}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			Player.getInstance().move(Player.getInstance().getSpeed());
			Player.getInstance().setGoRight(true);
			Player.getInstance().setGoLeft(false);
			break;
		case KeyEvent.VK_LEFT:
			Player.getInstance().move(-Player.getInstance().getSpeed());
			Player.getInstance().setGoLeft(true);
			Player.getInstance().setGoRight(false);
			break;
		case KeyEvent.VK_SPACE:
			//if(!Player.getInstance().isJumping()){
				Player.getInstance().jump();
				Player.getInstance().setJumping(true);
			//}
			break;
		case KeyEvent.VK_SHIFT:
			if(Player.getInstance().isGoRight()){
				new Projectile(Player.getInstance().pos.x + Player.getInstance().getWidth(),Player.getInstance().pos.y + Player.getInstance().getHeight()/2,5,5,Color.GREEN,new Vector2(10,0),true);
			}else{
				new Projectile(Player.getInstance().pos.x,Player.getInstance().pos.y + Player.getInstance().getHeight()/2,5,5,Color.GREEN,new Vector2(-10,0),true);
			}
			break;
		case KeyEvent.VK_K:
			Player.getInstance().kill();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			Player.getInstance().move(0);
			break;
		case KeyEvent.VK_LEFT:
			Player.getInstance().move(0);
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public static Clavier getInstance() {
		if(instance == null) {
			instance = new Clavier();
		}
		return instance;
	}
}
