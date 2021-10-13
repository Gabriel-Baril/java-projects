package com.tawin.physicEngine.main.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.tawin.physicEngine.debug.Debug;
import com.tawin.physicEngine.entity.shape.Polygon2D;
import com.tawin.physicEngine.main.Fenetre;
import com.tawin.physicEngine.main.Main;
import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Collider2D;
import com.tawin.physicEngine.toolKit.Util;
import com.tawin.physicEngine.toolKit.Vec2;

public class Mouse implements MouseListener, MouseMotionListener{
	private static Mouse instance;

	public void mouseDragged(MouseEvent e) {
		if(false){
			Vec2 mouse = new Vec2(e.getX() - 3,e.getY() - 22);
			for(int i = 0;i < Main.engine.getPolygons().size();i++){
				if(Collider2D.POLY_POINT(Main.engine.getPolygons().get(i), mouse)){
					Main.engine.getPolygons().get(i).vel.set(0, 0);
					Main.engine.getPolygons().get(i).acc.set(0, 0);
					for(int j = 0;j < Main.engine.getPolygons().get(i).vertices.size();j++){
						Vec2 cVertex = Main.engine.getPolygons().get(i).vertices.get(j);
						Vec2 diff = new Vec2(cVertex.x - mouse.x,cVertex.y - mouse.y);
						Panneau.getInstance().getG2D().drawLine((int)mouse.x, (int)mouse.y, (int)(mouse.x - diff.x), (int)(mouse.y - diff.y));
						cVertex.set(mouse.x - diff.x, mouse.y - diff.y);
					}
				}
			}
		}
		
		if(true){	// EDIT MANUALLY THE VERTICES OF A POLYGON
			ArrayList<Polygon2D> polygons = Main.engine.getPolygons();
			boolean taken = false;
			for(int i = 0;i < polygons.size();i++) {
				for(int j = 0;j < polygons.get(i).vertices.size();j++) {
					Vec2 cPoint = polygons.get(i).vertices.get(j);
					if(Util.dist(cPoint, new Vec2(e.getX() - 3,e.getY() - 22)) < 10 && !taken) {
						cPoint.set(e.getX() - 3,e.getY() - 22);
						taken = true;
					}
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		if(Fenetre.running){
			for(int i = 0;i < 10;i++){
				Polygon2D p = new Polygon2D(new Vec2(e.getX() - 3,e.getY() - 22), 10, Util.rand(3, 6));
				p.setColor(Util.rand(0, 255), Util.rand(0, 255), Util.rand(0, 255));
				Main.engine.addEntity(p);
			}
		}
		
		
		ArrayList<Polygon2D> shapes = Main.engine.getPolygons();
		for(int i = 0;i < shapes.size();i++) {
			if(shapes.get(i).isClicked(e.getX() - 3,e.getY() - 22)) {
				Panneau.getInstance().getG2D().drawOval(e.getX() - 3,e.getY() - 22, 4, 4);
				System.out.println("Entity : " + i + " touched");
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}
	
	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
	}

	public static Mouse getInstance(){
		if(instance == null){
			instance = new Mouse();
		}
		return instance;
	}
}
