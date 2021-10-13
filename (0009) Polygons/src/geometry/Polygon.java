package geometry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import toolkit.PVector;

import toolkit.Const;

public class Polygon {
	public boolean SHOW_PERIMETER = false;
	public boolean SHOW_AREA = false;
	public boolean SHOW_TOTAL_ANGLE = false;
	public boolean SHOW_NB_SIDES = false;
	public boolean SHOW_NB_POINTS = false;
	public boolean SHOW_IF_CONVEX = false;
	public boolean SHOW_IF_COMPLEX = false;

	//Polygon setting
	public boolean SHOW_POINT = true;
	public boolean SHOW_SEGMENT = true;
	public boolean SHOW_POINT_ANGLE = false;
	public boolean SHOW_SEGMENT_DISTANCE = false;
	public boolean SHOW_POINT_COORD = false;
	public boolean SHOW_SEGMENT_EQU = false;
	public boolean SHOW_SEGMENT_LINE = false;

	//Barycenter setting
	public boolean SHOW_BARYCENTER = true;
	public boolean SHOW_BARYCENTER_SEGMENT = false;
	public boolean SHOW_BARYCENTER_ANGLE = false;
	public boolean SHOW_BARYCENTER_SEGMENT_DISTANCE = false;
	public boolean SHOW_BARYCENTER_COORD = false;
	public boolean SHOW_BARYCENTER_SEGMENT_EQU = false;
	public boolean SHOW_BARYCENTER_SEGMENT_LINE = false;

	public ArrayList<Vertex> points = new ArrayList<Vertex>();
	public ArrayList<Segment> segments = new ArrayList<Segment>();
	public ArrayList<Segment> segments_brc = new ArrayList<Segment>();
	private Color color;
	private boolean focused = false;
	public String name;
	public String baryName;
	public boolean[] options = {
			SHOW_PERIMETER,
			SHOW_AREA,
			SHOW_TOTAL_ANGLE,
			SHOW_NB_SIDES,
			SHOW_NB_POINTS,
			SHOW_IF_CONVEX,
			SHOW_IF_COMPLEX,
			//
			SHOW_POINT,
			SHOW_SEGMENT,
			SHOW_POINT_ANGLE,
			SHOW_SEGMENT_DISTANCE,
			SHOW_POINT_COORD,
			SHOW_SEGMENT_EQU,
			SHOW_SEGMENT_LINE,
			//
			SHOW_BARYCENTER,
			SHOW_BARYCENTER_SEGMENT,
			SHOW_BARYCENTER_ANGLE,
			SHOW_BARYCENTER_SEGMENT_DISTANCE,
			SHOW_BARYCENTER_COORD,
			SHOW_BARYCENTER_SEGMENT_EQU,
			SHOW_BARYCENTER_SEGMENT_LINE
	};

	public void updateOption() {
		options[0] = SHOW_PERIMETER;
		options[1] = SHOW_AREA;
		options[2] = SHOW_TOTAL_ANGLE;
		options[3] = SHOW_NB_SIDES;
		options[4] = SHOW_NB_POINTS;
		options[5] = SHOW_IF_CONVEX;
		options[6] = SHOW_IF_COMPLEX;
		//
		options[7] = SHOW_POINT;
		options[8] = SHOW_SEGMENT;
		options[9] = SHOW_POINT_ANGLE;
		options[10] = SHOW_SEGMENT_DISTANCE;
		options[11] = SHOW_POINT_COORD;
		options[12] = SHOW_SEGMENT_EQU;
		options[13] = SHOW_SEGMENT_LINE;
		//
		options[14] = SHOW_BARYCENTER;
		options[15] = SHOW_BARYCENTER_SEGMENT;
		options[16] = SHOW_BARYCENTER_ANGLE;
		options[17] = SHOW_BARYCENTER_SEGMENT_DISTANCE;
		options[18] = SHOW_BARYCENTER_COORD;
		options[19] = SHOW_BARYCENTER_SEGMENT_EQU;
		options[20] = SHOW_BARYCENTER_SEGMENT_LINE;
	}

	public Polygon(Color c,String name,String baryName){
		Const.polygons.add(this);
		this.setColor(c);
		this.name = name;
		this.baryName = baryName;
	}

	public void update(){
		if(points.size() < 3){
			Const.polygons.remove(this);
		}
	}

	// Fait une réflexion à partir de l'axe des Y
	public void reflectY() {
		double centerX = getBarycenter().pos.x;
		for(int i = 0;i < points.size();i++) {
			double diffX = centerX - points.get(i).pos.x;
			points.get(i).pos.x += diffX*2;
		}
	}

	// Fait une réflexion à partir de l'axe des X
	public void reflectX() {
		double centerY = getBarycenter().pos.y;
		for(int i = 0;i < points.size();i++) {
			double diffY = centerY - points.get(i).pos.y;
			points.get(i).pos.y += diffY*2;
		}
	}

	// Fait une homethétie réduite du polygone à partir du barycentre
	public void smaller(){
		Vertex oldBary = getBarycenter();
		for(int i = 0;i < points.size();i++) {
			PVector p = new PVector(oldBary.pos.x - points.get(i).pos.x,oldBary.pos.y - points.get(i).pos.y);
			p.normalize();
			points.get(i).pos.add(p);
		}
	}

	// Fait une homethétie agrandit du polygone à partir du barycentre
	public void bigger() {
		Vertex oldBary = getBarycenter();
		for(int i = 0;i < points.size();i++) {
			PVector p = new PVector(oldBary.pos.x - points.get(i).pos.x,oldBary.pos.y - points.get(i).pos.y);
			p.normalize();
			points.get(i).pos.sub(p);
		}
	}

	// Permet au polygone de faire une translation
	public void move(PVector v){
		for(int i = 0;i < points.size();i++){
			points.get(i).pos.add(v);
		}
		getBarycenter();
	}

	// Permet au polygone de faire une rotation
	public void rotate(double angle){
		Vertex center = getBarycenter();
		for(int i = 0;i < points.size();i++) {
			points.get(i).pos.x = (double)((center.pos.x + ((points.get(i).pos.x - center.pos.x) * Math.cos(Math.toRadians(angle))) - ((points.get(i).pos.y - center.pos.y) * Math.sin(Math.toRadians(angle))))); 
			points.get(i).pos.y = (double)((center.pos.y + ((points.get(i).pos.x - center.pos.x) * Math.sin(Math.toRadians(angle))) + ((points.get(i).pos.y - center.pos.y) * Math.cos(Math.toRadians(angle)))));
		}
	}

	// Trouve le périmètre du polygone
	public double getPerimeter(){
		double perimeter = 0;
		for(int i = 0;i < segments.size();i++){
			perimeter += segments.get(i).dist();
		}
		return perimeter;
	}

	// Trouve l'aire du polygone (si convexe)
	public double getArea(){
		double area = 0;
		double heron = 0;

		Segment seg1;
		Segment seg2;
		Segment seg3;
		double p = 0;

		for(int i = 0;i < points.size();i++){
			if(i != points.size() - 1){
				seg1 = new Segment(points.get(i),points.get(i + 1));
				seg2 = new Segment(points.get(i),getBarycenter());
				seg3 = new Segment(points.get(i + 1),getBarycenter());
				p = (seg1.dist() + seg2.dist() + seg3.dist())/2;
				heron = Math.sqrt(p*(p - seg1.dist())*(p - seg2.dist())*(p - seg3.dist()));
				area += heron;
			}else{
				seg1 = new Segment(points.get(i),points.get(0));
				seg2 = new Segment(points.get(i),getBarycenter());
				seg3 = new Segment(points.get(0),getBarycenter());
				p = (seg1.dist() + seg2.dist() + seg3.dist())/2;
				heron = Math.sqrt(p*(p - seg1.dist())*(p - seg2.dist())*(p - seg3.dist()));
				area += heron;
			}
		}
		return area;
	}

	// Trouve l'angle à une vertex donné
	public double getAngleOf(Vertex v){
		double mAC;
		double mAB;
		double mBC;
		if(points.indexOf(v) == 0){
			mAB = Const.dist(v,points.get(points.size() - 1));
			mBC = Const.dist(v,points.get(points.indexOf(v) + 1));
			mAC = Const.dist(points.get(points.size() - 1),points.get(points.indexOf(v) + 1));
		}else if(points.indexOf(v) == points.size() - 1){
			mAB = Const.dist(v,points.get(points.size() - 2));
			mBC = Const.dist(v,points.get(0));
			mAC = Const.dist(points.get(points.size() - 2), points.get(0));
		}else{
			mAB = Const.dist(v,points.get(points.indexOf(v) - 1));
			mBC = Const.dist(v,points.get(points.indexOf(v) + 1));
			mAC = Const.dist(points.get(points.indexOf(v) - 1),points.get(points.indexOf(v) + 1));
		}
		return Math.toDegrees(Math.acos( ((mBC*mBC) + (mAB*mAB) - (mAC*mAC) )/(2*mBC*mAB)) );
	}

	// Trouve le barycentre du polygone
	public Vertex getBarycenter(){
		Vertex baryCenter;
		double x = 0;
		double y = 0;
		for(int i = 0;i < points.size();i++){
			x += points.get(i).pos.x;
			y += points.get(i).pos.y;
		}
		baryCenter = new Vertex(x/points.size(),y/points.size(),baryName);
		return baryCenter;
	}

	public double getMaxX(){
		double maxX = points.get(0).pos.x;
		for(int i = 1;i < points.size();i++){
			if(points.get(i).pos.x > maxX){
				maxX = points.get(i).pos.x;
			}
		}
		return maxX;
	}

	public double getMinX(){
		double minX = points.get(0).pos.x;
		for(int i = 1;i < points.size();i++){
			if(points.get(i).pos.x < minX){
				minX = points.get(i).pos.x;
			}
		}
		return minX;
	}


	public double getMaxY(){
		double maxY = points.get(0).pos.y;
		for(int i = 1;i < points.size();i++){
			if(points.get(i).pos.y > maxY){
				maxY = points.get(i).pos.y;
			}
		}
		return maxY;
	}

	public double getMinY(){
		double minY = points.get(0).pos.y;
		for(int i = 1;i < points.size();i++){
			if(points.get(i).pos.y < minY){
				minY = points.get(i).pos.y;
			}
		}
		return minY;
	}

	public boolean isConvex(){
		return true;
	}

	public void decompose(){
		if(isConvex()){

		}else{

		}
	}

	public void randomize(){
		for(int i = 0;i < points.size();i++){
			double randX = (int)(Math.random() * (Const.WIDTH - 20));
			double randY = (int)(Math.random() * (Const.HEIGHT - 20));
			points.get(i).pos.x = randX;
			points.get(i).pos.y = randY;
		}
	}

	public void mutate(double mutateRate){
		for(int i = 0;i < points.size();i++){
			double randX = points.get(i).pos.x + (-mutateRate + (int)(Math.random() * 2*mutateRate));
			double randY = points.get(i).pos.y + (-mutateRate + (int)(Math.random() * 2*mutateRate));
			points.get(i).pos.x = randX;
			points.get(i).pos.y = randY;
		}
	}

	public ArrayList<PVector> getPerpAxes() {
		try{
			ArrayList<PVector> perpEdge = new ArrayList<PVector>();
			for(int i = 0;i < segments.size();i++){
				perpEdge.add(segments.get(i).getPerp());
			}
			return perpEdge;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	public Projection project(PVector axis){
		axis.normalize();

		double min = axis.dot(points.get(0).pos);
		double max = min;

		for(int i = 1;i < points.size();i++){
			double p = axis.dot(points.get(i).pos);
			if(p < min){
				min = p;
			}else if(p > max){
				max = p;
			}
		}

		return new Projection(min,max);
	}

	// polygon-point
	public boolean POLYPOINT_COLLIDING(PVector point) {
		boolean collision = false;
		int next = 0;
		for(int i = 0;i < points.size();i++) {
			next = i + 1;
			if(next == points.size()) next = 0;

			PVector vc = points.get(i).pos;
			PVector vn = points.get(next).pos;

			if(((vc.y > point.y && vn.y < point.y) || (vc.y < point.y && vn.y > point.y)) && (point.x < (vn.x-vc.x)*(point.y-vc.y) / (vn.y-vc.y)+vc.x)){
				collision = !collision;
			}
		}
		return collision;
	}

	// polygon-line
	public boolean POLYLINE_COLLIDING(Segment seg){
		int next = 0;
		for(int i = 0;i < points.size();i++){
			next = i + 1;
			if(next == points.size()) next = 0;
			if(LINELINE_COLLIDING(new Segment(points.get(i),points.get(next)),seg)){
				return true;
			}
		}
		return false;
	}

	// line-line
	public boolean LINELINE_COLLIDING(Segment seg1,Segment seg2){
		double uA = ((seg2.end.pos.x - seg2.start.pos.x) * (seg1.start.pos.y - seg2.start.pos.y) - (seg2.end.pos.y - seg2.start.pos.y) * (seg1.start.pos.x - seg2.start.pos.x)) / ((seg2.end.pos.y - seg2.start.pos.y) * (seg1.end.pos.x - seg1.start.pos.x) - (seg2.end.pos.x - seg2.start.pos.x) * (seg1.end.pos.y - seg1.start.pos.y));
		double uB = ((seg1.end.pos.x - seg1.start.pos.x) * (seg1.start.pos.y - seg2.start.pos.y) - (seg1.end.pos.y - seg1.start.pos.y) * (seg1.start.pos.x - seg2.start.pos.x)) / ((seg2.end.pos.y - seg2.start.pos.y) * (seg1.end.pos.x - seg1.start.pos.x) - (seg2.end.pos.x - seg2.start.pos.x) * (seg1.end.pos.y - seg1.start.pos.y));
		if(uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1){
			return true;
		}
		return false;
	}


	public boolean PERFECT_COLLIDING(Polygon shape){
		if(AABB_COLLIDING(shape)){
			for(int i = 0;i < shape.points.size();i++){
				if(POLYPOINT_COLLIDING(shape.points.get(i).pos) || POLYLINE_COLLIDING(shape.segments.get(i))){
					return true;
				}
			}
		}
		return false;
	}

	// Aligned axis box-box
	public boolean AABB_COLLIDING(Polygon shape){
		if(getMinX() < shape.getMaxX() &&
				getMaxX() > shape.getMinX() &&
				getMinY() < shape.getMaxY() &&
				getMaxY() > shape.getMinY()){
			return true;
		}
		return false;
	}

	public boolean SAT_COLLIDING(Polygon shape){
		if(AABB_COLLIDING(shape)){
			try{
				ArrayList<PVector> perpAxesShape1 = getPerpAxes();
				ArrayList<PVector> perpAxesShape2 = shape.getPerpAxes();

				for(int i = 0;i < perpAxesShape1.size();i++){
					PVector axis = perpAxesShape1.get(i);

					Projection p1 = project(axis);
					Projection p2 = shape.project(axis);

					if(!p1.overlap(p2)){
						return false;
					}
				}

				for(int i = 0;i < perpAxesShape2.size();i++){
					PVector axis = perpAxesShape2.get(i);

					Projection p1 = project(axis);
					Projection p2 = shape.project(axis);

					if(!p1.overlap(p2)){
						return false;
					}
				}

				return true;
			}catch(Exception e){e.printStackTrace();}
		}
		return false;
	}

	public static void COLLISION_POLYGON(Polygon shape1,Polygon shape2){
		try{
			System.out.println("COLLIDE : " + shape1.name +  " -- " + shape2.name + "|" + "SAT : " + shape1.PERFECT_COLLIDING(shape2) + "|AABB : " + shape1.AABB_COLLIDING(shape2));
		}catch(Exception e){e.printStackTrace();}
	}

	// Connecte les points extérieurs du polygone
	public void connect(){
		for(int i = 0;i < points.size();i++){
			segments.add(points.get(i).connectTo((i == points.size() - 1) ? points.get(0) : points.get(i + 1)));
			segments_brc.add(points.get(i).connectTo(getBarycenter()));
		}
	}

	// Permet de rajouter un point au polygone
	public void add(Vertex v){
		points.add(v);
		setupSegment();
	}

	// Dessine le polygone
	public void fillPolygon(Graphics2D g2d) {
		int[] xPoints = new int[points.size()];
		int[] yPoints = new int[points.size()];
		g2d.setColor(getColor());
		for(int j = 0;j < points.size();j++){
			xPoints[j] = (int)points.get(j).pos.x;
			yPoints[j] = (int)points.get(j).pos.y;
		}
		g2d.fillPolygon(xPoints, yPoints, points.size());
		g2d.setColor(Color.BLACK);
	}

	public void drawSATInf(Graphics2D g2d){
		for(int j = 0;j < segments.size();j++){
			g2d.setColor(new Color(0,0,0,200));

			g2d.drawLine((int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2, (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2,(int)-segments.get(j).getPerp().normalize().scale(10).x + (int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2,(int)-segments.get(j).getPerp().normalize().scale(10).y + (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2);
			g2d.drawLine((int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2, (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2,(int)segments.get(j).getPerp().normalize().scale(10).x + (int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2,(int)segments.get(j).getPerp().normalize().scale(10).y + (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2);

			g2d.setColor(new Color(80,80,80,100));

			g2d.drawLine((int)segments.get(j).start.pos.x, (int)segments.get(j).start.pos.y,(int)-segments.get(j).getPerp().normalize().scale(40).x + (int)segments.get(j).start.pos.x,(int)-segments.get(j).getPerp().normalize().scale(40).y + (int)segments.get(j).start.pos.y);
			g2d.drawLine((int)segments.get(j).start.pos.x, (int)segments.get(j).start.pos.y,(int)segments.get(j).getPerp().normalize().scale(40).x + (int)segments.get(j).start.pos.x,(int)segments.get(j).getPerp().normalize().scale(40).y + (int)segments.get(j).start.pos.y);

			g2d.drawLine((int)segments.get(j).end.pos.x, (int)segments.get(j).end.pos.y,(int)-segments.get(j).getPerp().normalize().scale(40).x + (int)segments.get(j).end.pos.x,(int)-segments.get(j).getPerp().normalize().scale(40).y + (int)segments.get(j).end.pos.y);
			g2d.drawLine((int)segments.get(j).end.pos.x, (int)segments.get(j).end.pos.y,(int)segments.get(j).getPerp().normalize().scale(40).x + (int)segments.get(j).end.pos.x,(int)segments.get(j).getPerp().normalize().scale(40).y + (int)segments.get(j).end.pos.y);

			g2d.drawLine((int)getMinX(), (int)getMinY(), (int)getMaxX(), (int)getMinY());
			g2d.drawLine((int)getMaxX(), (int)getMinY(), (int)getMaxX(), (int)getMaxY());
			g2d.drawLine((int)getMaxX(), (int)getMaxY(), (int)getMinX(), (int)getMaxY());
			g2d.drawLine((int)getMinX(), (int)getMaxY(), (int)getMinX(), (int)getMinY());
			g2d.setColor(Color.BLACK);
		}
	}

	// Information des points
	public void drawPointInf(Graphics2D g2d) {
		for(int j = 0;j < points.size();j++){
			if(SHOW_POINT){
				g2d.fillOval((int)points.get(j).pos.x,(int)points.get(j).pos.y, Const.P_WEIGHT, Const.P_WEIGHT);
			}
			if(SHOW_POINT_COORD){
				g2d.drawString(points.get(j).name + "(" + (int)points.get(j).pos.x + "," + (int)points.get(j).pos.y + ")", (int)points.get(j).pos.x, (int)points.get(j).pos.y);
			}
			if(SHOW_POINT_ANGLE){
				g2d.drawString((int)getAngleOf(points.get(j)) + "°",(int)points.get(j).pos.x + 5,(int)points.get(j).pos.y + 10);	
			}
		}
	}

	// Information des segments extérieurs
	public void drawSegmentInf(Graphics2D g2d) {
		for(int j = 0;j < segments.size();j++){
			if(SHOW_SEGMENT){
				g2d.drawLine((int)segments.get(j).start.pos.x, (int)segments.get(j).start.pos.y, (int)segments.get(j).end.pos.x, (int)segments.get(j).end.pos.y);
			}
			if(SHOW_SEGMENT_EQU){
				g2d.drawString(segments.get(j).getEqu().showRegle(), (int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2, (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2);
			}
			if(SHOW_SEGMENT_DISTANCE){
				g2d.drawString(String.valueOf(String.format("%.1f", segments.get(j).dist())), (int)(segments.get(j).start.pos.x + segments.get(j).end.pos.x)/2, (int)(segments.get(j).start.pos.y + segments.get(j).end.pos.y)/2 + 10);
			}
			if(SHOW_SEGMENT_LINE){
				g2d.drawLine((int)segments.get(j).findLine().start.pos.x,(int) segments.get(j).findLine().start.pos.y,(int) segments.get(j).findLine().end.pos.x,(int) segments.get(j).findLine().end.pos.y);
			}
		}
	}

	// Information des segments du barycentre
	public void drawSegmentBrcInf(Graphics2D g2d) {
		for(int j = 0;j < segments_brc.size();j++){
			if(SHOW_BARYCENTER_SEGMENT){
				g2d.drawLine((int)segments_brc.get(j).start.pos.x, (int)segments_brc.get(j).start.pos.y, (int)getBarycenter().pos.x, (int)getBarycenter().pos.y);
			}
			if(SHOW_BARYCENTER_SEGMENT_EQU){
				g2d.drawString(segments_brc.get(j).getEqu().showRegle(), (int)(segments_brc.get(j).start.pos.x + segments_brc.get(j).end.pos.x)/2, (int)(segments_brc.get(j).start.pos.y + segments_brc.get(j).end.pos.y)/2);
			}
			if(SHOW_BARYCENTER_SEGMENT_DISTANCE){
				g2d.drawString("" + (int)segments_brc.get(j).dist(), (int)(segments_brc.get(j).start.pos.x + segments_brc.get(j).end.pos.x)/2, (int)(segments_brc.get(j).start.pos.y + segments_brc.get(j).end.pos.y)/2 + 10);
			}
			if(SHOW_BARYCENTER_SEGMENT_LINE){
				g2d.drawLine((int)segments_brc.get(j).findLine().start.pos.x,(int) segments_brc.get(j).findLine().start.pos.y,(int) segments_brc.get(j).findLine().end.pos.x,(int) segments_brc.get(j).findLine().end.pos.y);	
			}
		}
	}

	// Information générale du polygone
	public void drawGeneralInf(Graphics2D g2d) {
		if(SHOW_BARYCENTER){
			g2d.fillOval((int)getBarycenter().pos.x,(int) getBarycenter().pos.y,(int) Const.P_WEIGHT,(int) Const.P_WEIGHT);
		}
		if(SHOW_BARYCENTER_COORD){
			g2d.drawString("(" + (int)getBarycenter().pos.x + "," + (int)getBarycenter().pos.y + ")", (int)getBarycenter().pos.x, (int)getBarycenter().pos.y);
		}
		if(SHOW_PERIMETER){
			g2d.drawString("P = " + (int)getPerimeter() ,(int)getBarycenter().pos.x,(int)getBarycenter().pos.y + 10);
		}
		if(SHOW_AREA){
			g2d.drawString("A = " + (int)getArea(), (int)getBarycenter().pos.x,(int)getBarycenter().pos.y + 20);
		}
	}

	public void checkPointDragged(MouseEvent e){
		boolean selectioned = false;
		for(int j = 0;j < points.size();j++){
			if(points.get(j).collideWithMouse(e.getX() - Const.LEFT_MARGIN,e.getY() - Const.UP_MARGIN) && !selectioned && isFocused()){
				points.get(j).pos.x = e.getX() - Const.LEFT_MARGIN;
				points.get(j).pos.y = e.getY() - Const.UP_MARGIN;
				setupSegment();
				selectioned = true;
			}
		}
		selectioned = false;
	}

	public void checkPointDestroyed(MouseEvent e) {
		for(int j = 0;j < points.size();j++){
			if(points.get(j).collideWithMouse(e.getX() - Const.LEFT_MARGIN,e.getY() - Const.UP_MARGIN) && MouseEvent.BUTTON3 == e.getButton()){
				points.remove(j);
				setupSegment();
			}
		}
	}

	public void checkBarycenterClicked(MouseEvent e){
		if(getBarycenter().collideWithMouse(e.getX() - Const.LEFT_MARGIN,e.getY() - Const.UP_MARGIN)){
			setFocused(true);
		}else{
			setFocused(false);
		}
	}

	public void setupSegment(){
		segments.clear();
		segments_brc.clear();
		connect();
	}

	public boolean isFocused() {return focused;}
	public void setFocused(boolean focused) {this.focused = focused;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
}
