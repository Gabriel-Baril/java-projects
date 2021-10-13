package player;

import main.Const;
import platform.Rectangle;

public class Camera extends Rectangle{
	private static Camera instance;
	
	public Camera(double x,double y,int width,int height){
		super(x,y,width,height);
	}
	
	public void update(){
		targetPlayer();
	}
	
	public void targetPlayer(){
		pos.x = Player.getInstance().pos.x - this.width/2;
		pos.y = Player.getInstance().pos.y - this.height/2;
		
		if(pos.x < 0){
			pos.x = 0;
		}
		if(pos.y < 0){
			pos.y = 0;
		}
		if(pos.x + width >= Const.WIDTH){
			pos.x = Const.WIDTH - width;
		}
		if(pos.y + height >= Const.HEIGHT){
			pos.y = Const.HEIGHT - height;
		}
	}
	
	public static Camera getInstance() {
		if(instance == null) {
			instance = new Camera(0,0,250,250);
		}
		return instance;
	}
}
