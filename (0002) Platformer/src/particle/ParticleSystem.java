package particle;

import java.util.ArrayList;

import Math.PVector;
import main.Constant;

public class ParticleSystem {
	public PVector pos;
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	private int nbParticles;
	private int duration;
	private int time = 0;

	public ParticleSystem(float x,float y,int nbParticles,int duration) {
		pos = new PVector(x,y);
		this.nbParticles = nbParticles;
		this.setDuration(duration);
	}

	public void update() {
		this.limitEntities(); 
		if(getTime() <= getDuration()) {
			for(int i = 0;i < nbParticles;i++) {
				float randNb = (float) (2 + Math.random() * 3);
				this.particles.add(new Particle(this.pos.getX(),this.pos.getY(),randNb,randNb));
			}
		}
		for(int i = 0;i < particles.size();i++) {
			particles.get(i).update();
			particles.get(i).applyForce(Constant.GRAVITY);
		}
		this.setTime(this.getTime() + 1);
	}

	public void limitEntities() {
		try{
			for(int i = 0;i < particles.size();i++) {
				if(this.particles.get(i).pos.getX() < Constant.BORDER_LEFT){
					this.particles.remove(i);
				}
				if(this.particles.get(i).pos.getX() + this.particles.get(i).getWidth() > Constant.WIDTH - Constant.BORDER_RIGHT){
					this.particles.remove(i);
				}
				if(this.particles.get(i).pos.getY() + this.particles.get(i).getHeight() > Constant.HEIGHT - Constant.BORDER_DOWN){
					this.particles.remove(i);
				}
			}
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}
	
	public void reset() {
		this.time = 0;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
