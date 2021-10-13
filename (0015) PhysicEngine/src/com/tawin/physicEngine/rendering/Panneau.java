package com.tawin.physicEngine.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.tawin.physicEngine.entity.Segment;
import com.tawin.physicEngine.entity.shape.Polygon2D;
import com.tawin.physicEngine.main.Fenetre;
import com.tawin.physicEngine.main.Main;
import com.tawin.physicEngine.toolKit.Collider2D;
import com.tawin.physicEngine.toolKit.Vec2;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Panneau instance;
	private Graphics2D g2d;
	
	Table table = new Table(new Vec2(0,0),new Dimension(30,20),20,10);
	
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		ArrayList<Polygon2D> polygons = Main.engine.getPolygons();
		g2d.drawString("" + polygons.size(), 0, 10);
		g2d.drawString("" + Fenetre.getFPS(), 80, 20);
		Main.engine.render();
		
		//table.renderTab();
		Main.joint.render();
		//Main.circle.render();
	}
	
	public static Panneau getInstance() {
		if(Panneau.instance == null)
			Panneau.instance = new Panneau();
		return Panneau.instance;
	}
	
	public Graphics2D getG2D() {
		return this.g2d;
	}
}
