package platform;

import java.awt.Color;

import Math.PVector;
import abstractClass.Platforms;
import main.Constant;

public class MovingPlatform extends Platforms{

	public MovingPlatform(float x, float y, float width, float height, Color color,PVector p) {
		super(x, y, width, height, color, p);
	}

	public void update() {
		this.pos.add(vel);
		this.vel.add(acc);
		this.vel.mult(0);
		this.applyForce(new PVector(1,0));
	}
	
	public void applyForce(PVector p) {
		this.vel.add(p);
	}
}
