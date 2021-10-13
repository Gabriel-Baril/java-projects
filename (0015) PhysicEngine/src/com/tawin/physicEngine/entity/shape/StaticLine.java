package com.tawin.physicEngine.entity.shape;

import java.awt.Graphics2D;

import com.tawin.physicEngine.entity.Entity;
import com.tawin.physicEngine.entity.Segment;
import com.tawin.physicEngine.rendering.Panneau;

public class StaticLine implements Entity{
	Segment segment;
	double thickness;
	
	
	public StaticLine(Segment segment,double thickness) {
		this.segment = segment;
		this.thickness = thickness;
	}

	public void update() {
		
	}

	public void render() {
		Graphics2D g2d = Panneau.getInstance().getG2D();
		g2d.drawLine((int)segment.getStart().x, (int)segment.getStart().y, (int)segment.getEnd().x, (int)segment.getEnd().y);
	}
}
