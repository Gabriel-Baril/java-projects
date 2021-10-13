package main;

public class Platform {
	float x;
	float y;
	float velX;
	float velY;
	Sprite sprite;
	
	public Platform(float x,float y,Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		Main.images.add(sprite);
	}
	
	public void update(){if(
		sprite != null){
			sprite.setX(this.x);
			sprite.setY(this.y);
		}
		this.x += this.velX;
		this.y += this.velY;
	}
}
