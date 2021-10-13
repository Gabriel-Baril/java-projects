package pendulum;

import java.awt.Graphics2D;

import math.PVector;
import toolkit.Const;

public class DoublePendulum {
	float g = 0.4f;
	PVector origin;
	float m1 = 2;
	float m2 = 2;

	PVector m1_pos;
	PVector m1_v;
	PVector m1_a;
	float m1_r;
	float m1_angle;
	float m1_angle_v = 0;
	float m1_angle_a;

	PVector m2_pos;
	PVector m2_v;
	PVector m2_a;
	float m2_r;
	float m2_angle;
	float m2_angle_v = 0;
	float m2_angle_a;

	public DoublePendulum(PVector origin,float m1_r,float m1_angle,float m2_r,float m2_angle){
		Const.doublePendulums.add(this);
		
		this.origin = origin;
		this.m1_r = m1_r;
		this.m1_angle = m1_angle;
		this.m2_r = m2_r;
		this.m2_angle = m2_angle;
	}

	public void update(){
		this.updateData();
		this.m1_pos.add(m1_v);
		this.m1_v.add(m1_a);

		this.m2_pos.add(m2_v);
		this.m2_v.add(m2_a);

		this.m1_angle_v += this.m1_angle_a;
		this.m2_angle_v += this.m2_angle_a;
		this.m1_angle += this.m1_angle_v;
		this.m2_angle += this.m2_angle_v;

		m1_angle_v *= 0.999;
		m2_angle_v *= 0.999;
	}
	
	public void drawPendulum(Graphics2D g2d) {
		g2d.fillOval((int)origin.x,(int)origin.y,5,5);
		g2d.drawLine((int)origin.x + 2,(int)origin.y + 2,(int)m1_pos.x + 5,(int)m1_pos.y + 5);
		g2d.fillOval((int)m1_pos.x,(int)m1_pos.y,10,10);
		g2d.drawLine((int)m1_pos.x + 5,(int)m1_pos.y + 5,(int)m2_pos.x + 5,(int)m2_pos.y + 5);
		g2d.fillOval((int)m2_pos.x,(int)m2_pos.y,10,10);
	}

	public void updateData(){
		this.m1_angle_a = (float)( ( -g * (2 * m1 + m2) * Math.sin(m1_angle) - m2 * g * Math.sin(m1_angle - 2 * m2_angle) - 2 * Math.sin(m1_angle - m2_angle) * m2 * (m2_angle_v * m2_angle_v * m2_r + m1_angle_v * m1_angle_v * m1_r * Math.cos(m1_angle - m2_angle)) )/(m1_r * (2 * m1 + m2 - m2 * Math.cos(2 * m1_angle - 2 * m2_angle))) );
		this.m2_angle_a = (float)( ( 2 * Math.sin(m1_angle - m2_angle)*(m1_angle_v * m1_angle_v * m1_r * (m1 + m2) + g * (m1 + m2) * Math.cos(m1_angle) + m2_angle_v * m2_angle_v * m2_r * m2 * Math.cos(m1_angle - m2_angle)) )/(m2_r * (2 * m1 + m2 - m2 * Math.cos(2 * m1_angle - 2 * m2_angle))) );

		this.m1_pos = new PVector(m1_r * -Math.sin(m1_angle) + origin.x,-m1_r * -Math.cos(m1_angle) + origin.y);
		this.m2_pos = new PVector(m1_pos.x + m2_r * -Math.sin(m2_angle),m1_pos.y - m2_r * -Math.cos(m2_angle));
		this.m1_v = new PVector(m1_angle_v * m1_r * Math.cos(m1_angle),m1_angle_v * m1_r * Math.sin(m1_angle));
		this.m2_v = new PVector(m1_v.x + m2_angle_v * m2_r * Math.cos(m2_angle),m1_v.y + m2_angle_v * m2_r * Math.sin(m2_angle));
		this.m1_a = new PVector((float)( -m1_angle_v * m1_angle_v * m1_r * Math.sin(m1_angle) + m1_angle_a * m1_r * Math.cos(m1_angle) ),(float)( m1_angle_v * m1_angle_v * m1_r * Math.cos(m1_angle) + m1_angle_a * m1_r * Math.sin(m1_angle) ));
		this.m2_a = new PVector((float)(m1_a.x - m2_angle_v * m2_angle_v * m2_r * Math.sin(m2_angle) + m2_angle_a * m2_r * Math.cos(m2_angle)),(float)(m1_a.y + m2_angle_v * m2_angle_v * m2_r * Math.cos(m2_angle) + m2_angle_a * m2_r * Math.sin(m2_angle)));
	}
}
