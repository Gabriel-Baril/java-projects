package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i < Main.planets.size();i++) {
			g2d.setColor(Main.planets.get(i).getColor());
			g2d.fillOval((int) Main.planets.get(i).pos.getX() - (int) Main.planets.get(i).getRadius(),(int) Main.planets.get(i).pos.getY() - (int) Main.planets.get(i).getRadius(),(int) Main.planets.get(i).getRadius(),(int) Main.planets.get(i).getRadius());
		}
		for(int i = 0;i < Main.stars.size();i++) {
			g2d.fillOval((int) Main.stars.get(i).pos.getX() - (int) Main.stars.get(i).getRadius(),(int) Main.stars.get(i).pos.getY() - (int) Main.stars.get(i).getRadius(),(int) Main.stars.get(i).getRadius()*2,(int) Main.stars.get(i).getRadius()*2);
		}
	}
}
