package main;

import java.awt.Color;
import java.util.ArrayList;

import Math.PVector;
import abstractClass.Enemies;
import abstractClass.Platforms;
import enemy.Turret;
import graphics.RenderContent;
import item.Coin;
import listener.Clavier;
import listener.Molette;
import listener.Souris;
import platform.MovingPlatform;
import platform.StaticPlatform;
import player.Player;

public class General {
	// Random number between 10 and the width : (float)(10 + Math.random() * (Constant.WIDTH - 10))
	// Random number between 10 and the height : (float)(10 + Math.random() * (Constant.HEIGHT - 10))
	public static boolean running = true;
	public static Player player = new Player(Constant.WIDTH/2,0,10,10,Color.GRAY);
	public static RenderContent game = new RenderContent();
	public static Audio audio = new Audio();
	public static Clavier keyBoard = new Clavier();
	public static Souris mouse = new Souris();
	public static Molette mouseWheel = new Molette();
	public static ArrayList<Platforms> platforms = new ArrayList<Platforms>();
	public static ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	public static ArrayList<Coin> coins = new ArrayList<Coin>();

	public static void updateGame() {
		if(player != null) {
			player.applyForce(Constant.GRAVITY);
			player.update();
			if(player.isAlive()){
				player.collideEdge();
				player.collidePlatform();
				player.collideEnemy();
				player.collideCoin();
			}
			if(player.deadAnimation != null) {
				if(player.deadAnimation.getTime() > player.deadAnimation.getDuration() && player.deadAnimation.particles.size() == 0 && !player.isAlive()) {
					player.pos.setX(Constant.WIDTH/2);
					player.pos.setY(20);
					player.vel.setY(0);
					player.setAlive(true);
					player.deadAnimation.reset();
					player.pass = false;
				}
			}
		}
		for(int i = 0;i < enemies.size();i++) {
			enemies.get(i).update();
		}
		for(int i = 0;i < platforms.size();i++) {
			platforms.get(i).update();
		}
		game.repaint();
	}

	public static void setup() {
		for(int i = 0;i < 30;i++) {
			coins.add(new Coin((float)(10 + Math.random() * (Constant.WIDTH - 10)),(float)(10 + Math.random() * (Constant.HEIGHT - 10)),5,5,1,Color.YELLOW));
		}
		enemies.add(new Turret(100,100,10,10,Color.RED));
		platforms.add(new StaticPlatform(200,200,100,10,Color.BLACK, new PVector(0,0)));
		platforms.add(new MovingPlatform(200,300,100,10,Color.BLACK, new PVector(0,0)));
		platforms.add(new StaticPlatform(0,Constant.HEIGHT - 50,Constant.WIDTH,50,Color.DARK_GRAY, new PVector(0,0)));
	}
}
