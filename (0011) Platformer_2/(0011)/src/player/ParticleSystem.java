package player;

import java.awt.Color;
import java.util.ArrayList;

import main.Const;
import math.Vector2;

public class ParticleSystem {
	public Vector2 pos;
	private int duration;
	private int spawnRate;
	private int time = 0;
	ArrayList<Projectile> particles = new ArrayList<Projectile>();
	
	public ParticleSystem(double x,double y,int duration,int spawnRate){
		Const.particleSystems.add(this);
		pos = new Vector2(x,y);
		this.setDuration(duration);
		this.setSpawnRate(spawnRate);
		play();
	}
	
	public void update(){
		for(int i = 0;i < particles.size();i++){
			particles.get(i).acc.add(new Vector2(0,0.05));
		}
	}
	
	private void play(){
		while(time <= duration){
			particles.add(new Projectile(pos.x,pos.y,5,5,Color.RED.darker().darker(),new Vector2(-10 + (int)(Math.random() * 20),-10 + (int)(Math.random() * 20)),true));
			update();
			time++;
		}
		update();
		Const.particleSystems.remove(this);
	}

	public int getDuration() {return duration;}
	public void setDuration(int duration) {this.duration = duration;}
	public int getSpawnRate() {return spawnRate;}
	public void setSpawnRate(int spawnRate) {this.spawnRate = spawnRate;}
}
