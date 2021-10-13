package platform;

import java.awt.Color;

import graphic.Sprite;
import main.Const;

public class RectPlatform extends Rectangle{

	public RectPlatform(float x,float y,int nbBlocX,int nbBlocY,Sprite[] layout){
		super(x,y,nbBlocX,nbBlocY,layout);
		Const.platforms.add(this);
	}
	
	public RectPlatform(float x,float y,int width,int height){
		super(x,y,width,height);
		Const.platforms.add(this);
	}
	
	public RectPlatform(float x,float y,int width,int height,Color color){
		super(x,y,width,height,color);
		Const.platforms.add(this);
	}
}
