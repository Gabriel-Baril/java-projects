package com.tawin.main;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
			if(!Main.viewTrajectory){
				super.paintComponent(g);
			}
			g.setColor(Color.red);
			for(int i = 0;i < Main.rects.size();i++){
				g.fillRect(Main.rects.get(i).getX(), Main.rects.get(i).getY(), Main.rects.get(i).getWidth(), Main.rects.get(i).getHeight());
		}
	}
}