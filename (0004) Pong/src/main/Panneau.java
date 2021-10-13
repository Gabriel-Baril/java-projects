package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		Main.panel.setBackground(Color.BLACK);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int) Main.playerPalet.getX(),(int) Main.playerPalet.getY(),(int) Main.playerPalet.getWidth(),(int) Main.playerPalet.getHeight());
		g2d.fillRect((int) Main.IAPalet.getX(),(int) Main.IAPalet.getY(),(int) Main.IAPalet.getWidth(),(int) Main.IAPalet.getHeight());
		g2d.fillRect((int) Main.ball.getX(),(int) Main.ball.getY(),(int) Main.ball.getWidth(),(int) Main.ball.getHeight());
	}
}
