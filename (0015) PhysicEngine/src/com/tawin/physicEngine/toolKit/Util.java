package com.tawin.physicEngine.toolKit;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.tawin.physicEngine.rendering.Panneau;	

public class Util {
	public static final double GRAVITY = 0.1;
	
	public static Dimension getRenderedStringDimensions(Font font,String str){
		Graphics2D g2d = Panneau.getInstance().getG2D();
		FontMetrics metrics = g2d.getFontMetrics(font);
		
		return new Dimension(metrics.getHeight()+2,metrics.stringWidth(str)+2);
	}
	
	public static boolean between(double min,double max,double value){
		if(value >= min && value <= max)
			return true;
		return false;
	}
	
	public static double dist(Vec2 p1,Vec2 p2) {
		return Math.sqrt((p2.getX() - p1.getX())*(p2.getX() - p1.getX()) + (p2.getY() - p1.getY())*(p2.getY() - p1.getY()));
	}
	
	public static int rand(int min,int max){
		return min + (int)(Math.random()*max);
	}
}
