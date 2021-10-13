package main;

import java.awt.Color;

import javax.swing.JFrame;

import toolkit.PVector;

import geometry.Polygon;
import geometry.Vertex;
import infWindow.InfFenetre;
import toolkit.Const;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public static int fps = 0;

	public Fenetre(){
		this.setup();
		this.build();
		this.gameLoop();
	}

	public void build(){
		Main.iFen = new InfFenetre();
		this.addKeyListener(Const.kListener);
		this.addMouseListener(Const.mListener);
		this.addMouseWheelListener(Const.mwListener);
		this.addMouseMotionListener(Const.mListener);
		this.setSize(Const.WIDTH,Const.HEIGHT);
		this.setTitle("POLYGON");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(Const.panel);
	}

	public void setup(){
		Polygon plg = new Polygon(new Color(0,255,0),"Polygon_1","O");
		plg.setFocused(true);
		plg.add(new Vertex(20,20,"A"));
		plg.add(new Vertex(60,20,"B"));
		plg.add(new Vertex(120,20,"C"));
		plg.add(new Vertex(60,80,"D"));
		plg.add(new Vertex(100,100,"E"));
		plg.add(new Vertex(230,200,"F"));
		
		Polygon plg2 = new Polygon(new Color(0,255,0),"Polygon_2","O");
		plg2.add(new Vertex(200,200,"A"));
		plg2.add(new Vertex(300,200,"B"));
		plg2.add(new Vertex(250,250,"C"));

		Polygon plg3 = new Polygon(new Color(255,255,0),"Polygon_3","O");
		plg3.add(new Vertex(500,500,"A"));
		plg3.add(new Vertex(600,600,"B"));
		plg3.add(new Vertex(350,350,"C"));
		plg3.add(new Vertex(600,400,"D"));
	}

	public void gameLoop(){
		while(true){
			update();
		}
	}

	public void update(){
		long beforeTime = System.nanoTime(); 
		int frames = 0;
		while(Const.running){

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Const.panel.repaint();
			if(Main.iFen != null) {
				Main.iFen.panel.repaint();
			}

			for(int i = 0;i < Const.polygons.size();i++){
				for(int j = 0;j < Const.polygons.size();j++){
					if(i != j){
						Polygon.COLLISION_POLYGON(Const.polygons.get(i), Const.polygons.get(j));
					}
				}
			}

			frames++;
			if(System.nanoTime() - beforeTime >= 1000000000L) {
				fps = frames;
				frames = 0;
				beforeTime = System.nanoTime();
			}
		}
	}
}
