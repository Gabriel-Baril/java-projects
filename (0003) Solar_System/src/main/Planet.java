package main;

import java.awt.Color;

public class Planet {
	public Vector pos;
	public Vector vel = new Vector(2,1);
	public Vector acc = new Vector(0,0);
	private float mass;
	private float radius;
	private Color color;
	private Star attractor;
	
	public Planet(float x,float y,float mass,float radius,Color color) {
		this.pos = new Vector(x,y);
		this.setMass(mass);
		this.setRadius(radius);
		this.setColor(color);
	}
	
	public void update() {
		if(this.attractor != null) {
			this.acc.set(getAttractor().pos.getX() - this.pos.getX(),getAttractor().pos.getY() - this.pos.getY());
		}
		this.acc.normalize();
		this.vel.scale(0.99f);
		this.pos.add(vel);
		this.vel.add(acc);
	}
	
	public void collideEachOther(){
		for(int i = 0;i < Main.planets.size();i++){
			if(Main.planets.get(i) != this){
				if(Math.sqrt(((this.pos.getX() - this.getRadius()/2) - (Main.planets.get(i).pos.getX() - Main.planets.get(i).getRadius()/2)) * ((this.pos.getX() - this.getRadius()/2) - (Main.planets.get(i).pos.getX() - Main.planets.get(i).getRadius()/2)) + ((this.pos.getY() - this.getRadius()/2) - (Main.planets.get(i).pos.getY() - Main.planets.get(i).getRadius()/2)) * ((this.pos.getY() - this.getRadius()/2) - (Main.planets.get(i).pos.getY() - Main.planets.get(i).getRadius()/2))) < this.getRadius()/2 + Main.planets.get(i).getRadius()/2){
					//System.out.println(Main.balls.get(i) + " - " + Main.balls.get(j));
					this.vel.set(this.pos.getX() - Main.planets.get(i).pos.getX(), this.pos.getY() - Main.planets.get(i).pos.getY());
					//this.vel.normalize();
					//break;
				}
			}
		}
	}

	public void applyForce(Vector force) {
		this.acc.add(force);
	}
	
	public Star getAttractor() {return this.attractor;}
	public void setAttractor(Star att) {this.attractor = att;}

	public float getMass() {return mass;}
	public float getRadius() {return radius;}
	public Color getColor() {return color;}
	public void setMass(float mass) {this.mass = mass;}
	public void setRadius(float radius) {this.radius = radius;}
	public void setColor(Color color) {this.color = color;}
}
