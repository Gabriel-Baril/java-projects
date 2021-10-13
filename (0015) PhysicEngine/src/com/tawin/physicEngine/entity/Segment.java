package com.tawin.physicEngine.entity;

import com.tawin.physicEngine.toolKit.Vec2;

public class Segment {
	private Vec2 start;
	private Vec2 end;
	
	public Segment(Vec2 start,Vec2 end) {
		this.start = start;
		this.end = end;
	}
	
	public double getMinX(){
		if(start.x < end.x)
			return start.x;
		else
			return end.x;
	}
	
	public double getMaxX(){
		if(start.x > end.x)
			return start.x;
		else
			return end.x;
	}
	
	public double getMinY(){
		if(start.y < end.y)
			return start.y;
		else
			return end.y;
	}
	
	public double getMaxY(){
		if(start.y > end.y)
			return start.y;
		else
			return end.y;
	}
	
	public double dist() {
		return Math.sqrt((end.getX() - start.getX())*(end.getX() - start.getX()) + (end.getY() - start.getY())*(end.getY() - start.getY()));
	}
	
	public Vec2 midPoint() {
		return new Vec2((start.getX() + end.getX())/2,(start.getY() + end.getY())/2);
	}
	
	public Vec2 getVector(){
		return new Vec2(start.getX() - end.getX(),start.getY() - end.getY());
	}
	
	public Vec2 getPerp(){
		return Vec2.PerpenticularClockwise(getVector());
	}
	
	public Vec2 getStart() {return start;}
	public void setStart(Vec2 start) {this.start = start;}
	public Vec2 getEnd() {return end;}
	public void setEnd(Vec2 end) {this.end = end;}

	public String toString() {
		return "Start : [" + (int)start.getX() + "," + (int)start.getY() + "]" + "End : [" + (int)end.getX() + "," + (int)end.getY() + "]";
	}
}
