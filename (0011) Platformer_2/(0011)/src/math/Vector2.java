package math;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector2 PerpenticularClockwise(Vector2 v){
		return new Vector2(-v.y,v.x);
	}
	
	public static Vector2 PerpenticularCounterClockwise(Vector2 v){
		return new Vector2(v.y,-v.x);
	}
	
	public double Length(){
		return Math.sqrt(x*x + y*y);
	}
	
	public double LengthSquared(){
		return x*x + y*y;
	}
	
	public double Distance(Vector2 v){
		return Math.sqrt( ((x - v.x)*(x - v.x)) + ((y - v.y)*(y - v.y)));
	}

	public double DistanceSquared(Vector2 v){
		return ((x - v.x)*(x - v.x)) + ((y - v.y)*(y - v.y));
	}
	
	public double Dot(Vector2 v){
		return x*v.x + y*v.y;
	}
	
	public double Cross(Vector2 v){
		return x*v.y + y*v.x;
	}
	
	public Vector2 Normalize(){
		if(Length() != 0){
			double currentLength = Length();
			x /= currentLength;
			y /= currentLength;
			return this;
		}
		return null;
	}
	
	public Vector2 add(Vector2 v){
		x += v.x;
		y += v.y;
		
		return this;
	}
	
	public Vector2 sub(Vector2 v){
		x -= v.x;
		y -= v.y;
		
		return this;
	}
	
	public Vector2 scale(double lambda){
		x *= lambda;
		y *= lambda;
		
		return this;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
}
