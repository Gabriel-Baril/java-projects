package com.tawin.physicEngine.entity.joint;

import java.awt.Color;
import java.awt.Graphics2D;

import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Vec2;

public class LinearJoint extends AbstractJoint {
	private static final float thickness = 13;
	
	Vec2 pos; // position of the first arm
	float L1; // length of the first arm
	float angle; // angle of the first arm
	float L2; // length of the other arm
	
	
	public LinearJoint(Vec2 pos,float L1,float angle,float L2) {
		this.pos = pos;
		this.L1 = L1;
		this.angle = angle;
		this.L2 = L2;
	}

	public void render() {
		//P3 | 			  |P6
		//P1 |------------|P4
		//P2 |			  |P5
		
		Graphics2D g2d = Panneau.getInstance().getG2D();
		Vec2 p1 = pos;
		Vec2 p4 = new Vec2(p1.x + L1*Math.cos(Math.toRadians(angle)),p1.y + L1*Math.sin(Math.toRadians(angle)));
		Vec2 p2 = Vec2.PerpenticularClockwise(new Vec2(p1.x - p4.x,p1.y - p4.y)).setMag(thickness/2);
		Vec2 p3 = Vec2.PerpenticularCounterClockwise(new Vec2(p1.x - p4.x,p1.y - p4.y)).setMag(thickness/2);
		
		g2d.setColor(Color.BLACK);
		//g2d.drawLine((int)p1.x, (int)p1.y, (int)p4.x, (int)p4.y);
		g2d.drawLine((int)p1.x, (int)p1.y, (int)(p2.x + p1.x), (int)(p2.y + p1.y));
		g2d.drawLine((int)p1.x, (int)p1.y, (int)(p3.x + p1.x), (int)(p3.y + p1.y));
		g2d.drawLine((int)p4.x, (int)p4.y, (int)(p2.x + p4.x), (int)(p2.y + p4.y));
		g2d.drawLine((int)p4.x, (int)p4.y, (int)(p3.x + p4.x), (int)(p3.y + p4.y));
		
		g2d.drawLine((int)(p4.x + p2.x), (int)(p4.y + p2.y), (int)(p1.x + p2.x), (int)(p1.y + p2.y));
		g2d.drawLine((int)(p4.x + p3.x), (int)(p4.y + p3.y), (int)(p1.x + p3.x), (int)(p1.y + p3.y));
	
		
	
	}

	public void update() {
		
		angle++;
	}

	public void getInfo() {
		
	}

	public void link(AbstractJoint joint) {
		// TODO Auto-generated method stub
		
	}
}
