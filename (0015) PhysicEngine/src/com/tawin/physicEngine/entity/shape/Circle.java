package com.tawin.physicEngine.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;

import com.tawin.physicEngine.behavior.Rendable;
import com.tawin.physicEngine.entity.Entity;
import com.tawin.physicEngine.main.Fenetre;
import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Util;
import com.tawin.physicEngine.toolKit.Vec2;

public class Circle implements Entity {
	private Vec2 pos = new Vec2(0,0);
	private Vec2 vel = new Vec2(0,0);
	private Vec2 acc = new Vec2(0,0);
	private double radius;
	private Color color;
	
	public Circle(double radius,Color color,Vec2 pos,Vec2 vel,Vec2 acc) {
		this.radius = radius;
		this.color = color;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
	}

	public Circle(double radius,Color color,Vec2 pos,Vec2 vel) {
		this.radius = radius;
		this.color = color;
		this.pos = pos;
		this.vel = vel;
	}

	public Circle(double radius,Color color,Vec2 pos) {
		this.radius = radius;
		this.color = color;
		this.pos = pos;
	}

	@Override
	public void render() {
		Graphics2D g2d = Panneau.getInstance().getG2D();
		g2d.setColor(this.color);
		g2d.drawOval((int)(this.pos.x - radius), (int)(this.pos.y - radius), (int)this.radius*2, (int)this.radius*2);
	}
	
	@Override
	public void update() {
		this.applyForce(new Vec2(0,Util.GRAVITY));
		this.collide_edge();
		
		this.pos.add(this.vel);
		this.vel.add(this.acc);
		this.acc.scale(0);
	}
	
	public void collide_edge() {
		if(pos.x - radius < 0) {
			this.pos.x = radius;
			this.vel.x *= -1;
			this.vel.scale(0.9);
		}
		if(pos.x + radius >= Fenetre.WIDTH) {
			this.pos.x = Fenetre.WIDTH - radius;
			this.vel.x *= -1;
			this.vel.scale(0.9);			
		}
		if(pos.y + radius >= Fenetre.HEIGHT) {
			this.pos.y = Fenetre.HEIGHT - radius;
			this.vel.y *= -1;
			this.vel.scale(0.9);
		}
	}
	
	public void applyForce(Vec2 force) {
		this.acc.add(force);
	}
	
	public Vec2 getPos() {return pos;}
	public void setPos(Vec2 pos) {this.pos = pos;}
	public Vec2 getVel() {return vel;}
	public void setVel(Vec2 vel) {this.vel = vel;}
	public Vec2 getAcc() {return acc;}
	public void setAcc(Vec2 acc) {this.acc = acc;}
	public double getRadius() {return radius;}
	public void setRadius(double radius) {this.radius = radius;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
}
