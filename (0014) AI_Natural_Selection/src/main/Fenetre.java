package main;

import java.awt.Color;

import javax.swing.JFrame;

import entity.active.Mob;
import entity.passive.Food;
import main.listener.Clavier;
import main.listener.Molette;
import main.listener.Souris;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	long ClearLag = System.currentTimeMillis();

	public Fenetre(){
		this.setup();
		this.build();
		this.gameLoop();
	}

	private void setup(){
		for(int i = 0;i < 1;i++){
			new Mob(Const.rand(0, Const.WIDTH),Const.rand(0, Const.HEIGHT),20,10);
		}
	}

	private void build(){
		this.addKeyListener(Clavier.getInstance());
		this.addMouseListener(Souris.getInstance());
		this.addMouseWheelListener(Molette.getInstance());
		this.addMouseMotionListener(Souris.getInstance());

		this.setSize(Const.WIDTH,Const.HEIGHT);
		this.setTitle(Const.TITLE);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setContentPane(Panneau.getInstance());
	}

	private void gameLoop(){
		while(Const.running){
			try {
				Thread.sleep(Const.DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Const.updateGame){
				this.update();
			}
		}

	}

	private void update(){
		if(Const.mobs.size() == 0){
			new Mob(Const.rand(0, Const.WIDTH),Const.rand(0, Const.HEIGHT),20,10);
		}
		Panneau.getInstance().repaint();
		if(System.nanoTime() % 1 == 0){
			new Food(Const.rand(0, Const.WIDTH),Const.rand(0, Const.HEIGHT),5,Color.RED);
		}
	}
}
