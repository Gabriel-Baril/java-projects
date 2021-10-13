package main;

import java.awt.Color;

import javax.swing.JFrame;

import consummable.Coin;
import enemy.Turret;
import graphic.Animation;
import graphic.Sprite;
import input.Clavier;
import input.FenetreListener;
import input.Molette;
import input.Souris;
import menu.GameState;
import platform.RectPlatform;


public final class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	private static int fps = 0;
	private static volatile Fenetre instance = null;

	private Fenetre(){
		this.load();
		this.build();
		this.gameLoop();
	}

	private void build(){
		this.setTitle("Platformer v_1.4");
		this.addKeyListener(Clavier.getInstance());
		this.addMouseListener(Souris.getInstance());
		this.addMouseMotionListener(Souris.getInstance());
		this.addMouseWheelListener(Molette.getInstance());
		this.addWindowListener(FenetreListener.getInstance());
		this.setSize(Const.WIDTH,Const.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
		this.setAlwaysOnTop(false);
		this.setContentPane(Panneau.getInstance());
	}

	private void load(){
		System.out.println("Loading...");
		long t = System.currentTimeMillis();
		GameState.setCurrentState(GameState.IN_GAME);

		for(int i = 0;i < 100;i++){
			new Coin(Math.random() * Const.WIDTH,Math.random() * Const.HEIGHT,10,10,Color.ORANGE);
		}
		Sprite[] s = {
				new Sprite("gfx/platform/top_left_ground_extended.png"),
				new Sprite("gfx/platform/top_ground.png"),
				new Sprite("gfx/platform/top_right_ground_extended.png"),
				new Sprite("gfx/platform/left_ground.png"),
				new Sprite("gfx/platform/right_ground.png"),
				new Sprite("gfx/platform/bottom_left_ground_extended.png"),
				new Sprite("gfx/platform/bottom_ground.png"),
				new Sprite("gfx/platform/bottom_right_ground_extended.png"),
				new Sprite("gfx/platform/center_ground.png"),
		};
		String root = "gfx/player";
		String[] wLeft = {"wLeft_01","",""};
		
		Animation wLeft_a = new Animation("gfx/player",wLeft,10);
		
		Animation[] a = {wLeft_a};
		
		new Turret(10,10,10,10,new Color(0));
		
		new RectPlatform(450, 500, 5 ,10,s);
		new RectPlatform(0, Const.HEIGHT - Const.TILE_SIZE*3, 15, 3,s);
		new RectPlatform(Const.TILE_SIZE*15, Const.HEIGHT - Const.TILE_SIZE*5, 15, 5,s);
		new RectPlatform(Const.TILE_SIZE*30, Const.HEIGHT - Const.TILE_SIZE*4, 15, 4,s);
		new RectPlatform(Const.TILE_SIZE*45, Const.HEIGHT - Const.TILE_SIZE*10, 12, 10,s);

		System.out.println("Loaded in " + (System.currentTimeMillis() - t) + " milliseconds");
	}   

	private void gameLoop(){
		while(true){
			update();
		}
	}

	private void update(){
		long beforeTime = System.nanoTime(); 
		int frames = 0;
		while(Const.running){
			try{
				Thread.sleep(Const.updateTime);
			}catch(InterruptedException e){e.printStackTrace();}

			contentGame();

			frames++;
			if(System.nanoTime() - beforeTime >= 1000000000L) {
				fps = frames;
				frames = 0;
				beforeTime = System.nanoTime();
			}
		}
	}

	private void contentGame(){
		Panneau.getInstance().repaint();
	}


	public static void create() {
		if(Fenetre.instance == null){
			Fenetre.instance = new Fenetre();
		}
	}


	public static Fenetre getInstance(){
		return Fenetre.instance;
	}

	public static int getFPS(){return fps;}
}
