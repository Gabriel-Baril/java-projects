package com.tawin.physicEngine.entity.shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.tawin.physicEngine.debug.Debug;
import com.tawin.physicEngine.entity.Entity;
import com.tawin.physicEngine.entity.Segment;
import com.tawin.physicEngine.main.Fenetre;
import com.tawin.physicEngine.main.Main;
import com.tawin.physicEngine.rendering.Panneau;
import com.tawin.physicEngine.toolKit.Collider2D;
import com.tawin.physicEngine.toolKit.Util;
import com.tawin.physicEngine.toolKit.Vec2;

public class Polygon2D implements Entity{
	public static boolean RENDER_COLOR_FILL = true;
	public static boolean DRAW_TRIANGULATION = false;
	public static boolean DRAW_TRIANGULATION_INF = false;
	public static boolean DRAW_SEGMENT = true;
	public static boolean DRAW_SEGMENT_BARYCENTER = false;
	public static boolean DRAW_SELF_INTERSECTION_VERTICES = false;
	public static boolean DRAW_SELF_INTERSECTION_COORDONATE = false;
	public static boolean DRAW_VERTICES_ANGLE = false;
	public static boolean DRAW_VERTICES_EAR = false;
	public static boolean DRAW_VERTICES_CONVEX = false;
	public static boolean DRAW_VERTICES_COORDONATE = false;
	public static boolean DRAW_VERTICES_ANGLE_ARC = false;
	public static boolean DRAW_PERP_SEGMENT = false;
	public static boolean DRAW_VERTICES = true;
	public static boolean DRAW_BARYCENTER = false;
	public static boolean DRAW_AABB_BOX = false;
	public static boolean DRAW_CIRCLE_ = false;

	public static int POINT_THICKNESS = 4;

	public boolean movable = true;

	public ArrayList<Vec2> vertices;
	private Color c;
	public Vec2 vel = new Vec2(0,0);
	public Vec2 acc = new Vec2(0,0);

	// BOX
	public Polygon2D(double x,double y,int width,int height) {
		this.vertices = new ArrayList<Vec2>();
		Vec2 pos = new Vec2(x,y);
		this.vertices.add(new Vec2(pos.x,pos.y));
		this.vertices.add(new Vec2(pos.x + width,pos.y));
		this.vertices.add(new Vec2(pos.x + width,pos.y + height));
		this.vertices.add(new Vec2(pos.x,pos.y + height));
	}

	// REGULAR POLYGON
	public Polygon2D(Vec2 pos,double radius,int nb_vertice) {
		this.vertices = new ArrayList<Vec2>();
		Vec2 iPoint = new Vec2(pos.x + radius,pos.y);
		for(int i = 0;i < nb_vertice;i++) {
			this.vertices.add(Polygon2D.rotate(pos,iPoint,(360/nb_vertice)*i)); 
		}
	}

	// COPY CONSTRUCTOR
	public Polygon2D(Polygon2D Polygon2D) {
		this.vertices = new ArrayList<Vec2>();
		for(int i = 0;i < Polygon2D.vertices.size();i++) {
			this.vertices.add(new Vec2(Polygon2D.vertices.get(i).x,Polygon2D.vertices.get(i).y));
		}
		this.vel = new Vec2(Polygon2D.vel.x,Polygon2D.vel.y);
		this.acc = new Vec2(Polygon2D.acc.x,Polygon2D.acc.y);
	}

	public Polygon2D(ArrayList<Vec2> vertices) {
		this.vertices = vertices;
	}

	public Polygon2D(ArrayList<Vec2> vertices,Vec2 vel) {
		this.vertices = vertices;
		this.vel = vel;
	}

	public Polygon2D(ArrayList<Vec2> vertices,Vec2 vel,Vec2 acc) {
		this.vertices = vertices;
		this.vel = vel;
		this.acc = acc;
	}

	public double getMinX(){
		double minX = vertices.get(0).getX();
		for(int i = 1;i < vertices.size();i++){
			Vec2 cPoint = vertices.get(i);
			if(cPoint.getX() < minX){
				minX = cPoint.getX();
			}
		}
		return minX;
	}

	public double getMaxX(){
		double maxX = vertices.get(0).getX();
		for(int i = 1;i < vertices.size();i++){
			Vec2 cPoint = vertices.get(i);
			if(cPoint.getX() > maxX){
				maxX = cPoint.getX();
			}
		}
		return maxX;
	}

	public double getMinY(){
		double minY = vertices.get(0).getY();
		for(int i = 1;i < vertices.size();i++){
			Vec2 cPoint = vertices.get(i);
			if(cPoint.getY() < minY){
				minY = cPoint.getY();
			}
		}
		return minY;
	}

	public double getMaxY(){
		double maxY = vertices.get(0).getY();
		for(int i = 1;i < vertices.size();i++){
			Vec2 cPoint = vertices.get(i);
			if(cPoint.getY() > maxY){
				maxY = cPoint.getY();
			}
		}
		return maxY;
	}

	public Vec2 getFartestPoint(){
		Vec2 fartest = vertices.get(0);
		for(int i = 1;i < vertices.size();i++){
			if(Util.dist(Center(), vertices.get(i)) > Util.dist(Center(), fartest)){
				fartest = vertices.get(i);
			}
		}
		return fartest;
	}
	
	public void collide_edge(){
		if(movable){
			if(getMinX() <= 0 || getMaxX() >= Fenetre.WIDTH){
				this.vel.setX(-this.vel.getX());
			}
			if(getMinY() <= 0 || getMaxY() >= Fenetre.HEIGHT){
				//this.vel.setY(-this.vel.scale(0.9).getY());
				Main.engine.removeEntity(this);
			}
		}
	}

	public void applyForce(Vec2 force){
		this.acc = force;
	}

	public void update() {
		//collide_edge();
		if(movable) {
			applyForce(new Vec2(0,Util.GRAVITY));
			for(int i = 0;i < this.vertices.size();i++) {
				this.vertices.get(i).add(vel);
			}
			this.vel.add(acc);
			rotate(vel.getX()*vel.getY()*1.5);
		}else{
			vel.scale(0);
			acc.scale(0);
		}
	}



	public double area() {


		return 0;
	}

	public void render() {
		Graphics2D g2d = Panneau.getInstance().getG2D();

		if(RENDER_COLOR_FILL){
			g2d.setColor(this.c);
			int[] pointX = new int[vertices.size()];
			int[] pointY = new int[vertices.size()];
			for(int j = 0;j < vertices.size();j++) {
				pointX[j] = (int)vertices.get(j).getX();
				pointY[j] = (int)vertices.get(j).getY();
			}
			g2d.fillPolygon(pointX,pointY,vertices.size());
		}

		if(DRAW_SELF_INTERSECTION_COORDONATE || DRAW_SELF_INTERSECTION_VERTICES) {
			g2d.setColor(Color.RED);
			ArrayList<Vec2> selfIntersection = getSelfIntersections();
			for(int i = 0;i < selfIntersection.size();i++) {
				if(DRAW_SELF_INTERSECTION_COORDONATE) {
					g2d.drawString("(" + ((int)selfIntersection.get(i).x - 3) + "," + ((int)selfIntersection.get(i).y - 3) + ")", (int)selfIntersection.get(i).x,(int)selfIntersection.get(i).y);
				}
				if(DRAW_SELF_INTERSECTION_VERTICES) {
					g2d.fillOval((int)selfIntersection.get(i).x - 3,(int)selfIntersection.get(i).y - 3, 6, 6);	
				}
			}
		}


		g2d.setColor(Color.BLACK);
		Vec2 center = Center();

		g2d.setFont(new Font(null, Font.PLAIN, 9)); // Change the font


		if(DRAW_TRIANGULATION){
			ArrayList<Integer> triangulation = this.triangulate();
			System.out.println(triangulation);
			//ArrayList<Segment> segments = Segments();

			//for(int i = 0;i < segments.size();i++){
			//g2d.drawString("" + (int)segments.get(i).dist(), (int)(segments.get(i).getStart().x + segments.get(i).getEnd().x)/2, (int)(segments.get(i).getStart().y + segments.get(i).getEnd().y)/2);
			//}

			for(int i = 0;i < triangulation.size();i+=3) {
				Vec2 pP = this.vertices.get(triangulation.get(i));
				Vec2 cP = this.vertices.get(triangulation.get(i + 1));
				Vec2 nP = this.vertices.get(triangulation.get(i + 2)); 

				Segment s1 = new Segment(pP,cP);
				Segment s2 = new Segment(cP,nP);
				Segment s3 = new Segment(pP,nP);

				double p = (s1.dist() + s2.dist() + s3.dist())/2; // demi_PERIMETER

				if(DRAW_TRIANGULATION_INF){
					g2d.drawString("" + (int)Math.sqrt(p*(p - s1.dist())*(p - s2.dist())*(p - s3.dist())), (int)(pP.x + cP.x + nP.x)/3, (int)(pP.y + cP.y + nP.y)/3);
					g2d.drawString("" + (int)Util.dist(pP, nP), (int)((pP.x + nP.x)/2),(int)((pP.y + nP.y)/2));
				}

				g2d.drawLine((int)this.vertices.get(triangulation.get(i)).x , (int)this.vertices.get(triangulation.get(i)).y, (int)this.vertices.get(triangulation.get(i+2)).x , (int)this.vertices.get(triangulation.get(i+2)).y);		
			}
		}

		ArrayList<Integer> earIndexes = new ArrayList<>();
		for(int i = 0;i < this.vertices.size();i++){
			earIndexes.add(i);
		}

		if(DRAW_SEGMENT){
			for(int i = 0; i < this.vertices.size();i++) {
				int prev = (i == 0) ? (vertices.size() - 1) : i - 1;
				int next = (i == vertices.size() - 1) ? 0 : i + 1;
				Vec2 cPoint = vertices.get(i);
				Vec2 nPoint = vertices.get(next);
				g2d.drawLine((int)cPoint.getX(),(int)cPoint.getY(),(int)nPoint.getX(),(int)nPoint.getY()); // Draw the segments of the polygon
			}
		}

		for(int i = 0;i < vertices.size();i++) {
			int prev = (i == 0) ? (vertices.size() - 1) : i - 1;
			int next = (i == vertices.size() - 1) ? 0 : i + 1;
			Vec2 pPoint = vertices.get(prev);
			Vec2 cPoint = vertices.get(i);
			Vec2 nPoint = vertices.get(next);
			Segment perpSeg = Perp_Segments(8).get(i);

			if(DRAW_VERTICES_ANGLE){
				g2d.drawString((int)angleAt(pPoint,cPoint,nPoint,earIndexes) + "°", (int)cPoint.getX(), (int)cPoint.getY() - 7); // Draw the angle of each point
			}

			if(DRAW_VERTICES_EAR){
				g2d.drawString("" + isEar(pPoint,cPoint,nPoint,earIndexes), (int)cPoint.getX() + 17, (int)cPoint.getY() - 7);
			}

			if(DRAW_VERTICES_COORDONATE){
				g2d.drawString(i + "(" + (int)cPoint.getX() + "," + (int)cPoint.getY() + ")", (int)cPoint.getX(), (int)cPoint.getY()); // Draw the coordonate of each point
			}

			if(DRAW_VERTICES_ANGLE_ARC){
				int arc_radius = 15;
				double a_ = arc_radius;
				double b_ = Util.dist(pPoint, cPoint);
				double c_ = 0;
				double theta = 0;
				if(cPoint.y - pPoint.y >= 0) {
					c_ = Util.dist(pPoint, new Vec2(cPoint.x + arc_radius,cPoint.y));
					theta = Math.toDegrees(Math.acos( (c_*c_ - a_*a_ - b_*b_)/(-2*a_*b_) ));
					g2d.drawArc((int)cPoint.getX() - arc_radius/2, (int)cPoint.getY() - arc_radius/2, arc_radius, arc_radius,(int)theta, (int)angleAt(pPoint,cPoint,nPoint,earIndexes));
				} else {
					c_ = Util.dist(pPoint, new Vec2(cPoint.x - arc_radius,cPoint.y));
					theta = Math.toDegrees(Math.acos( (c_*c_ - a_*a_ - b_*b_)/(-2*a_*b_) ));
					g2d.drawArc((int)cPoint.getX() - arc_radius/2, (int)cPoint.getY() - arc_radius/2, arc_radius, arc_radius, 180 + (int)theta, (int)angleAt(pPoint,cPoint,nPoint,earIndexes));
				}
			}

			if(DRAW_PERP_SEGMENT){
				g2d.drawLine((int)perpSeg.getStart().getX(),(int)perpSeg.getStart().getY(), (int)perpSeg.getEnd().getX(), (int)perpSeg.getEnd().getY()); // Draw the perpenticular segments of the segments of the polygon
			}

			if(DRAW_VERTICES){
				g2d.fillOval((int)cPoint.getX() - POINT_THICKNESS/2,(int)cPoint.getY() - POINT_THICKNESS/2,POINT_THICKNESS,POINT_THICKNESS); // Draw the vertices	
			}

			if(DRAW_BARYCENTER){
				g2d.fillOval((int)center.getX() - POINT_THICKNESS/2,(int)center.getY() - POINT_THICKNESS/2, POINT_THICKNESS, POINT_THICKNESS); // Draw the barycenter
			}

			if(DRAW_SEGMENT_BARYCENTER){
				g2d.drawLine((int)cPoint.getX(),(int)cPoint.getY(),(int)center.getX(),(int)center.getY()); // Draw the segments between each vertices and the barycenter
			}

			if(DRAW_AABB_BOX){
				g2d.drawRect((int)getMinX(), (int)getMinY(), (int)(getMaxX() - getMinX()), (int)(getMaxY() - getMinY())); // Draw the AABB box of the polygon	
			}

			if(movable){
				if(DRAW_CIRCLE_){
					g2d.drawOval((int)(Center().getX() - Util.dist(Center(),getFartestPoint())), (int)(Center().getY() - Util.dist(Center(),getFartestPoint())), (int)(Util.dist(Center(),getFartestPoint()))*2, (int)(Util.dist(Center(),getFartestPoint()))*2); // Draw the general circle of the polygon
				}
			}
		}

	}

	// Return a arraylist that contain all the normal of each segments
	public ArrayList<Segment> Perp_Segments(double dist) {
		ArrayList<Segment> perp_segments = new ArrayList<Segment>();
		ArrayList<Segment> segments = Segments();
		for(int i = 0;i < segments.size();i++) {
			Vec2 midPoint = segments.get(i).midPoint();
			perp_segments.add(new Segment(midPoint,segments.get(i).getPerp().normalize().scale(dist).add(midPoint)));
		}
		return perp_segments;
	}


	// Return a arraylist that contain all segments
	public ArrayList<Segment> Segments() {
		ArrayList<Segment> segments = new ArrayList<Segment>();	
		for(int i = 0;i < vertices.size();i++) {
			int next = (i == vertices.size() - 1) ? 0 : i + 1;
			segments.add(new Segment(vertices.get(i),vertices.get(next)));
		}
		return segments;
	}

	public ArrayList<Segment> Center_Segments() {
		ArrayList<Segment> segments = new ArrayList<Segment>();	
		Vec2 center = Center();
		for(int i = 0;i < vertices.size();i++) {
			segments.add(new Segment(vertices.get(i),center));
		}
		return segments;
	}

	public Vec2 Center() {
		double cx = 0;
		double cy = 0;
		double vSize = vertices.size();
		for(int i = 0;i < vSize;i++) {
			cx += vertices.get(i).getX();
			cy += vertices.get(i).getY();
		}
		return new Vec2(cx/vSize,cy/vSize);
	}

	public void rotate(double angle) {
		Vec2 center = Center();
		for(int i = 0;i < vertices.size();i++){
			Vec2 cPoint = vertices.get(i);
			double x = center.getX() + (cPoint.getX() - center.getX())*Math.cos(Math.toRadians(angle)) - (cPoint.getY() - center.getY())*Math.sin(Math.toRadians(angle));
			double y = center.getY() + (cPoint.getX() - center.getX())*Math.sin(Math.toRadians(angle)) + (cPoint.getY() - center.getY())*Math.cos(Math.toRadians(angle));
			cPoint.set(x,y);		
		}
	}

	public static Vec2 rotate(Vec2 center,Vec2 point,double angle) {
		double x = center.getX() + (point.getX() - center.getX())*Math.cos(Math.toRadians(angle)) - (point.getY() - center.getY())*Math.sin(Math.toRadians(angle));
		double y = center.getY() + (point.getX() - center.getX())*Math.sin(Math.toRadians(angle)) + (point.getY() - center.getY())*Math.cos(Math.toRadians(angle));
		return new Vec2(x,y);
	}

	public void bigger() {
		Vec2 center = Center();
		for(int i = 0;i < vertices.size();i++) {
			Vec2 expVec = new Vec2(center.getX() - vertices.get(i).getX(),center.getY() - vertices.get(i).getY()).normalize();
			vertices.get(i).sub(expVec);
		}
	}

	public void smaller() {
		Vec2 center = Center();
		for(int i = 0;i < vertices.size();i++) {
			Vec2 expVec = new Vec2(center.getX() - vertices.get(i).getX(),center.getY() - vertices.get(i).getY()).normalize();
			vertices.get(i).add(expVec);
		}
	}

	public ArrayList<Vec2> isConvex() {
		ArrayList<Vec2> result = new ArrayList<>();
		for(int i = 0;i < this.vertices.size();i++) {
			int next = (i == this.vertices.size() - 1) ? 0 : (i+1);
			Vec2 dir_norm = new Vec2(this.vertices.get(next).x - this.vertices.get(i).x,(this.vertices.get(next).y - this.vertices.get(i).y)).normalize();
			if(Collider2D.POLY_POINT(this,new Vec2(this.vertices.get(next).x + dir_norm.x, this.vertices.get(next).y + dir_norm.y))) {
				result.add(this.vertices.get(next));
			}
		}
		return result;
	}

	public ArrayList<Integer> isConvex_index() {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0;i < this.vertices.size();i++) {
			int next = (i == this.vertices.size() - 1) ? 0 : (i+1);
			Vec2 dir_norm = new Vec2(this.vertices.get(next).x - this.vertices.get(i).x,(this.vertices.get(next).y - this.vertices.get(i).y)).normalize();
			if(Collider2D.POLY_POINT(this,new Vec2(this.vertices.get(next).x + dir_norm.x, this.vertices.get(next).y + dir_norm.y))) {
				result.add(next);
			}
		}
		return result;
	}

	public ArrayList<Vec2> getSelfIntersections(){
		ArrayList<Vec2> intersections = new ArrayList<Vec2>();
		ArrayList<Segment> segments = Segments();
		for(int j = 0;j < segments.size();j++) {
			for(int k = j;k < segments.size();k++) {
				if(Collider2D.LINE_LINE_BOOL(segments.get(j), segments.get(k))) {
					Vec2 colData = Collider2D.LINE_LINE_INTER(segments.get(j), segments.get(k));
					boolean isValid = true;
					for(int v = 0;v < vertices.size();v++) {
						if(Collider2D.POINT_POINT(colData, vertices.get(v), 0.001)) {
							isValid = false;
							break;
						}
					}
					if(isValid) {
						intersections.add(colData);
					}
				}
			}
		}
		return intersections;
	}

	private double angleAt(Vec2 prev,Vec2 current,Vec2 next,ArrayList<Integer> indexList) {

		ArrayList<Vec2> partialArray = new ArrayList<>();

		for(int i = 0;i < indexList.size();i++){
			partialArray.add(this.vertices.get(indexList.get(i)));
		}

		Polygon2D partialPolygon = new Polygon2D(partialArray);


		Vec2 pPoint = prev;
		Vec2 cPoint = current;
		Vec2 nPoint = next;

		double a = Util.dist(pPoint, nPoint);
		double b = Util.dist(pPoint, cPoint);
		double c = Util.dist(cPoint, nPoint);

		int angle = 0;
		if(partialPolygon.isConvex().contains(current)) {
			angle = (360 - (int)(Math.toDegrees(Math.acos( (a*a - b*b - c*c) / (-2*b*c) ) ) ) );
		} else {
			angle = (int)(Math.toDegrees(Math.acos( (a*a - b*b - c*c) / (-2*b*c) ) ));
		}

		return angle;
	}

	private boolean isEar(Vec2 prev,Vec2 current,Vec2 next,ArrayList<Integer> indexList) {
		if(indexList.size() == 3)
			return true;
		if(angleAt(prev,current,next,indexList) > 180)
			return false;
		Segment d1 = new Segment(prev,next);

		double mid_x = (prev.x + next.x)/2;
		double mid_y = (prev.y + next.y)/2;

		ArrayList<Segment> segments = Segments();


		int count_2 = 0;
		if(!Collider2D.POLY_POINT(this,new Vec2(mid_x,mid_y)))
			return false;

		for(int i = 0;i < segments.size();i++) {
			if(Collider2D.LINE_LINE_BOOL(d1, segments.get(i))) {
				count_2++;
			}
		}

		if(count_2 > 4)
			return false;

		return true;
	}

	public ArrayList<Integer> triangulate() {
		ArrayList<Integer> index = new ArrayList<Integer>();
		ArrayList<Integer> output = new ArrayList<Integer>();

		for(int i = 0;i < this.vertices.size();i++) {
			index.add(i);
		}

		int security = 0;

		while(index.size() >= 2) {
			for(int i = 0;i < index.size();i++) {
				int prev = (i == 0) ? (index.size() - 1) : i - 1;
				int next = (i == index.size() - 1) ? 0 : i + 1;
				if(this.isEar(this.vertices.get(index.get(prev)),this.vertices.get(index.get(i)),this.vertices.get(index.get(next)),index)) {
					output.add(index.get(prev));
					output.add(index.get(i));
					output.add(index.get(next));
					index.remove(i);
				}
			}
			if(security > this.vertices.size()){
				//System.out.println("BUG : " + security + " : " + index.size());
				break;
			}
			security++;
		}

		return output;
	}

	public void setColor(int r,int g,int b){
		this.c = new Color(r,g,b);
	}

	public void renderConsoleInfo() {
		System.out.println("POINT : " + vertices + " VELOCITY : " + vel + " ACCELERATION : " + acc);
	}

	public void renderGraphicsInfo() {

	}

	public boolean isClicked(double x,double y) {
		if(Collider2D.POLY_POINT(this,new Vec2(x,y))) {
			renderConsoleInfo();
			return true;
		}
		return false;
	}
}
