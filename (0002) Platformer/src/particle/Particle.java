package particle;

import Math.PVector;

public class Particle {
	public PVector pos;
	public PVector vel = new PVector((float)(-3 + (Math.random() * ((3 + 3)) + 1)),(float)(-3 + (Math.random() * ((3 + 3)) + 1)));
	public PVector acc = new PVector((float)(-3 + (Math.random() * ((3 + 3)) + 1)),(float)(-3 + (Math.random() * ((3 + 3)) + 1)));
	private float width;
	private float height;
	
	public Particle(float x,float y,float width,float height) {
		this.pos = new PVector(x,y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public void update() {
		this.pos.add(vel);
		this.vel.add(acc);
		this.acc.mult(0);
	}
	
	public void applyForce(PVector p) {
		this.acc.add(p);
	}

	public float getWidth() {return width;}
	public float getHeight() {return height;}
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
}
