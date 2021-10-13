package main;

public class Ball {
	private float x;
	private float y;
	private float width;
	private float height;
	private float speedX;
	private float speedY;
	
	public Ball(float x,float y,float width,float height,float speedX,float speedY){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setSpeedX(speedX);
		this.setSpeedY(speedY);
	}
	
	public void update(){
		this.x += this.speedX;
		this.y += this.speedY; 
	}
	
	public void collideEdge(){
		if(this.x  < 0){
			this.speedX = -speedX;
		}
		
		if(this.x + this.width + 7> Main.WIDTH){
			this.speedX = -speedX;
		}
		
		if(this.y < 0){
			this.speedY = -speedY;
		}
		
		if(this.y + 40 > Main.HEIGHT){
			this.speedY = -speedY;
		}
	}
	
	public void collidePlayerPalet(){
		if(this.x < Main.playerPalet.getX() + Main.playerPalet.getWidth() &&
		   this.x + this.width > Main.playerPalet.getX() &&
		   this.y < Main.playerPalet.getY() + Main.playerPalet.getHeight() &&
		   this.y + this.height > Main.playerPalet.getY()){
			this.speedX = -speedX;
		}
	}
	
	public void collideIAPalet(){
		if(this.x < Main.IAPalet.getX() + Main.IAPalet.getWidth() &&
		   this.x + this.width > Main.IAPalet.getX() &&
		   this.y < Main.IAPalet.getY() + Main.IAPalet.getHeight() &&
		   this.y + this.height > Main.IAPalet.getY()){
			this.speedX = -speedX;
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
	public float getSpeedX() {return speedX;}
	public void setSpeedX(float speedX) {this.speedX = speedX;}
	public float getSpeedY() {return speedY;}
	public void setSpeedY(float speedY) {this.speedY = speedY;}
}
