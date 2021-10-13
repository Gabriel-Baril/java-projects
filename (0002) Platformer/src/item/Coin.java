package item;

import java.awt.Color;

import Math.PVector;

public class Coin {
	public PVector pos;
	private float width;
	private float height;
	private int value;
	private Color color;
	
	public Coin(float x,float y,float width,float height,int value,Color color) {
		this.pos = new PVector(x,y);
		this.setWidth(width);
		this.setHeight(height);
		this.setValue(value);
		this.setColor(color);
	}

	public float getWidth() {return width;}
	public float getHeight() {return height;}
	public int getValue() {return value;}
	public Color getColor() {return color;}
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
	public void setValue(int value) {this.value = value;}
	public void setColor(Color color) {this.color = color;}
}
