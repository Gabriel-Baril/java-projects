package main;

public class Ball {
	private float x;
	private float y;
	private int w;
	private int h;
	private float velX = (float)(-5 + Math.random() * 5);
	private float velY = (float)(-5 + Math.random() * 5);

	public Ball(float x,float y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void update(){
		this.x += velX;
		this.y += velY;
	}

	public void collideEdge(){
		if(this.x < 0){
			this.x = 0;
			this.velX = -this.velX;
		}
		if(this.x + this.w > Main.WIDTH - 8){
			this.x = (Main.WIDTH - 8) - this.w;
			this.velX = -this.velX;
		}
		if(this.y < 0){
			this.y = 0;
			this.velY = -this.velY;
		}
		if(this.y + this.h > Main.HEIGHT - 28){
			this.y = (Main.HEIGHT - 28) - this.h;
			this.velY = -this.velY;
		}
	}

	public void collideBricks(){
		for(int i = 0;i < Main.bricks.size();i++){
			if(this.x < Main.bricks.get(i).getX() + Main.bricks.get(i).getWidth() &&
					this.x + this.w > Main.bricks.get(i).getX() &&
					this.y < Main.bricks.get(i).getY() + Main.bricks.get(i).getHeight() &&
					this.y + this.h > Main.bricks.get(i).getY()){
				Main.bricks.remove(i);
				Main.ball.setVelY(-Main.ball.getVelY());
			}
		}
	}

	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public void setWidth(int w){this.w = w;}
	public void setHeight(int h){this.h = h;}
	public void setVelX(float velX){this.velX = velX;};
	public void setVelY(float velY){this.velY = velY;};
	public float getX(){return this.x;}
	public float getY(){return this.y;}
	public int getWidth(){return this.w;}
	public int getHeight(){return this.h;}
	public float getVelX(){return this.velX;}
	public float getVelY(){return this.velY;}
}
