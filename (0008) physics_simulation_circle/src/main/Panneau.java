package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i < Main.balls.size();i++){
			g2d.fillOval((int) Main.balls.get(i).pos.getX(),(int) Main.balls.get(i).pos.getY(),(int) Main.balls.get(i).getRadius(),(int) Main.balls.get(i).getRadius());
		}
	}
}
