package main;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;

	public Fenetre(){
		this.build();
		this.setup();
		this.gameLoop(25);
	}
	
	private void build(){
		setTitle("Circle physics simulation");
		setSize(Main.WIDTH,Main.HEIGHT);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(Main.panel);
	}
	
	private void setup(){
		for(int i = 0;i < 2000;i++){
			Main.balls.add(new Ball((float)(Math.random() * Main.WIDTH),(float)(Math.random() * Main.HEIGHT),10));
		}
	}
	
	private void gameLoop(int time){
		while(Main.running){
			try{
				Thread.sleep(time);
				
				for(int i = 0;i < Main.balls.size();i++){
					Main.balls.get(i).update();
					Main.balls.get(i).collideEdge();
					Main.balls.get(i).collideEachOther();
					Main.balls.get(i).applyForce(Main.GRAVITY);
				}
				Main.panel.repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
