package main;

public class Vector {
	private float x;
	private float y;
	
	public Vector(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector v){
		this.x += v.x;
		this.y += v.y;
	}
	
	public void sub(Vector v){
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public void scale(float f){
		this.x *= 0;
		this.y *= 0;
	}
	
	public float length(){
		return (float) Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public void normalize(){
		float currentLength = this.length();
		this.x /= currentLength;
		this.y /= currentLength;
	}
	
	public void set(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){return this.x;}
	public float getY(){return this.y;}
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
}
