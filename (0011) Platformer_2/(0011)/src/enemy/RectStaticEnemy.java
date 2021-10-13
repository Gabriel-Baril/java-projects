package enemy;

import java.awt.Color;

import main.Const;
import platform.Rectangle;
import player.Player;

public class RectStaticEnemy extends Rectangle{

	public RectStaticEnemy(double x, double y, int width, int height) {
		super(x, y, width, height);
		Const.rectStaticEnemies.add(this);
	}
	
	public RectStaticEnemy(double x, double y, int width, int height,Color color) {
		super(x, y, width, height,color);
		Const.rectStaticEnemies.add(this);
	}
	
	public void update(){
		if(checkCollision(Player.getInstance())){
			Player.getInstance().kill();
		}
		super.update();
	}

}
