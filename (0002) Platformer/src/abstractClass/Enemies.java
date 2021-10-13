package abstractClass;

import java.awt.Color;

import Math.PVector;

public abstract class Enemies{
	public PVector pos;
	public PVector vel = new PVector(0,0);
	public PVector acc = new PVector(0,0);
	private float width;
	private float height;
	private Color color;
	
	public Enemies(float x,float y,float width,float height, Color color) {
		this.pos = new PVector(x,y);
		this.width = width;
		this.height = height;
		this.setColor(color);
	}
	
	public abstract void update();

	public float getWidth() {return width;}
	public float getHeight() {return height;}
	public Color getColor() {return color;}
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
	public void setColor(Color color) {this.color = color;}
}
