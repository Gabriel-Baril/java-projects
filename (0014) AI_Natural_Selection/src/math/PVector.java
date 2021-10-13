package math;

public class PVector {
	public double x;
	public double y;

	public PVector(double x,double y){
		this.x = x;
		this.y = y;
	}

	public static PVector PerpenticularClockwise(PVector v){
		return new PVector(-v.y,v.x);
	}
	

	public static PVector PerpenticularCounterClockwise(PVector v){
		return new PVector(v.y,-v.x);
	}

	public PVector add(PVector p){
		this.x += p.x;
		this.y += p.y;
		
		return this;
	}

	public void sub(PVector p){
		this.x -= p.x;
		this.y -= p.y;
	}

	public PVector scale(double lambda){
		this.x *= lambda;
		this.y *= lambda;
		
		return this;
	}


	public void div(PVector p){
		this.x /= p.x;
		this.y /= p.y;
	}

	public double length(){
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}

	public PVector normalize(){
		double currentLength = this.length();
		this.x /= currentLength;
		this.y /= currentLength;
		
		return this;
	}
	
	public double dot(PVector v){
		return x*v.x + y*v.y;
	}
	
	public PVector copy(PVector p){
		PVector copy = p;
		return copy;
	}

	public String toString(){
		return "(" + x + "," + y + ")";	
	}
	
	public void set(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){return this.x;}
	public void setX(double x){this.x = x;}
	public double getY(){return this.y;}
	public void setY(double y){this.y = y;}
}
