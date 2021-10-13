package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private float x;
	private float y;
	private String path;
	private Image img;
	private int scaleX;
	private int scaleY;
	private int rotation;
	
	public Sprite(String path,float x,float y){
		this.setPath(path);
		this.setX(x);
		this.setY(y);
		this.setScaleX(1);
		this.setScaleY(1);
		this.setRotation(0);
		try{
			this.img = ImageIO.read(new File(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Sprite(String path,float x,float y,int scaleX,int scaleY,int rotation){
		this(path,x,y);
		this.setScaleX(scaleX);
		this.setScaleY(scaleY);
		this.setRotation(rotation);
	}

	public void setImage(Image img){this.img = img;}
	public Image getImage(){return this.img;}
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	public int getScaleX() {return scaleX;}
	public void setScaleX(int scaleX) {this.scaleX = scaleX;}
	public int getScaleY() {return scaleY;}
	public void setScaleY(int scaleY) {this.scaleY = scaleY;}
	public int getRotation() {return rotation;}
	public void setRotation(int rotation) {this.rotation = rotation;}
}
