package main;

import java.awt.Color;

public class Star {
	public Vector pos;
	public Vector vel = new Vector(1,1);
	public Vector acc = new Vector(0,0);
	private float mass;
	private float radius;
	private Color color;
	
	public Star(float x,float y,float mass,float radius,Color color) {
		this.pos = new Vector(x,y);
		this.setMass(mass);
		this.setRadius(radius);
		this.setColor(color);
	}
	
	public void update() {
		this.acc.normalize();
		this.vel.scale(0.99f);
		this.pos.add(vel);
		this.vel.add(acc);
	}

	public void applyForce(Vector force) {
		this.acc.add(force);
	}

	public float getMass() {return mass;}
	public float getRadius() {return radius;}
	public Color getColor() {return color;}
	public void setMass(float mass) {this.mass = mass;}
	public void setRadius(float radius) {this.radius = radius;}
	public void setColor(Color color) {this.color = color;}
}
