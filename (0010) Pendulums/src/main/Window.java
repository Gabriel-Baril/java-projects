package main;

import javax.swing.JFrame;

import math.PVector;
import pendulum.DoublePendulum;
import pendulum.Pendulum;
import toolkit.Const;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	public static int fps = 0;
	
	public Window(){
		this.setup();
		this.build();
		this.gameLoop();
	}

	public void build(){
		this.setSize(Const.WIDTH,Const.HEIGHT);
		this.setTitle("PENDULUMS");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(Const.panel);
	}
	
	public void setup(){
		new DoublePendulum(new PVector(200,200),100,90,100,20);
		new Pendulum(new PVector(400,200),100,90,0.99,10);
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
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Const.panel.repaint();

			frames++;
			if(System.nanoTime() - beforeTime >= 1000000000L) {
				fps = frames;
				frames = 0;
				beforeTime = System.nanoTime();
			}
		}
	}
}
