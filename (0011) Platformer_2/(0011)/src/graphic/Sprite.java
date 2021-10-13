package graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Const;
import math.Vector2;

public class Sprite{
	private BufferedImage sprite;
	public Vector2 pos;
	
	public Sprite(BufferedImage sprite,double x,double y){
		this.setSprite(sprite);
		this.pos = new Vector2(x,y);
		Const.sprites.add(this);
	}
	
	public Sprite(BufferedImage sprite){
		this.setSprite(sprite);
		this.pos = new Vector2(0,0);
		Const.sprites.add(this);
	}
	
	public Sprite(String path,double x,double y){
		try{
			sprite = ImageIO.read(new File(path));
		}catch(IOException e){}
		this.pos = new Vector2(x,y);
		Const.sprites.add(this);
	}
	
	public Sprite(String path){
		try{
			sprite = ImageIO.read(new File(path));
		}catch(IOException e){}
		this.pos = new Vector2(0,0);
		Const.sprites.add(this);
	}

	public BufferedImage getSprite() {return sprite;}
	public void setSprite(BufferedImage sprite) {this.sprite = sprite;}
}
