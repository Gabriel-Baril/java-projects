package main;

import java.awt.Color;

public class Brick {
	private float x;
	private float y;
	private int w;
	private int h;
	private float velX = 0;
	private Color c = new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255));
	
	public Brick(float x,float y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public void setWidth(int w){this.w = w;}
	public void setHeight(int h){this.h = h;}
	public void setVelX(float velX){this.velX = velX;};
	public void setC(Color c) {this.c = c;}
	public float getX(){return this.x;}
	public float getY(){return this.y;}
	public int getWidth(){return this.w;}
	public int getHeight(){return this.h;}
	public float getVelX(){return this.velX;}
	public Color getC() {return c;}
}
