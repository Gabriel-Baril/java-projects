package player;

import java.awt.Color;

import Math.PVector;
import comportement.Movable;
import main.Constant;
import main.General;
import particle.ParticleSystem;

public class Player implements Movable{
	public PVector pos;
	public PVector vel = new PVector(0,0);
	public PVector acc = new PVector(0,0);
	private float width;
	private float height;
	private Color color;
	private boolean alive = true;
	private boolean rendered = false;
	public ParticleSystem deadAnimation;
	public boolean pass = false;
	public boolean colliding = false;


	public Player(float x,float y,float width,float height, Color color){
		pos = new PVector(x,y);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void update(){
		this.pos.add(vel);
		this.vel.add(acc);
		this.acc.mult(0);
		if(this.vel.getY() > 4){
			this.vel.setY(4);
		}
		if(!this.alive) {
			if(!pass) {
				deadAnimation = new ParticleSystem(this.pos.getX(),this.pos.getY(),5,20);
				General.audio.playSound("data/hit-03.wav",-25);
			}
			deadAnimation.update();
			this.pass = true;
		}
	}
	
	public void render() {
		setRendered(true);
	}

	public void collideEdge(){
		if(this.pos.getX() < Constant.BORDER_LEFT){
			this.pos.setX(0);
			this.vel.setX(0);
		}
		if(this.pos.getX() + this.width > Constant.WIDTH - Constant.BORDER_RIGHT){
			this.pos.setX((Constant.WIDTH - this.width) - Constant.BORDER_RIGHT);
			this.vel.setX(0);
		}
		if(this.pos.getY() < Constant.BORDER_UP){
			this.pos.setY(0);
			this.vel.setY(0);
		}
		if(this.pos.getY() + this.height > Constant.HEIGHT - Constant.BORDER_DOWN){
			this.pos.setY((Constant.HEIGHT - this.height) - Constant.BORDER_DOWN);
			this.vel.setY(0);
		}
	}

	public void collidePlatform() {
		for(int i = 0;i < General.platforms.size();i++) {
			if(this.pos.getX() < General.platforms.get(i).pos.getX() + General.platforms.get(i).getWidth() &&
					this.pos.getX() + this.getWidth() > General.platforms.get(i).pos.getX() &&
					this.pos.getY() < General.platforms.get(i).pos.getY() + General.platforms.get(i).getHeight() &&
					this.pos.getY() + this.getHeight() > General.platforms.get(i).pos.getY()) {
				if (this.pos.getX() + this.getWidth() > General.platforms.get(i).pos.getX() + General.platforms.get(i).getWidth()) {
					this.pos.setX(General.platforms.get(i).pos.getX() + General.platforms.get(i).getWidth());
					this.vel.setX(0);
				} else if (this.pos.getY() + this.getHeight() > General.platforms.get(i).pos.getY() + General.platforms.get(i).getHeight()) {
					this.pos.setY(General.platforms.get(i).pos.getY() + General.platforms.get(i).getHeight());
					this.vel.setY(0);
				} else if (this.pos.getY() + this.getHeight() >= General.platforms.get(i).pos.getY() && this.pos.getX() + this.getWidth() >= General.platforms.get(i).pos.getX() && this.pos.getY() < General.platforms.get(i).pos.getY()) {
					this.pos.setY(General.platforms.get(i).pos.getY() - this.getHeight());
					this.vel.setY(0);
				} else if (this.pos.getX() + this.getWidth() >= General.platforms.get(i).pos.getX() && this.pos.getY() + this.getHeight() > General.platforms.get(i).pos.getY()) {
					this.pos.setX(General.platforms.get(i).pos.getX() - this.getWidth());
					this.vel.setX(0);
				}
				General.platforms.get(i).addAttribute();
				colliding = true;
			}
		}
	}

	public void collideEnemy() {
		for(int i = 0;i < General.enemies.size();i++) {
			if(this.pos.getX() < General.enemies.get(i).pos.getX() + General.enemies.get(i).getWidth() &&
					this.pos.getX() + this.getWidth() > General.enemies.get(i).pos.getX() &&
					this.pos.getY() < General.enemies.get(i).pos.getY() + General.enemies.get(i).getHeight() &&
					this.pos.getY() + this.getHeight() > General.enemies.get(i).pos.getY()) {
				this.alive = false;
			}
		}
	}
	
	public void collideCoin() {
		for(int i = 0;i < General.coins.size();i++) {
			if(this.pos.getX() < General.coins.get(i).pos.getX() + General.coins.get(i).getWidth() &&
					this.pos.getX() + this.getWidth() > General.coins.get(i).pos.getX() &&
					this.pos.getY() < General.coins.get(i).pos.getY() + General.coins.get(i).getHeight() &&
					this.pos.getY() + this.getHeight() > General.coins.get(i).pos.getY()) {
				General.audio.playSound("data/collect-point-01.wav",-25);
				General.coins.remove(i);
			}
		}
	}

	public void applyForce(PVector p) {
		this.acc.add(p);
	}

	public void moveLeft() {
		vel.setX(-2);
	}

	public void moveRight() {
		vel.setX(2);
	}

	public void jump() {
		vel.setY(-3);
	}

	public void stop() {

	}

	public float getWidth() {return width;}
	public float getHeight() {return height;}
	public Color getColor() {return color;}
	public boolean isAlive() {return alive;}
	public boolean isRendered() {return rendered;}
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
	public void setColor(Color color) {this.color = color;}
	public void setAlive(boolean alive) {this.alive = alive;}
	public void setRendered(boolean rendered) {this.rendered = rendered;}

}
