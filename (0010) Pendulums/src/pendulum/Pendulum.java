package pendulum;

import java.awt.Graphics2D;

import math.PVector;
import toolkit.Const;

public class Pendulum {
	PVector origin;
	PVector p;
	PVector p_vel;
	PVector p_acc;
	double radius;
	double angle;
	double aVel;
	double aAcc;
	double mass;
	double damping;

	public Pendulum(PVector origin,double angle,double radius,double damping,double mass){
		Const.pendulums.add(this);

		this.origin = origin;
		this.angle = angle;
		this.radius = radius;
		this.damping = damping;
		this.mass = mass;
	}

	public void update(){
		this.p = new PVector(radius*Math.sin(angle),radius*Math.cos(angle));

		this.aAcc = -Const.GRAVITY * Math.sin(this.angle);

		this.angle += this.aVel;
		this.aVel += this.aAcc;

		aVel *= this.damping;
	}

	public void render(Graphics2D g2d){
		PVector tPos = new PVector(p.x + this.origin.x,p.y + this.origin.y);
		g2d.drawLine((int)this.origin.x,(int)this.origin.y,(int)tPos.x,(int)tPos.y);
		g2d.fillOval((int)(tPos.x - mass/2),(int)(tPos.y - mass/2),(int)mass,(int)mass);
	}


}
