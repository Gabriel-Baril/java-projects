package consummable;

import java.awt.Color;

import main.Const;
import main.Panneau;
import math.Vector2;
import platform.Rectangle;
import player.Player;

public class Coin extends Rectangle{
	
	public Coin(double x,double y,int width,int height,Color color){
		super(x,y,width,height,color);
		Const.coins.add(this);
	}
	
	public void update(){
		if(checkCollision(Player.getInstance()) || checkPlatformCollide()){
			Const.coins.remove(this);
		}
		render();
	}
	
	public boolean checkPlatformCollide(){
		for(int i = 0;i < Const.platforms.size();i++){
			if(this.pos.x < Const.platforms.get(i).pos.x + Const.platforms.get(i).getWidth() &&
					this.pos.x + this.width > Const.platforms.get(i).pos.x &&
					this.pos.y < Const.platforms.get(i).pos.y + Const.platforms.get(i).getHeight() &&
					this.pos.y + this.height > Const.platforms.get(i).pos.y){
				return true;
			}
		}
		return false;
		
	}
}
