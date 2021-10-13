package main;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static boolean running = true;
	
	public Fenetre() {
		this.setup();
		this.build();
		this.mainLoop();
	}
	
	private void setup() {
	}
	
	private void build() {
		this.setTitle("INVERSE KINEMATIC");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setContentPane(Panneau.getInstance());
	}
	
	private void mainLoop() {
		while(true) {
			gameLoop();
		}
	}
	
	private void gameLoop() {
		while(Fenetre.running) {
			try {
				Thread.sleep(15);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			this.update();
		}
	}
	
	private void update() {
		Panneau.getInstance().repaint();
	}
}
