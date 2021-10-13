package Math;

public class PVector {
	private float x;
	private float y;
	
	public PVector(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public void add(PVector p){
		this.x += p.x;
		this.y += p.y;
	}
	
	public void sub(PVector p){
		this.x -= p.x;
		this.y -= p.y;
	}
	
	public void mult(int multiplier){
		this.x *= multiplier;
		this.y *= multiplier;
	}
	
	public float getX(){return this.x;}
	public float getY(){return this.y;}
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
}
