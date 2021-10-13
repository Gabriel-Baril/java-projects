package main;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Fenetre(){
		build();
		updateGame();
	}
	
	public void build(){
		this.addKeyListener(Main.kListener);
		this.setTitle("Pong Game");
		this.setSize(Main.WIDTH,Main.HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(Main.panel);
	}
	
	public void updateGame(){
		while(Main.running){
			try{
				Thread.sleep(20);
				Main.ball.update();
				Main.ball.collideEdge();
				Main.ball.collidePlayerPalet();
				Main.ball.collideIAPalet();
				Main.IAPalet.activeIA();
				Main.panel.repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
