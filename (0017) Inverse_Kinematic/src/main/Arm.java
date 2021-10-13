package main;

import java.awt.Graphics2D;

public class Arm {
	public static final int JoinWeight = 10;
	
	public Vec2 posStart;
	public Vec2 posEnd;
	
	private int len;
	
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	private double angle;
	
	public Arm(Vec2 posStart,int len,double angle) {
		this.posStart = posStart;
		this.len = len;
		this.angle = angle;
		posEnd = getPosEnd();
		Main.arms.add(this);
	}
	
	public Vec2 getPosEnd() {
		return new Vec2(len*Math.cos(Math.toRadians(this.angle)) + posStart.x,len*Math.sin(Math.toRadians(this.angle)) + posStart.y);
	}
	
	public double getAngle() {
		return Math.atan2(posEnd.y - posStart.y, posEnd.x - posStart.x);
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.posEnd = getPosEnd();
	}
	
	public void rotate(double ang) {
		setAngle(Math.toDegrees(getAngle()) + ang);
	}
	
	public void DRAW() {
		Graphics2D g2d = Panneau.getInstance().getG2D(); 
		g2d.drawOval((int)posStart.x - JoinWeight/2, (int)posStart.y - JoinWeight/2, JoinWeight, JoinWeight);
		g2d.drawLine((int)posStart.x, (int)posStart.y, (int)posEnd.x, (int)posEnd.y);
		g2d.drawOval((int)posEnd.x - JoinWeight/2, (int)posEnd.y - JoinWeight/2, JoinWeight, JoinWeight);
		g2d.drawString("" + (int)this.angle,(int)posStart.x, (int)posStart.y);
	}
}
