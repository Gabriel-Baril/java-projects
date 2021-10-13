package main;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;

	public Fenetre(){
		build();
		setup();
		gameLoop();
	}
	
	private void build(){
		setTitle("VECTOR");
		setSize(Main.WIDTH,Main.HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(Main.panel);
	}
	
	private void setup(){
		for(int i = 0;i < 2000;i++){
			Main.entities.add(new Entity((float) (10 + Math.random() * Main.WIDTH - 10),(float) (10 + Math.random() * Main.WIDTH - 10),2,2));
		}
	}
	
	private void gameLoop(){
		while(Main.running){
			try{
				Thread.sleep(10);
				for(int i = 0;i < Main.entities.size();i++){
					Main.entities.get(i).update();
				}
				Main.panel.repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
