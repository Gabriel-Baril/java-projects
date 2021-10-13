package consummable;

import java.awt.Graphics2D;

import math.Vector2;
import player.Player;

public abstract class Consummable {
	public Vector2 pos;
	public Vector2 vel = new Vector2(0,0);
	public Vector2 acc = new Vector2(0,0);
	public int width;
	public int height;
	
	public Consummable(double x,double y,int width,int height) {
		this.pos = new Vector2(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawRect((int)pos.x, (int)pos.y, width, height);
	}
	
	public void update() {
		pos.add(vel);
		vel.add(acc);
	}
	
	public boolean collidePlayer() {
		if(Player.getInstance().pos.x < this.pos.x + this.width &&
		   Player.getInstance().pos.x + Player.getInstance().getWidth() > this.pos.x &&
		   Player.getInstance().pos.y < this.pos.y + this.height &&
		   Player.getInstance().pos.y + Player.getInstance().getHeight() > this.pos.y) {
			return true;
		}
		return false;
	}
	
	public abstract void effect();
}
