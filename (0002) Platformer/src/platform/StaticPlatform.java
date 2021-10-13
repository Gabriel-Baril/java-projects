package platform;

import java.awt.Color;

import Math.PVector;
import abstractClass.Platforms;

public class StaticPlatform extends Platforms{

	public StaticPlatform(float x,float y,float width,float height,Color color,PVector p) {
		super(x,y,width,height,color, p);
	}

	public void update() {
	}

}
