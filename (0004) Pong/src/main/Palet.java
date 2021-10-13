package main;

public class Palet {
	private float x;
	private float y;
	private float width;
	private float height;
	private float speed;

	public Palet(float x,float y,float width,float height,float speed){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.speed = speed;
	}

	public void activeIA(){
		if(this.y + this.height/2 < Main.ball.getY() && Main.ball.getX() > Main.WIDTH/2){
			this.y += this.speed;
		}
		if(this.y + this.height/2 > Main.ball.getY() && Main.ball.getX() > Main.WIDTH/2){
			this.y -= this.speed;
		}
	}

	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	public float getWidth() {return width;}
	public void setWidth(float width) {this.width = width;}
	public float getHeight() {return height;}
	public void setHeight(float height) {this.height = height;}
	public float getSpeed() {return speed;}
	public void setSpeed(float speed) {this.speed = speed;}
}
