package graphic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.Const;
import main.Panneau;
import player.Player;

public class Animation {
	private int frameCount;
	private int frameDelay;
	private int currentFrame;
	private int animationDirection;
	private int totalFrames;
	
	private boolean stopped;
	
	private List<Frame> frames = new ArrayList<Frame>();
	
	public Animation(Sprite[] frames,int frameDelay){
		this.stopped = true;
		
		for(int i = 0;i < frames.length;i++){
			this.addFrame(frames[i].getSprite(), frameDelay);
		}
		
		this.frameCount = 0;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
		
		this.start();
		Const.animations.add(this);
	}
	
	public Animation(BufferedImage[] frames,int frameDelay){
		this.stopped = true;
		
		for(int i = 0;i < frames.length;i++){
			this.addFrame(frames[i], frameDelay);
		}
		
		this.frameCount = 0;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
		
		this.start();
		Const.animations.add(this);
	}
	
	public Animation(String root,String[] paths,int frameDelay){
		this.stopped = true;
		
		for(int i = 0;i < paths.length;i++){
			this.addFrame(new Sprite(root + paths[i]).getSprite(), frameDelay);
		}
		
		this.frameCount = 0;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
		
		this.start();
		Const.animations.add(this);
	}
	
	public void update(){
		if(!stopped){
			frameCount++;
			if(frameCount > frameDelay){
				frameCount = 0;
				currentFrame += animationDirection;
				if(currentFrame >= totalFrames - 1){
					currentFrame = 0;
				}else if(currentFrame < 0){
					currentFrame = totalFrames - 1;
				}
			}
		}
		//Panneau.getInstance().getG2D().drawImage(getSprite(),(int)Player.getInstance().pos.x,(int)Player.getInstance().pos.y,null);
	}

	public void addFrame(BufferedImage frame, int delay){
		if(delay <= 0){
			System.err.println("Invalid duration : " + delay);
			throw new RuntimeException("Invalid duration : " + delay);
		}
		
		frames.add(new Frame(frame,delay));
		this.currentFrame = 0;
	}
	
	public void start(){
		if(!stopped || frames.size() == 0){
			return;
		}
		
		stopped = false;
	}
	
	public void stop(){
		if(frames.size() == 0){
			return;
		}
		
		stopped = true;
	}
	
	public void restart(){
		if(frames.size() == 0){
			return;
		}
		
		stopped = false;
		currentFrame = 0;
	}
	
	public void reset(){
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
	}
	
	public BufferedImage getSprite(){
		//System.out.println(currentFrame);
		return frames.get(currentFrame).getFrame();
	}
	
	private class Frame {
		private BufferedImage frame;
		private int duration;
		
		public Frame(BufferedImage frame,int duration){
			this.setFrame(frame);
			this.setDuration(duration);
		}

		public BufferedImage getFrame() {return frame;}
		public void setFrame(BufferedImage frame) {this.frame = frame;}
		public int getDuration() {return duration;}
		public void setDuration(int duration) {this.duration = duration;}
	}
}
