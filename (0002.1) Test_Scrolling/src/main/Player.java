package main;

public class Player {
	float x;
	float y;
	float velX;
	float velY;
	float accX;
	float accY;
	Sprite sprite;
	boolean blocked = false;
	
	public Player(float x,float y,Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		Main.images.add(this.sprite);
	}
	
	public void update(){
		if(sprite != null){
			sprite.setX(this.x);
			sprite.setY(this.y);
		}
		this.x += velX;
		this.y += velY;
		this.velX += this.accX;
		this.velY += this.accY;
		
		this.accX *= 0;
		this.accY *= 0;
	}
	
	public void collideEdge(){
		if(this.x < 0){
			this.velX = 0;
			this.x = 0;
		}
		if(this.y < 0){
			this.velY = 0;
			this.y = 0;
		}
		if(this.x + this.sprite.getImage().getWidth(null) > Main.WIDTH - 16){
			this.velX = 0;
			this.x = ((Main.WIDTH - this.sprite.getImage().getWidth(null)) - 16);
		}
		if(this.y + this.sprite.getImage().getHeight(null) > Main.HEIGHT - 38 ){
			this.velY = 0;
			this.y = ((Main.HEIGHT - this.sprite.getImage().getHeight(null)) - 38);
		}
	}
	
	public void collidePlatform() {
		for(int i = 0;i < Main.platforms.size();i++) {
			if(this.x < Main.platforms.get(i).x + Main.platforms.get(i).sprite.getImage().getWidth(null) &&
					this.x + this.sprite.getImage().getWidth(null) > Main.platforms.get(i).x &&
					this.y < Main.platforms.get(i).y + Main.platforms.get(i).sprite.getImage().getHeight(null) &&
					this.y + this.sprite.getImage().getHeight(null) > Main.platforms.get(i).y) {
				if (this.x + this.sprite.getImage().getWidth(null) > Main.platforms.get(i).x + Main.platforms.get(i).sprite.getImage().getWidth(null)) {
					this.x = Main.platforms.get(i).x + Main.platforms.get(i).sprite.getImage().getWidth(null);
					this.velX = 0;
				} else if (this.y + this.sprite.getImage().getHeight(null) > Main.platforms.get(i).y + Main.platforms.get(i).sprite.getImage().getHeight(null)) {
					this.y = Main.platforms.get(i).y + Main.platforms.get(i).sprite.getImage().getHeight(null);
					this.velY = 0;
				} else if (this.y + this.sprite.getImage().getHeight(null) >= Main.platforms.get(i).y && this.x + this.sprite.getImage().getWidth(null) >= Main.platforms.get(i).x && this.y < Main.platforms.get(i).y) {
					this.y = Main.platforms.get(i).y - this.sprite.getImage().getHeight(null);
					this.velY = 0;
				} else if (this.x + this.sprite.getImage().getWidth(null) >= Main.platforms.get(i).x && this.y + this.sprite.getImage().getHeight(null) > Main.platforms.get(i).y) {
					this.x = Main.platforms.get(i).x - this.sprite.getImage().getWidth(null);
					this.velX = 0;
				}
			}
		}
	}
	
	public void applyForce(float forceX,float forceY){
		this.accX += forceX;
		this.accY += forceY;
	}
	
	public void limitMove(){
		if(this.x > Main.WIDTH/2){
			this.x = Main.WIDTH/2;
			this.blocked = true;
		}
	}
}
