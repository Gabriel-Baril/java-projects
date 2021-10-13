package main;

public class Palet {
	private float x;
	private float y;
	private int w;
	private int h;
	private float velX = 0;
	
	public Palet(float x,float y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void update(){
		this.x += velX;
	}
	
	public void collideEdge(){
		if(this.x + this.w > Main.WIDTH){
			this.x = Main.WIDTH - this.w;
			this.velX = 0;
		}
		if(this.x < 0){
			this.x = 0;
		}
	}
	
	public void collideBall(){
		if(this.x < Main.ball.getX() + Main.ball.getWidth() &&
		   this.x + this.w > Main.ball.getX() &&
		   this.y < Main.ball.getY() + Main.ball.getHeight() &&
		   this.y + this.h > Main.ball.getY()){
			Main.ball.setVelY(-Main.ball.getVelY());
		}
	}
	
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public void setWidth(int w){this.w = w;}
	public void setHeight(int h){this.h = h;}
	public void setVelX(float velX){this.velX = velX;};
	public float getX(){return this.x;}
	public float getY(){return this.y;}
	public int getWidth(){return this.w;}
	public int getHeight(){return this.h;}
	public float getVelX(){return this.velX;}
}
