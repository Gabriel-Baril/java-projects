package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private boolean passed = false;
	private Color c;
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		Main.panel.setBackground(Color.BLACK);
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int) Main.palet.getX(),(int) Main.palet.getY(),(int) Main.palet.getWidth(),(int) Main.palet.getHeight());
		g2d.fillRect((int) Main.ball.getX(), (int) Main.ball.getY(), (int) Main.ball.getWidth(), (int) Main.ball.getHeight());
		for(int i = 0;i < Main.bricks.size();i++){
			g2d.setColor(Main.bricks.get(i).getC());
			g2d.fillRect((int) Main.bricks.get(i).getX(),(int) Main.bricks.get(i).getY(), (int) Main.bricks.get(i).getWidth(), (int) Main.bricks.get(i).getHeight());
		}
	}
}
