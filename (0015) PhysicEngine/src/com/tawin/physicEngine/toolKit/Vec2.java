package com.tawin.physicEngine.toolKit;

public class Vec2 {
	public double x;
	public double y;


	public static Vec2 PerpenticularClockwise(Vec2 v){
		return new Vec2(-v.y,v.x);
	}
	

	public static Vec2 PerpenticularCounterClockwise(Vec2 v){
		return new Vec2(v.y,-v.x);
	}
	
	public Vec2(Vec2 copy) {
		this.x = copy.x;
		this.y = copy.y;
	}
	
	public Vec2(double x,double y){
		this.x = x;
		this.y = y;
	}

	public Vec2 add(Vec2 p){
		this.x += p.x;
		this.y += p.y;
		
		return this;
	}

	public Vec2 sub(Vec2 p){
		this.x -= p.x;
		this.y -= p.y;
		
		return this;
	}

	public Vec2 scale(double lambda){
		this.x *= lambda;
		this.y *= lambda;
		
		return this;
	}


	public void div(Vec2 p){
		this.x /= p.x;
		this.y /= p.y;
	}

	public double length(){
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public Vec2 setMag(double mag) {
		double length = length();
		this.x = this.x * mag/length;
		this.y = this.y * mag/length;
		
		return this;
	}

	public Vec2 normalize(){
		double currentLength = this.length();
		this.x /= currentLength;
		this.y /= currentLength;
		
		return this;
	}
	
	public double dot(Vec2 v){
		return x*v.x + y*v.y;
	}
	
	public boolean equals(Vec2 v) {
		if(this.x == v.x && this.y == v.y)
			return true;
		return false;
	}
	
	public static Vec2 copy(Vec2 p){
		Vec2 copy = p;
		return copy;
	}
	
	
	// SETTER/GETTER
	public void set(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	// -------------
	
	
	public String toString(){
		return "(" + String.format("%.2f", x) + ";" + String.format("%.2f", y) + ")";	
	}
}
