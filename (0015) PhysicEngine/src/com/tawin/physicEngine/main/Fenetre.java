package com.tawin.physicEngine.main;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.tawin.physicEngine.debug.Debug;
import com.tawin.physicEngine.entity.shape.Circle;
import com.tawin.physicEngine.entity.shape.Polygon2D;
import com.tawin.physicEngine.main.listener.Clavier;
import com.tawin.physicEngine.main.listener.Molette;
import com.tawin.physicEngine.main.listener.Mouse;
import com.tawin.physicEngine.main.listener.Window;
import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Util;
import com.tawin.physicEngine.toolKit.Vec2;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int   WIDTH = 1500;
	public static final int   HEIGHT = 800;
	public static boolean     running = true;
	public static final long  PREFERED_FPS = 60;
	public static long   	  DELAY = 1000/PREFERED_FPS;
	private static long       fps = 0;

	public Fenetre() {
		this.setup();
		this.build();
		this.gameLoop();
	}

	public void setup() {
		Debug.alert("START LOADING DATA...");
		ArrayList<Vec2> v = new ArrayList<>();
		v.add(new Vec2(0,HEIGHT - 60));
		v.add(new Vec2(WIDTH,HEIGHT - 60));
		v.add(new Vec2(WIDTH,HEIGHT));
		v.add(new Vec2(0,HEIGHT));
		
		Polygon2D p = new Polygon2D(v);
		p.movable = false;
		p.setColor(255, 0, 0);
		Main.engine.addEntity(p);
		
		Polygon2D pstatic1 = new Polygon2D(0,0,WIDTH,20);
		pstatic1.movable = false;
		Polygon2D pstatic2 = new Polygon2D(0,0,20,HEIGHT);
		pstatic2.movable = false;
		Polygon2D pstatic3 = new Polygon2D(WIDTH - 20,0,20,HEIGHT);
		pstatic3.movable = false;
		
		Main.engine.addEntity(pstatic1);
		Main.engine.addEntity(pstatic2);
		Main.engine.addEntity(pstatic3);
		
		for(int i = 0;i < 1;i++) {
			Main.engine.addEntity(new Circle(50,new Color(0),new Vec2(Util.rand(0,Fenetre.WIDTH),Util.rand(0,Fenetre.HEIGHT)),new Vec2(Util.rand(-10,10),Util.rand(-10,10))));
		}
		
		ArrayList<Vec2> custom = new ArrayList<>();
		custom.add(new Vec2(50,50));
		custom.add(new Vec2(130,62));
		custom.add(new Vec2(155,154));
		custom.add(new Vec2(200,50));
		custom.add(new Vec2(256,85));
		custom.add(new Vec2(300,50));
		custom.add(new Vec2(349,11));
		custom.add(new Vec2(408,42));
		custom.add(new Vec2(465,122));
		custom.add(new Vec2(509,21));
		custom.add(new Vec2(550,50));
		custom.add(new Vec2(550,100));
		custom.add(new Vec2(607,264));
		custom.add(new Vec2(476,262));
		custom.add(new Vec2(429,333));
		custom.add(new Vec2(342,199));
		custom.add(new Vec2(253,376));
		custom.add(new Vec2(296,249));
		custom.add(new Vec2(234,185));
		custom.add(new Vec2(177,273));
		custom.add(new Vec2(195,277));
		custom.add(new Vec2(50,100));
		
		Polygon2D p2 = new Polygon2D(custom);
		p2.setColor(204, 204, 0);
		
		//Main.engine.addEntity(p2);
		
		for(int i = 0;i < 100;i++){
			Polygon2D p3 = new Polygon2D(new Vec2(Util.rand(0, WIDTH),Util.rand(60, HEIGHT)),45,Util.rand(3, 6));
			//Polygon2D p3 = new Polygon2D(Util.rand(0, WIDTH),Util.rand(60, HEIGHT),Util.rand(15, 25),Util.rand(15, 25));
			p3.setColor(Util.rand(0, 255), Util.rand(0, 255), Util.rand(0, 255));
			Main.engine.addEntity(p3);
		}
		Debug.successful("DATA LOADING FINISHED");
	}

	public void build() {
		Debug.alert("START BUILDING APPLICATION...");
		this.addKeyListener(Clavier.getInstance());
		this.addMouseListener(Mouse.getInstance());
		this.addMouseMotionListener(Mouse.getInstance());
		this.addMouseWheelListener(Molette.getInstance());
		this.addWindowListener(Window.getInstance());

		this.setTitle("PhysicEngine");
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(Panneau.getInstance());
		Debug.successful("BUILDING APPLICATION FINISHED");
	}

	public void gameLoop() {
		Debug.alert("START RUNNING");
		long before;
		long after;
		long elapsed;
		while(true){
			while(Fenetre.running) {
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				before = System.currentTimeMillis();
				
				this.update();
				
				after =  System.currentTimeMillis();
				
				elapsed = after - before;
				
				//System.out.println(elapsed);
				setFPS((long)((double)1000/(elapsed + DELAY)));
				
				DELAY -= (long)Math.ceil((PREFERED_FPS - 1000/(elapsed + DELAY))/3);
				if(DELAY < 1)
					DELAY = 1;
			}
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		Main.engine.update();
		Panneau.getInstance().repaint();
	}
	
	public static void setFPS(long fps){
		Fenetre.fps = fps;
	}
	
	public static long getFPS(){
		return fps;
	}
}
