package enemy;

import java.awt.Color;

import abstractClass.Enemies;

public class Turret extends Enemies{

	public Turret(float x, float y, float width, float height, Color color) {
		super(x, y, width, height, color);
	}
	
	public void trackPlayer() {
	}

	public void update() {
		this.pos.add(vel);
		this.vel.add(acc);
		this.trackPlayer();
	}
}
