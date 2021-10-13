package player;

import java.awt.Color;

import graphic.Animation;
import main.Const;
import math.Vector2;
import platform.Rectangle;

public class Player extends Rectangle{
	private static Player instance;
	private int speed = 4;
	private boolean jumping = false;
	private boolean goLeft = false;
	private boolean goRight = false;
	
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private Animation jumpAnimation;
	private Animation fallAnimation;

	private Player(double x,double y,int width,int height,Animation[] animations) {
		super(x,y,width,height);
		this.walkLeftAnimation = animations[0];
		this.walkRightAnimation = animations[1];
		this.jumpAnimation = animations[2];
		this.fallAnimation = animations[3];
	}
	
	private Player(double x,double y,int width,int height) {
		super(x,y,width,height);
	}
	
	private Player(double x,double y,int width,int height,Color color) {
		super(x,y,width,height,color);
	}

	public void move(int move){
		this.vel.setX(move);
	}

	public void jump(){
		vel.setY(-5);
	}
	
	public void kill(){
		new ParticleSystem(Player.getInstance().pos.x - Player.getInstance().width/2, Player.getInstance().pos.y - Player.getInstance().height/3, 50, 2);
		instance = null;
	}

	public void update() {
		render();
		updatePos();
		applyForce(new Vector2(0,0.1));
		collideBorder();
		collidePlatform();
		collideProjectiles();
	}

	public void updatePos(){
		pos.add(vel);
		vel.add(acc);
	}

	public void applyForce(Vector2 force) {
		if(acc.y > 0.4){
			acc.y = 0.1;
		}
		acc.add(force);
	}

	public void collideBorder(){
		if(this.pos.x + this.width > Const.WIDTH - Const.LEFT_MARGIN){
			this.pos.x = Const.WIDTH - this.width - Const.LEFT_MARGIN;
			this.vel.x = 0;
			this.setJumping(false);
		}
		if(this.pos.x < 0){
			this.pos.x = 0;
			this.vel.x = 0;
			this.setJumping(false);
		}
		if(this.pos.y < 0){
			this.pos.y = 0;
			this.vel.y = 0;
			this.setJumping(false);
		}
		if(this.pos.y + this.height > Const.HEIGHT - Const.UP_MARGIN){
			this.pos.y = Const.HEIGHT - this.height - Const.UP_MARGIN;
			this.vel.y = 0;
			this.setJumping(false);
		}
	}


	public void collidePlatform(){
		for(int i = 0;i < Const.platforms.size();i++){
			if(checkCollision(Const.platforms.get(i))){
				this.setJumping(false);
				if(this.pos.x > Const.platforms.get(i).pos.x + Const.platforms.get(i).getWidth() - 5){
					this.pos.x = Const.platforms.get(i).pos.x + Const.platforms.get(i).getWidth();
					this.vel.x = 0;
					return;
				}
				if(this.pos.x + this.width > Const.platforms.get(i).pos.x &&
						this.pos.x < Const.platforms.get(i).pos.x + Const.platforms.get(i).getWidth() &&
						this.pos.y + this.height < Const.platforms.get(i).pos.y + 20){
					this.pos.y = Const.platforms.get(i).pos.y - this.height;
					this.vel.y = 0;
					return;
				}
				if(this.pos.x + this.width > Const.platforms.get(i).pos.x + 5 &&
						this.pos.x < Const.platforms.get(i).pos.x + Const.platforms.get(i).getWidth() &&
						this.pos.y > Const.platforms.get(i).pos.y &&
						this.pos.y < Const.platforms.get(i).pos.y + Const.platforms.get(i).getHeight()){
					this.pos.y = Const.platforms.get(i).pos.y + Const.platforms.get(i).getHeight();
					this.vel.y = 0;
					return;
				}
				if(this.pos.x < Const.platforms.get(i).pos.x){
					this.pos.x = Const.platforms.get(i).pos.x - this.width;
					this.vel.x = 0;
					return;
				}
			}
		}
	}
	
	public void collideProjectiles(){
		for(int i = 0;i < Const.projectiles.size();i++){
			if(!Const.projectiles.get(i).isFriendly() && checkCollision(Const.projectiles.get(i))){
				Const.projectiles.remove(i);
				Player.getInstance().kill();
			}
		}
	}

	public static Player getInstance() {
		if(instance == null) {
			instance = new Player(500,400,12,16,Color.BLACK);
		}
		return instance;
	}

	public int getSpeed() {return this.speed;}

	public boolean isJumping() {return jumping;}
	public void setJumping(boolean jumping) {this.jumping = jumping;}
	public boolean isGoLeft() {return goLeft;}
	public void setGoLeft(boolean goLeft) {this.goLeft = goLeft;}
	public boolean isGoRight() {return goRight;}
	public void setGoRight(boolean goRight) {this.goRight = goRight;}

	public Animation getWalkLeftAnimation() {return walkLeftAnimation;}
	public void setWalkLeftAnimation(Animation walkLeftAnimation) {this.walkLeftAnimation = walkLeftAnimation;}
	public Animation getWalkRightAnimation() {return walkRightAnimation;}
	public void setWalkRightAnimation(Animation walkRightAnimation) {this.walkRightAnimation = walkRightAnimation;}
	public Animation getJumpAnimation() {return jumpAnimation;}
	public void setJumpAnimation(Animation jumpAnimation) {this.jumpAnimation = jumpAnimation;}
	public Animation getFallAnimation() {return fallAnimation;}
	public void setFallAnimation(Animation fallAnimation) {this.fallAnimation = fallAnimation;}
}
