package main;

import java.util.ArrayList;

import consummable.Coin;
import enemy.RectStaticEnemy;
import enemy.Turret;
import graphic.Animation;
import graphic.Sprite;
import menu.Bouton;
import platform.RectPlatform;
import player.ParticleSystem;
import player.Projectile;

public class Const {
	public static ArrayList<RectPlatform> platforms = new ArrayList<RectPlatform>();
	public static ArrayList<Coin> coins = new ArrayList<Coin>();
	public static ArrayList<RectStaticEnemy> rectStaticEnemies = new ArrayList<RectStaticEnemy>();
	public static ArrayList<Turret> turrets = new ArrayList<Turret>();
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public static ArrayList<Bouton> buttonMenus = new ArrayList<Bouton>();
	
	public static ArrayList<ParticleSystem> particleSystems = new ArrayList<ParticleSystem>();
	
	public static ArrayList<Animation> animations = new ArrayList<Animation>();
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public static final int UP_MARGIN = 35       ;
	public static final int LEFT_MARGIN = 15;
	
	public static final int TILE_SIZE = 16;
	
	public static int updateTime = 15;
	public static boolean running = true;
	public static int WIDTH = 900;
	public static int HEIGHT = 900;
}
