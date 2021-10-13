package main;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;

	public Fenetre(){
		this.build();
		this.setup();
		this.gameLoop();
	}

	private void build(){
		this.addKeyListener(Main.kListener);
		this.setTitle("Brick Breaker");
		this.setSize(Main.WIDTH,Main.HEIGHT);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(Main.panel);
	}
	
	private void setup(){
		for(int i = 0;i < Main.WIDTH/25;i++){
			for(int j = 0;j < 10;j++){
				Main.bricks.add(new Brick(25 * i,15 * j,25,15));
			}
		}
	}

	private void gameLoop(){
		while(Main.running){
			try{
				Thread.sleep(10);
				System.out.println(Main.palet.getX());
				Main.ball.update();
				Main.ball.collideEdge();
				Main.ball.collideBricks();
				Main.palet.update();
				Main.palet.collideEdge();
				Main.palet.collideBall();
				Main.panel.repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
