package main;

public class Ball {
	public Vector pos;
	public Vector vel = new Vector(0,0);
	public Vector acc = new Vector(0,0);
	private int radius;

	public Ball(float x,float y,int radius){
		pos = new Vector(x,y);
		this.radius = radius;
	}

	public void update(){
		pos.add(vel);
		vel.add(acc);
		acc.scale(0);
	}

	public void collideEdge(){
		if(this.pos.getX() < 0){
			this.pos.setX(0);
			this.vel.setX(-vel.getX() * 0.50f);
		}
		if(this.pos.getX() + this.radius > Main.WIDTH){
			this.pos.setX(Main.WIDTH - this.radius);
			this.vel.setX(-vel.getX() * 0.50f);
		}
		if(this.pos.getY() < 0){
			this.pos.setY(1);
			this.vel.setY(-vel.getY() * 0.50f);
		}
		if(this.pos.getY() + this.radius > Main.HEIGHT){
			this.pos.setY(Main.HEIGHT - this.radius);
			this.vel.setY(-vel.getY() * 0.50f);
		}
	}

	public void collideEachOther(){
		for(int i = 0;i < Main.balls.size();i++){
			if(Main.balls.get(i) != this){
				if(Math.sqrt(((this.pos.getX() - this.getRadius()/2) - (Main.balls.get(i).pos.getX() - Main.balls.get(i).getRadius()/2)) * ((this.pos.getX() - this.getRadius()/2) - (Main.balls.get(i).pos.getX() - Main.balls.get(i).getRadius()/2)) + ((this.pos.getY() - this.getRadius()/2) - (Main.balls.get(i).pos.getY() - Main.balls.get(i).getRadius()/2)) * ((this.pos.getY() - this.getRadius()/2) - (Main.balls.get(i).pos.getY() - Main.balls.get(i).getRadius()/2))) < this.getRadius()/2 + Main.balls.get(i).getRadius()/2){
					//System.out.println(Main.balls.get(i) + " - " + Main.balls.get(j));
					this.vel.set(this.pos.getX() - Main.balls.get(i).pos.getX(), this.pos.getY() - Main.balls.get(i).pos.getY());
					this.vel.normalize();
					//break;
				}
			}
		}
	}

	public void applyForce(Vector force){
		acc.add(force);
	}

	public int getRadius() {return radius;}
	public void setRadius(int radius) {	this.radius = radius;}
}
