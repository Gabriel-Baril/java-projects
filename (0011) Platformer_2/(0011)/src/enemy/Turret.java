package enemy;

import java.awt.Color;

import main.Const;
import math.Vector2;
import platform.Rectangle;
import player.Player;
import player.Projectile;

public class Turret extends Rectangle{
	private int cooldown = 25;
	private int time = 0;
	private double speed = 10;
	private double accuracy = 100;
	
	public Turret(double x,double y,int width,int height,Color color){
		super(x,y,width,height,color);
		Const.turrets.add(this);
	}
	
	public void update() {
		if(checkCollision(Player.getInstance())) {
			Player.getInstance().kill();
		}
		targetPlayer();
		super.update();
	}
	
	public double mutate(double diff){
		diff += (Math.random() >= 0.5) ? (diff/accuracy)*(Math.random()*10) : -(diff/accuracy)*(Math.random()*10);
		return diff;
	}
	
	public void targetPlayer(){
		if(time == cooldown) {
			new Projectile(pos.x + getWidth()/2,pos.y + getHeight()/2,5,5,Color.RED,new Vector2(mutate(Player.getInstance().pos.x - this.pos.x),mutate(Player.getInstance().pos.y - this.pos.y)).Normalize().scale(this.speed),false);
			time = 0;
		}
		time++;
	}
}
