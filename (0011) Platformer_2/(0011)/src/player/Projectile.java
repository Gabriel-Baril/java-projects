package player;

import java.awt.Color;
import java.util.ArrayList;

import main.Const;
import math.Vector2;
import platform.Rectangle;

public class Projectile extends Rectangle{
	private boolean friendly;
	
	public Projectile(double x,double y,int width,int height,Vector2 vel,boolean friendly){
		super(x,y,width,height,vel);
		this.setFriendly(friendly);
		Const.projectiles.add(this);
	}
	
	public Projectile(double x,double y,int width,int height,Color color,Vector2 vel,boolean friendly){
		super(x,y,width,height,color,vel);
		this.setFriendly(friendly);
		Const.projectiles.add(this);
	}
	
	public void update(){
		if(collideBorder() || checkCollision(Const.platforms)){
			Const.projectiles.remove(this);
		}
		super.update();
	}
	
	public boolean collideBorder(){
		if(pos.x < -20 || pos.x > Const.WIDTH + 20 || pos.y < -20 || pos.y > Const.HEIGHT + 20){
			return true;
		}
		return false;
	}
	
	public boolean collidePlatform(){
		for(int i = 0;i < Const.platforms.size();i++){
			if(checkCollision(Const.platforms.get(i))){
				return true;
			}
		}
		return false;
	}

	public boolean isFriendly() {return friendly;}
	public void setFriendly(boolean friendly) {this.friendly = friendly;}
}
