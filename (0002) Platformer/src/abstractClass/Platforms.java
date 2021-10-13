package abstractClass;

import java.awt.Color;

import Math.PVector;
import main.General;

public abstract class Platforms {
	public PVector pos;
	public PVector vel = new PVector(0,0);
	public PVector acc = new PVector(0,0);
	private float width;
	private float height;
	private Color color;
	private PVector p;
	
	public Platforms(float x,float y,float width,float height, Color color,PVector p) {
		pos = new PVector(x,y);
		this.width = width;
		this.height = height;
		this.setColor(color);
		this.p = p;
	}
	
	public abstract void update();
	
	public void addAttribute() {
		General.player.vel.add(this.p);
	}
	
	public void stopAttribute() {
		General.player.vel.sub(this.p);
	}
	
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
	public void setColor(Color color) {this.color = color;}
	public float getWidth() {return this.width;}
	public float getHeight() {return this.height;}
	public Color getColor() {return color;}
}
