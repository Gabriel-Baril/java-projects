package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	public static Panneau instance;
	public Graphics2D g2d;

	ArrayList<Arm> poly = new ArrayList<Arm>();
	
	private Panneau() {
		poly.add(new Arm(new Vec2(Fenetre.WIDTH/2,Fenetre.HEIGHT/2),200,0));
		poly.add(new Arm(new Vec2(150,150),200,0));
	}
	
	PolyArm p = new PolyArm(poly);
	
	Vec2 pos = new Vec2(Fenetre.WIDTH/2,Fenetre.HEIGHT/2);
	Vec2 vel = new Vec2(2,2.5);
	
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(Arm arm : Main.arms) {
			arm.DRAW();
		}
		if(pos.x < 0 || pos.x > Fenetre.WIDTH - 10) {
			vel.x *= -1;
		}
		if(pos.y < 0 || pos.y > Fenetre.HEIGHT - 10) {
			vel.y *= -1;
		}
		pos.x += vel.x;
		pos.y += vel.y;
		g2d.fillOval((int)pos.x - 5,(int)pos.y - 5, 10, 10);
		p.update();
		p.target2arms(new Vec2(pos.x,pos.y));
		
	}
	
	public Graphics2D getG2D() {
		return this.g2d;
	}
	
	public static Panneau getInstance() {
		if(Panneau.instance == null) {
			instance = new Panneau();
		}
		return Panneau.instance;
	}
}
