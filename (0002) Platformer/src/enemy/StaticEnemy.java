package enemy;

import java.awt.Color;

import abstractClass.Enemies;

public class StaticEnemy extends Enemies{
	
	public StaticEnemy(float x,float y,float width,float height,Color color) {
		super(x,y,width,height,color);
	}

	public void update() {
	}
}
