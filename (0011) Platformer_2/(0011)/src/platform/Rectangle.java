package platform;

import java.awt.Color;
import java.util.ArrayList;

import graphic.Animation;
import graphic.Sprite;
import main.Const;
import main.Panneau;
import math.Vector2;

public class Rectangle {
	private Sprite TILE_TOP_LEFT;
	private Sprite TILE_TOP;
	private Sprite TILE_TOP_RIGHT;
	private Sprite TILE_RIGHT;
	private Sprite TILE_LEFT;
	private Sprite TILE_BOTTOM_LEFT;
	private Sprite TILE_BOTTOM;
	private Sprite TILE_BOTTOM_RIGHT;
	private Sprite TILE_CENTER;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Sprite sprite;
	
	private boolean textured;
	
	public Vector2 pos;
	public Vector2 vel = new Vector2(0,0);
	public Vector2 acc = new Vector2(0,0);
	protected int width;
	protected int height;
	protected Color color;
	
	public Rectangle(double x,double y,Sprite sprite) {
		this.pos = new Vector2(x,y);
		this.setSprite(sprite);
		this.width = sprite.getSprite().getWidth();
		this.height = sprite.getSprite().getHeight();
	}
	
	public Rectangle(double x,double y,int nbBlocX,int nbBlocY,Sprite[] layout){
		this.pos = new Vector2(x,y);
		this.width = nbBlocX*Const.TILE_SIZE;
		this.height = nbBlocY*Const.TILE_SIZE;
		
		setTILE_TOP_LEFT(layout[0]);
		setTILE_TOP(layout[1]);
		setTILE_TOP_RIGHT(layout[2]); 
		setTILE_RIGHT(layout[3]);
		setTILE_LEFT(layout[4]);
		setTILE_BOTTOM_LEFT(layout[5]);
		setTILE_BOTTOM(layout[6]);
		setTILE_BOTTOM_RIGHT(layout[7]);
		setTILE_CENTER(layout[8]);
		
		construct();
		setTextured(true);
	}
	
	public Rectangle(double x,double y,int width,int height){
		this.pos = new Vector2(x,y);
		this.width = width;
		this.height = height;
		this.color = Color.BLACK;
		setTextured(false);
	}
	
	public Rectangle(double x,double y,int width,int height,Color color){
		this(x, y, width, height);
		this.color = color;
		setTextured(false);
	}
	
	public Rectangle(double x,double y,int width,int height,Vector2 vel){
		this.pos = new Vector2(x,y);
		this.vel = vel;
		this.width = width;
		this.height = height;
		setTextured(false);
		this.color = Color.BLACK;
	}
	
	public Rectangle(double x,double y,int width,int height,Color color,Vector2 vel){
		this(x, y, width, height, vel);
		this.color = color;
		setTextured(false);
	}
	

	private void construct(){
		for(int i = 0;i < height/Const.TILE_SIZE;i++){
			for(int j = 0;j < width/Const.TILE_SIZE;j++){
				if(i == 0 && j == 0) sprites.add(new Sprite(getTILE_TOP_LEFT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // TOP_LEFT
				if(i == 0 && j == (width/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_TOP_RIGHT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // TOP_RIGHT
				if(i == 0 && j != 0 && j != (width/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_TOP().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // TOP
				if(j == 0 && i != 0 && i != (height/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_RIGHT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // RIGHT
				if(j == (width/Const.TILE_SIZE) - 1 && i != 0 && i != (height/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_LEFT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // LEFT
				if(j == 0 && i == (height/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_BOTTOM_LEFT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // BOTTOM_LEFT
				if(j != 0 && i == (height/Const.TILE_SIZE) - 1 && j != (width/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_BOTTOM().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // BOTTOM
				if(j == (width/Const.TILE_SIZE) - 1 && i == (height/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_BOTTOM_RIGHT().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // BOTTOM_RIGHT
				if(i != 0 && i != (height/Const.TILE_SIZE) - 1 && j != 0 && j != (width/Const.TILE_SIZE) - 1) sprites.add(new Sprite(getTILE_CENTER().getSprite(),pos.x + j*Const.TILE_SIZE,pos.y + i*Const.TILE_SIZE)); // CENTER
			}
		}
	}
	
	public void update(){
		pos.add(vel);
		vel.add(acc);
		render();
	}
	
	public final void render(){
		if(isTextured()){
			for(int i = 0;i < sprites.size();i++){
				int tabX = i % (width/Const.TILE_SIZE);
				int tabY = i/(width/Const.TILE_SIZE);
				//System.out.println("index : " + i + " , x : " + tabX + " , y :" + tabY);
				sprites.get(i).pos.x = pos.x + tabX*Const.TILE_SIZE;
				sprites.get(i).pos.y = pos.y + tabY*Const.TILE_SIZE;
				Panneau.getInstance().getG2D().drawImage(sprites.get(i).getSprite(), (int)sprites.get(i).pos.x, (int)sprites.get(i).pos.y, null);
			}
		}else if(!isTextured()){
			Panneau.getInstance().getG2D().setColor(this.color);
			Panneau.getInstance().getG2D().fillRect((int)pos.x,(int)pos.y, width, height);
			Panneau.getInstance().getG2D().setColor(Color.BLACK);
		}
	}
	
	public final boolean checkCollision(Rectangle obj){
		if(this.pos.x < obj.pos.x + obj.getWidth() &&
				this.pos.x + this.width > obj.pos.x &&
				this.pos.y < obj.pos.y + obj.getHeight() &&
				this.pos.y + this.height > obj.pos.y){
			return true;
		}
		return false;
	}
	
	public final boolean checkCollision(ArrayList<? extends Rectangle> arrays){
		for(int i = 0;i < arrays.size();i++){
			if(checkCollision(arrays.get(i))){
				return true;
			}
		}
		return false;
	}
	

	public void setWidth(int width){this.width = width;}
	public void setHeight(int height){this.height = height;}
	public int getWidth(){return this.width;}
	public int getHeight(){return this.height;}
	
	public Sprite getTILE_TOP_LEFT() {return TILE_TOP_LEFT;}
	public void setTILE_TOP_LEFT(Sprite tILE_TOP_LEFT) {TILE_TOP_LEFT = tILE_TOP_LEFT;}
	public Sprite getTILE_TOP() {return TILE_TOP;}
	public void setTILE_TOP(Sprite tILE_TOP) {TILE_TOP = tILE_TOP;}
	public Sprite getTILE_TOP_RIGHT() {return TILE_TOP_RIGHT;}
	public void setTILE_TOP_RIGHT(Sprite tILE_TOP_RIGHT) {TILE_TOP_RIGHT = tILE_TOP_RIGHT;}
	public Sprite getTILE_RIGHT() {return TILE_RIGHT;}
	public void setTILE_RIGHT(Sprite tILE_RIGHT) {TILE_RIGHT = tILE_RIGHT;}
	public Sprite getTILE_LEFT() {return TILE_LEFT;}
	public void setTILE_LEFT(Sprite tILE_LEFT) {TILE_LEFT = tILE_LEFT;}
	public Sprite getTILE_BOTTOM_LEFT() {return TILE_BOTTOM_LEFT;}
	public void setTILE_BOTTOM_LEFT(Sprite tILE_BOTTOM_LEFT) {TILE_BOTTOM_LEFT = tILE_BOTTOM_LEFT;}
	public Sprite getTILE_BOTTOM() {return TILE_BOTTOM;}
	public void setTILE_BOTTOM(Sprite tILE_BOTTOM) {TILE_BOTTOM = tILE_BOTTOM;}
	public Sprite getTILE_BOTTOM_RIGHT() {return TILE_BOTTOM_RIGHT;}
	public void setTILE_BOTTOM_RIGHT(Sprite tILE_BOTTOM_RIGHT) {TILE_BOTTOM_RIGHT = tILE_BOTTOM_RIGHT;}
	public Sprite getTILE_CENTER() {return TILE_CENTER;}
	public void setTILE_CENTER(Sprite tILE_CENTER) {TILE_CENTER = tILE_CENTER;}

	public boolean isTextured() {return textured;}
	public void setTextured(boolean textured) {this.textured = textured;}

	public Sprite getSprite() {return sprite;}
	public void setSprite(Sprite sprite) {this.sprite = sprite;}
}
