package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Animation {
	private int speed;
	public ArrayList<Image> sprites = new ArrayList<Image>();
	
	public Animation(int speed){
		this.setSpeed(speed);
	}
	
	public void add(String path){
		Image img;
		try{
			img = ImageIO.read(new File(path));
			sprites.add(img);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void startAnimation(){
		try {
			Thread.sleep(this.speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getSpeed() {return speed;}
	public void setSpeed(int speed) {this.speed = speed;}
}
