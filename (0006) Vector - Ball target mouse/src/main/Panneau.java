package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i < Main.entities.size();i++){
			g2d.fillRect((int) Main.entities.get(i).pos.getX(),(int) Main.entities.get(i).pos.getY(),(int) Main.entities.get(i).getWidth(),(int) Main.entities.get(i).getHeight());
		}
	}
}
