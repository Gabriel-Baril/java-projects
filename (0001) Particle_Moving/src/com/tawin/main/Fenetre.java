package com.tawin.main;
import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();

	public Fenetre(){        
		this.setTitle("Animation");
		this.setSize(Main.WIDTH, Main.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
		this.setResizable(false);
		update();
	}

	private void update(){
		while(true){
			for(int i = 0;i < Main.rects.size();i++){
				if(Main.rects.get(i).getX() + Main.rects.get(i).getWidth() + 12> Main.WIDTH || Main.rects.get(i).getX() < 0){
					Main.rects.get(i).setDx(-Main.rects.get(i).getDx());
				}
				if(Main.rects.get(i).getY() + Main.rects.get(i).getHeight() + 30 > Main.HEIGHT || Main.rects.get(i).getY() < 0){
					Main.rects.get(i).setDy(-Main.rects.get(i).getDy());
				}
				Main.rects.get(i).setX(Main.rects.get(i).getX() + Main.rects.get(i).getDx());
				Main.rects.get(i).setY(Main.rects.get(i).getY() + Main.rects.get(i).getDy());
				//if(Main.rects.get(i).getX() + Main.rects.get(i).getWidth() > Main.WIDTH - 10 || Main.rects.get(i).getY() + Main.rects.get(i).getHeight() > Main.HEIGHT - 10){
				//	Main.rects.remove(i);
				//}
				
			}
			pan.repaint();
			//System.out.println(Main.rects.size());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}   
}