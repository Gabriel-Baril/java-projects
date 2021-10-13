package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import menu.GameState;
import player.Camera;
import player.Player;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;
	private static Panneau instance;
	private double scaleX;
	private double scaleY;

	private Panneau(){}

	public void paintComponent(Graphics g){
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		switch(GameState.getCurrentState()) {
		case GameState.MENU:
			for(int i = 0;i < Const.buttonMenus.size();i++) {
				Const.buttonMenus.get(i).update();
			}
			break;
		case GameState.IN_GAME:
			this.setBackground(Color.cyan);
			SET_CAMERA();
			DISPLAY_ANIMATIONS();
			DISPLAY_PROJECTILES();
			DISPLAY_PLATFORMS();
			DISPLAY_COINS();
			DISPLAY_PLAYER();
			DISPLAY_RECT_STATIC_ENEMY();
			DISPLAY_TURRETS();
			DISPLAY_PARTICLESYSTEMS();
			break;
		}
	}
	
	public void SET_CAMERA() {
		Camera.getInstance().update();
		g2d.scale(Const.WIDTH/Camera.getInstance().getWidth(),Const.HEIGHT/Camera.getInstance().getHeight());
		g2d.translate(-Camera.getInstance().pos.x,-Camera.getInstance().pos.y);
		scaleX = Const.WIDTH/Camera.getInstance().getWidth();
		scaleY = Const.HEIGHT/Camera.getInstance().getHeight();
	}
	
	public void DISPLAY_ANIMATIONS() {
		for(int i = 0;i < Const.animations.size();i++){
			Const.animations.get(i).update();
		}
	}

	public void DISPLAY_COINS(){
		for(int i =  0;i < Const.coins.size();i++){
			Const.coins.get(i).update();
		}
	}

	public void DISPLAY_PLATFORMS(){
		for(int i = 0;i < Const.platforms.size();i++){
			Const.platforms.get(i).update();
		}
	}

	public void DISPLAY_PLAYER(){
		if(Player.getInstance() != null){
			Player.getInstance().update();
		}
	}

	public void DISPLAY_RECT_STATIC_ENEMY(){
		for(int i = 0;i < Const.rectStaticEnemies.size();i++){
			Const.rectStaticEnemies.get(i).update();
		}
	}

	public void DISPLAY_PROJECTILES(){
		for(int i = 0;i < Const.projectiles.size();i++){
			Const.projectiles.get(i).update();
		}
	}

	public void DISPLAY_TURRETS(){
		for(int i = 0;i < Const.turrets.size();i++){
			Const.turrets.get(i).update();
		}
	}

	public void DISPLAY_PARTICLESYSTEMS(){
		for(int i = 0;i < Const.particleSystems.size();i++){
			Const.particleSystems.get(i).update();
		}
	}


	public static Panneau getInstance(){
		if(instance == null){
			instance = new Panneau();
		}
		return instance;
	}

	public Graphics2D getG2D(){
		return this.g2d;
	}
	
	public double getScaleX(){return scaleX;}
	public double getScaleY(){return scaleY;}
}
