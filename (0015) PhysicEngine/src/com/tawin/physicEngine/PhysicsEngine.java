package com.tawin.physicEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.tawin.physicEngine.entity.Entity;
import com.tawin.physicEngine.entity.shape.Circle;
import com.tawin.physicEngine.entity.shape.Polygon2D;
import com.tawin.physicEngine.entity.shape.StaticLine;
import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Collider2D;
import com.tawin.physicEngine.toolKit.Vec2;

public class PhysicsEngine {
	public static boolean DRAW_POLYGONS_INTERSECTIONS_VERTICES = true;
	public static boolean DRAW_POLYGONS_INTERSECTIONS_VERTICES_COORD = false;
	public static boolean DRAW_CIRCLES_INTERSECTIONS_VERTICES = false;
	public static boolean DRAW_CIRCLES_INTERSECTIONS_VERTICES_COORD = false;
	public static final int THICKNESS_INTERSECTION_VERTICES = 6; 

	Entity focused = null;
	
	ArrayList<Polygon2D> polygons;
	ArrayList<Circle> circles;
	ArrayList<StaticLine> staticlines;

	public PhysicsEngine() {
		this.polygons = new ArrayList<Polygon2D>();
		this.circles = new ArrayList<Circle>();
		this.staticlines = new ArrayList<StaticLine>();
	}

	public void render() {
		Graphics2D g2d = Panneau.getInstance().getG2D();
		int collisions = 0;
		for(int i = 0;i < polygons.size();i++) {
			g2d.setColor(Color.BLACK);
			if(DRAW_POLYGONS_INTERSECTIONS_VERTICES || DRAW_POLYGONS_INTERSECTIONS_VERTICES_COORD) {
				for(int j = i;j < polygons.size();j++){
					ArrayList<Vec2> col = Collider2D.POLY_POLY(polygons.get(i),polygons.get(j));
					for(int k = 0;k < col.size();k++) {
						if(DRAW_POLYGONS_INTERSECTIONS_VERTICES_COORD){
							g2d.drawString("(" + (int)col.get(k).x + "," +  (int)col.get(k).y + ")", (int)col.get(k).x, (int)col.get(k).y);
						}
						if(DRAW_POLYGONS_INTERSECTIONS_VERTICES){
							g2d.fillOval((int)col.get(k).x - THICKNESS_INTERSECTION_VERTICES/2,(int)col.get(k).y - THICKNESS_INTERSECTION_VERTICES/2, THICKNESS_INTERSECTION_VERTICES, THICKNESS_INTERSECTION_VERTICES);
						}
					}
					collisions += col.size();
				}
			}
			polygons.get(i).render();
		}
		for(int i = 0;i < circles.size();i++) {
			if(DRAW_CIRCLES_INTERSECTIONS_VERTICES || DRAW_CIRCLES_INTERSECTIONS_VERTICES_COORD){
				for(int j = i;j < circles.size();j++) {
					ArrayList<Vec2> inter = Collider2D.CIRCLE_CIRCLE_INTER(circles.get(i), circles.get(j));
					for(int k = 0;k < inter.size();k++) {
						if(DRAW_CIRCLES_INTERSECTIONS_VERTICES_COORD){
							g2d.drawString("(" + (int)inter.get(k).x + "," +  (int)inter.get(k).y + ")", (int)inter.get(k).x, (int)inter.get(k).y);
						}
						if(DRAW_CIRCLES_INTERSECTIONS_VERTICES){	
							g2d.fillOval((int)inter.get(k).x - THICKNESS_INTERSECTION_VERTICES/2,(int)inter.get(k).y - THICKNESS_INTERSECTION_VERTICES/2, THICKNESS_INTERSECTION_VERTICES, THICKNESS_INTERSECTION_VERTICES);
						}
					}
					collisions += inter.size();
				}
			}
			circles.get(i).render();
		}
		for(int i = 0;i < polygons.size();i++){
			for(int j = 0;j < circles.size();j++){
				ArrayList<Vec2> inter = Collider2D.POLY_CIRCLE_INTER(polygons.get(i),circles.get(j));
				for(int k = 0;k < inter.size();k++){
					g2d.fillOval((int)inter.get(k).x - THICKNESS_INTERSECTION_VERTICES/2,(int)inter.get(k).y - THICKNESS_INTERSECTION_VERTICES/2, THICKNESS_INTERSECTION_VERTICES, THICKNESS_INTERSECTION_VERTICES);
				}
				collisions += inter.size();
			}
		}
		g2d.drawString("COLLISIONS : " + collisions, 30, 30);
	}

	public void update() {
		for(int i = 0;i < polygons.size();i++) {
			for(int j = 0;j < polygons.size();j++){
				Collider2D.POLY_POLY(polygons.get(i),polygons.get(j));
			}
			polygons.get(i).update();
		}
		for(int i = 0;i < circles.size();i++) {
			circles.get(i).update();
		}
	}

	public ArrayList<Polygon2D> getPolygons(){
		return this.polygons;
	}

	public void addEntity(Entity entity) {
		if(entity instanceof Polygon2D)
			polygons.add((Polygon2D)entity);
		if(entity instanceof Circle)
			circles.add((Circle)entity);
		if(entity instanceof StaticLine)
			staticlines.add((StaticLine)entity);
	}

	public void removeEntity(Entity entity) {
		if(entity instanceof Polygon2D)
			polygons.remove(entity);
		if(entity instanceof Circle)
			circles.remove(entity);
		if(entity instanceof StaticLine)
			staticlines.remove(entity);
	}
}

// 1899 lines