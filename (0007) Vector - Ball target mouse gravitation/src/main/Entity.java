package main;

import java.awt.MouseInfo;

public class Entity {
	public Vector pos;
	public Vector vel = new Vector(0,1);
	public Vector acc = new Vector(0,0);
	private float w;
	private float h;
	
	public Entity(float x,float y,float w,float h){
		pos = new Vector(x,y);
		this.w = w;
		this.h = h;
	}
	
	public void update(){
		acc.set(MouseInfo.getPointerInfo().getLocation().x - Main.panel.getLocationOnScreen().x - this.pos.getX() - this.w/2, MouseInfo.getPointerInfo().getLocation().y - Main.panel.getLocationOnScreen().y - this.pos.getY() - this.h/2);
		acc.normalize();
		vel.scale(0.99f);
		pos.add(vel);
		vel.add(acc);
	}
	
	public void setWidth(float w){this.w = w;}
	public void setHeight(float h){this.h = h;}
	public float getWidth(){return this.w;}
	public float getHeight(){return this.h;}
}
