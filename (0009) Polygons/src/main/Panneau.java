package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import toolkit.Const;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	double angle = 0.001;
	Graphics2D g2d;

	public void paintComponent(Graphics g){
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		DISPLAY_FPS(1,10);
		DISPLAY_POLYGON();
	}
	
	public void DISPLAY_FPS(int x,int y) {
		g2d.drawString("FPS : " + Fenetre.fps, 1, 10);
	}
	
	public void DISPLAY_POLYGON() {
		for(int i = 0;i < Const.polygons.size();i++){
			Const.polygons.get(i).drawSATInf(g2d);
			Const.polygons.get(i).fillPolygon(g2d);
			Const.polygons.get(i).drawPointInf(g2d);
			Const.polygons.get(i).drawSegmentInf(g2d);
			Const.polygons.get(i).drawSegmentBrcInf(g2d);
			Const.polygons.get(i).drawGeneralInf(g2d);
			Const.polygons.get(i).update();
		}
	}
}
