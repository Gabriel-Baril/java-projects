package com.tawin.physicEngine.toolKit;

import java.util.ArrayList;

import com.tawin.physicEngine.entity.Segment;
import com.tawin.physicEngine.entity.shape.Circle;
import com.tawin.physicEngine.entity.shape.Polygon2D;

public class Collider2D {

	private Collider2D() {}

	public static ArrayList<Vec2> POLY_CIRCLE_INTER(Polygon2D p2d,Circle c) {
		ArrayList<Vec2> intersections = new ArrayList<Vec2>();
		if(POLY_CIRCLE_BOOL(p2d, c)){
			ArrayList<Segment> segments = p2d.Segments();
			for(int i = 0;i < segments.size();i++){
				ArrayList<Vec2> inter = Collider2D.CIRCLE_LINE_INTER(c, segments.get(i));
				for(int j = 0;j < inter.size();j++){
					intersections.add(inter.get(j));
				}
			}
			return intersections;
		}
		return intersections;
	}

	public static boolean POLY_CIRCLE_BOOL(Polygon2D p2d,Circle c) {
		ArrayList<Segment> segments = p2d.Segments();
		for(int i = 0;i < segments.size();i++){
			if(CIRCLE_LINE_BOOL(c,segments.get(i)))
				return true;
		}
		return false;
	}

	public static ArrayList<Vec2> CIRCLE_CIRCLE_INTER(Circle c1,Circle c2) {
		// http://nains-games.com/2014/12/intersection-de-deux-cercles.html
		ArrayList<Vec2> intersections = new ArrayList<Vec2>();
		if(CIRCLE_CIRCLE_BOOL(c1,c2)){

			double r1 = c1.getRadius();
			double x1 = c1.getPos().x;
			double y1 = c1.getPos().y;

			double r2 = c2.getRadius();
			double x2 = c2.getPos().x;
			double y2 = c2.getPos().y;

			double a = (-x1*x1 - y1*y1 + x2*x2 + y2*y2 + r1*r1 - r2*r2)/(2*(y2-y1));
			double d = (x2-x1)/(y2-y1);

			double A = d*d + 1;
			double B = -2*x1 + 2*y1*d - 2*a*d;
			double C = x1*x1 + y1*y1 - 2*y1*a + a*a - r1*r1;

			double delta = B*B - 4*A*C;

			if(delta > 0){
				double sol_1 = (-B + Math.sqrt(delta))/(2*A);
				double sol_2 = (-B - Math.sqrt(delta))/(2*A);

				intersections.add(new Vec2(sol_1,a - sol_1*d));
				intersections.add(new Vec2(sol_2,a - sol_2*d));
			}

			if(delta == 0){
				double sol = -B/(2*A);
				intersections.add(new Vec2(sol,a - sol*d));
			}
			return intersections;
		}
		return intersections;
	}

	public static boolean CIRCLE_CIRCLE_BOOL(Circle c1,Circle c2) {
		if(Util.dist(c1.getPos(), c2.getPos()) <= c1.getRadius() + c2.getRadius())
			return true;
		return false;
	}

	public static ArrayList<Vec2> CIRCLE_LINE_INTER(Circle c,Segment s){
		ArrayList<Vec2> intersections = new ArrayList<Vec2>();
		if(CIRCLE_LINE_BOOL(c,s)){
			double x1 = s.getStart().x;
			double y1 = s.getStart().y;
			double x2 = s.getEnd().x;
			double y2 = s.getEnd().y;

			double cx = c.getPos().x;
			double cy = c.getPos().y;
			double r = c.getRadius();

			double a = (y2-y1)/(x2-x1);
			double b = y1 - a*x1;

			double A = 1 + a*a;
			double B = -2*cx + 2*a*b - 2*a*cy;
			double C = -r*r + cx*cx + b*b - 2*b*cy + cy*cy;

			double delta = B*B - 4*A*C;

			if(delta > 0){
				double sol_1 = (-B + Math.sqrt(delta))/(2*A);
				double sol_2 = (-B - Math.sqrt(delta))/(2*A);

				if(Util.between(s.getMinX(), s.getMaxX(), sol_1))
					intersections.add(new Vec2(sol_1,a*sol_1 + b));
				if(Util.between(s.getMinX(), s.getMaxX(), sol_2))
					intersections.add(new Vec2(sol_2,a*sol_2 + b));
			}

			if(delta == 0){
				double sol = -B/(2*A);
				if(Util.between(s.getMinX(), s.getMaxX(), sol))
					intersections.add(new Vec2(sol,a*sol + b));
			}

			return intersections;
		}
		return intersections;
	}

	public static boolean CIRCLE_LINE_BOOL(Circle c,Segment s) {
		double a = (s.getEnd().y-s.getStart().y)/(s.getEnd().x-s.getStart().x);
		double b = s.getStart().y - a*s.getStart().x;

		if(Math.abs(a*c.getPos().x - c.getPos().y + b)/Math.sqrt(a*a + 1) <= c.getRadius() )
			return true;
		return false;
	}

	public static boolean CIRCLE_POINT(Circle c,Vec2 point) {
		if(Util.dist(c.getPos(), point) <= c.getRadius())
			return true;
		return false;
	}

	public static boolean LINE_LINE_BOOL(Segment s1,Segment s2) {
		double uA = ((s2.getEnd().getX() - s2.getStart().getX()) * (s1.getStart().getY() - s2.getStart().getY()) - (s2.getEnd().getY() - s2.getStart().getY()) * (s1.getStart().getX() - s2.getStart().getX())) / ((s2.getEnd().getY() - s2.getStart().getY()) * (s1.getEnd().getX() - s1.getStart().getX()) - (s2.getEnd().getX() - s2.getStart().getX()) * (s1.getEnd().getY() - s1.getStart().getY()));
		double uB = ((s1.getEnd().getX() - s1.getStart().getX()) * (s1.getStart().getY() - s2.getStart().getY()) - (s1.getEnd().getY() - s1.getStart().getY()) * (s1.getStart().getX() - s2.getStart().getX())) / ((s2.getEnd().getY() - s2.getStart().getY()) * (s1.getEnd().getX() - s1.getStart().getX()) - (s2.getEnd().getX() - s2.getStart().getX()) * (s1.getEnd().getY() - s1.getStart().getY()));
		if(uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1){
			return true;
		}
		return false;
	}

	public static Vec2 LINE_LINE_INTER(Segment s1,Segment s2) {
		if(LINE_LINE_BOOL(s1,s2)) {
			double x1 = s1.getStart().x;
			double y1 = s1.getStart().y;
			double x2 = s1.getEnd().x;
			double y2 = s1.getEnd().y;
			double x3 = s2.getStart().x;
			double y3 = s2.getStart().y;
			double x4 = s2.getEnd().x;
			double y4 = s2.getEnd().y;

			double p_s1 = (y2-y1)/(x2-x1);
			double p_s2 = (y4-y3)/(x4-x3);

			double x_inter = (y3 - y1 - x3*p_s2 + x1*p_s1)/(p_s1 - p_s2);
			double y_inter = (p_s1*x_inter + (y1 - x1*p_s1));
			return new Vec2(x_inter,y_inter);
		}
		return null;
	}

	public static boolean LINE_POINT(Segment s,Vec2 point) {
		if(Util.dist(point, s.getStart()) + Util.dist(point, s.getEnd()) == s.dist())
			return true;
		return false;
	}

	public static boolean LINE_POINT(Segment s,Vec2 point,double buffer) {
		if(Math.abs(Util.dist(point, s.getStart()) + Util.dist(point, s.getEnd()) - s.dist()) <= buffer)
			return true;
		return false;
	}

	public static boolean POINT_POINT(Vec2 p1,Vec2 p2) {
		if(p1.x == p2.x && p1.y == p2.y)
			return true;
		return false;
	}

	public static boolean POINT_POINT(Vec2 p1,Vec2 p2,double buffer) {
		if(Math.abs(p1.x - p2.x) <= buffer && Math.abs(p1.y - p2.y) <= buffer)
			return true;
		return false;
	}

	public static boolean POINT_POINT(Vec2 p1,Vec2 p2,double bufferX,double bufferY) {
		if(Math.abs(p1.x - p2.x) <= bufferX && Math.abs(p1.y - p2.y) <= bufferY)
			return true;
		return false;
	}


	public static boolean POLY_POINT(Polygon2D p2d,Vec2 point) {
		boolean collision = false;
		int next = 0;
		for(int i = 0;i < p2d.vertices.size();i++) {
			next = i + 1;
			if(next == p2d.vertices.size()) next = 0;

			Vec2 vc = p2d.vertices.get(i);
			Vec2 vn = p2d.vertices.get(next);

			if(((vc.y > point.y && vn.y < point.y) || (vc.y < point.y && vn.y > point.y)) && (point.x < (vn.x-vc.x)*(point.y-vc.y) / (vn.y-vc.y)+vc.x)){
				collision = !collision;
			}
		}
		return collision;
	}

	public static boolean POLY_LINE(Polygon2D p, Segment s) {
		ArrayList<Segment> segments = p.Segments();
		if(Collider2D.POLY_POINT(p, s.getStart()) || Collider2D.POLY_POINT(p, s.getEnd()))
			return true;

		for(int i = 0;i < segments.size();i++) {
			if(Collider2D.LINE_LINE_BOOL(segments.get(i),s))
				return true;
		}

		return false;
	}

	public static boolean POLY_AABB(Polygon2D p1,Polygon2D p2){
		if(p1.getMinX() < p2.getMaxX() &&
				p1.getMaxX() > p2.getMinX() &&
				p1.getMinY() < p2.getMaxY() &&
				p1.getMaxY() > p2.getMinY()){
			return true;
		}
		return false;
	}



	public static ArrayList<Vec2> POLY_POLY(Polygon2D p1,Polygon2D p2) {
		ArrayList<Vec2> collisions = new ArrayList<Vec2>();
		if(p1 != p2){
			if(Collider2D.POLY_AABB(p1,p2)){
				ArrayList<Segment> segs_p2 = p2.Segments();
				ArrayList<Segment> segs_p1 = p1.Segments();
				for(int j = 0;j < segs_p2.size();j++){
					for(int k = 0;k < segs_p1.size();k++){ 
						if(Collider2D.LINE_LINE_BOOL(segs_p2.get(j),segs_p1.get(k))){
							collisions.add(Collider2D.LINE_LINE_INTER(segs_p2.get(j),segs_p1.get(k)));
							p1.vel.scale(0);
							if(p2.movable){
								p2.vel.add(segs_p1.get(k).getPerp().normalize().scale(1.1));
								p1.vel.add(segs_p2.get(j).getPerp().normalize().scale(1.1));
							}else{
								p1.vel.add(segs_p2.get(j).getPerp().normalize().scale(1.1));
							}
						}
					}
				}
			}
		}
		return collisions;
	}



}
