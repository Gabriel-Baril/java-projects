package entity.active;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import entity.passive.Food;
import main.Const;
import main.Panneau;
import math.PVector;

public class Mob {
	// OPTION
	public static boolean SHOW_ZONE = false;
	public static boolean SHOW_FOOD_SEG = false;
	
	// VARIABLE
	private String name;
	private Color c = Color.BLACK; //new Color(Const.rand(0, 255),Const.rand(0, 255),Const.rand(0, 255));
	private PVector vel = new PVector(0,0);
	private int width;
	private int height;
	private double life = Const.rand(70, 120); //LFE
	private double initialLife = getCurrentLife();
	private double speed = Const.rand(2.0,4.0); // SPD
	private double resistance = Const.rand(1.0, 2.0); // RST
	private int radiusDetection = Const.rand(70, 140); // RAD
	private ArrayList<PVector> points = new ArrayList<PVector>();
	private double rotation = 0;
	public Mob parent = null;
	public ArrayList<Mob> child = new ArrayList<Mob>();
	
	private int decisionTime = Const.rand(1000, 3000);
	private int fertilityTime = Const.rand(5000, 10000);
	private long lTurn = System.currentTimeMillis();
	private long fTurn = System.currentTimeMillis();
	
	

	public Mob(double x,double y,int width,int height){
		Const.mobs.add(this);
		name = "Mob_" + (Const.mobs.indexOf(this)+1);
		this.width = Const.rand(20, 30);
		this.height = Const.rand(10, 15);
		points.add(new PVector(x,y));
		points.add(new PVector(x,y + height));
		points.add(new PVector(x + width,y + height/2));
	}
	
	public Mob(double x,double y,int width,int height,double life,double speed,double resistance,int radiusDetection,Mob parent){
		this(x, y, width, height);
		this.setCurrentLife(life);
		this.setSpeed(speed);
		this.setResistance(resistance);
		this.setRadiusDetection(radiusDetection);
		this.parent = parent;
	}

	public void update(Graphics2D g2d){
		if(life <= 0){
			this.kill();
		}
		this.move();
		this.reproduce();
		this.setCurrentLife(this.getCurrentLife() - 0.6);
		this.CollideBorder();
		this.Display(g2d);
	}
	
	public void move(){
		Food closer = getCloserFood();
		PVector center = Center();
		PVector point_2 = points.get(2);
		if(closer != null){
			this.vel = new PVector(closer.pos.getX() - center.getX(),closer.pos.getY() - center.getY()).normalize().scale(speed);
			double rot = Math.toDegrees(Math.atan2(closer.pos.getY() - center.getY(),closer.pos.getX() - center.getX()));
			rotate(rot - getRotation());
			setRotation(rot);
			if(Const.dist(point_2,closer.pos) < 10 || Const.dist(center,closer.pos) < 10){
				life += closer.getNutritionRate();
				Const.foods.remove(closer);
			}
		}else{
			if(System.currentTimeMillis() - lTurn >= decisionTime){
				if(Const.rand(0.0, 1.0) < 0.035){
					double rot = Const.rand(0, 360);
					rotate(rot);
					this.vel = new PVector(point_2.getX() - center.getX(),point_2.getY() - center.getY()).normalize().scale(speed/1.5);
					lTurn = System.currentTimeMillis();
					setRotation(rot + getRotation());
				}else{
					this.vel = new PVector(0,0);
				}
			}
		}
		points.get(0).add(vel);
		points.get(1).add(vel);
		points.get(2).add(vel);
	}
	
	public void reproduce(){
		if(System.currentTimeMillis() - fTurn >= fertilityTime){
			this.child.add(new Mob(points.get(0).x,points.get(0).y,width,height,getInitialLife(),getSpeed(),getResistance(),getRadiusDetection(),this));
			fTurn = System.currentTimeMillis();
		}
	}
	
	public void kill(){
		System.out.println(Const.mobs.size());
		Const.mobs.remove(this);
	}
	
	public String getRootParent(Mob parent){
		if(parent == null){
			return name;
		}
		return getRootParent(parent);
	}

	public ArrayList<Food> getProximityFood(){
		ArrayList<Food> prox = new ArrayList<Food>();
		for(int i = 0;i < Const.foods.size();i++){
			Food cFood = Const.foods.get(i);
			if(cFood.pos != null){
				if(Const.dist(Center(), cFood.pos) < radiusDetection + cFood.getRadius()){
					prox.add(cFood);
				}
			}
		}
		return prox;
	}

	public Food getCloserFood(){
		ArrayList<Food> proxFood = getProximityFood();
		if(proxFood.size() == 0){
			return null;
		}
		PVector center = Center();
		double closer = Double.MAX_VALUE;
		Food food = null;
		for(int i = 0;i < proxFood.size();i++){
			if(Const.dist(center, proxFood.get(i).pos) < closer){
				closer = Const.dist(center, proxFood.get(i).pos);
				food = proxFood.get(i);
			}
		}
		return food;
	}

	public void Display(Graphics2D g2d){
		PVector center = Center();
		g2d.setColor(c);
		//for(int i = 0;i < points.size();i++){
		//	g2d.fillOval((int)points.get(i).getX(),(int)points.get(i).getY(),3,3);
		//}
		//g2d.drawString("(" + (int)center.getX() + "," + (int)center.getY() + ")", (int)center.getX(), (int)center.getY());
		//g2d.fillOval((int)center.getX(),(int)center.getY(),3,3);
		for(int i = 0;i < points.size();i++){
			int next = (i == points.size() - 1) ? 0 : i + 1;
			g2d.drawLine((int)points.get(i).getX() + 1,(int)points.get(i).getY() + 1, (int)points.get(next).getX() + 1,(int)points.get(next).getY() + 1);
		}
		g2d.drawString("" + (int)life, (int)center.getX(), (int)center.getY());
		if(SHOW_ZONE){
			g2d.drawOval((int)center.getX() - radiusDetection + 2, (int)center.getY() - radiusDetection + 2, radiusDetection*2, radiusDetection*2);
		}
		if(SHOW_FOOD_SEG){
			ArrayList<Food> proxFood = getProximityFood();
			for(int i = 0;i < proxFood.size();i++){
				g2d.drawLine((int)center.getX(), (int)center.getY(), (int)proxFood.get(i).pos.getX() + proxFood.get(i).getRadius(), (int)proxFood.get(i).pos.getY() + proxFood.get(i).getRadius());
			}
		}
	}

	public PVector Center(){
		return new PVector((points.get(0).getX() + points.get(1).getX() + points.get(2).getX())/3,(points.get(0).getY() + points.get(1).getY() + points.get(2).getY())/3);
	}
	
	public void CollideBorder(){
		PVector point_2 = points.get(2);
		if(point_2.getX() > Const.WIDTH || point_2.getX() < 0){
			this.vel.setX(-this.vel.getX());
		}
		if(point_2.getY() > Const.HEIGHT || point_2.getY() < 0){
			this.vel.setY(-this.vel.getY());
		}
	}

	public void rotate(double angle){
		PVector center = Center();
		for(int i = 0;i < points.size();i++){
			PVector cPoint = points.get(i);
			double x = center.getX() + (cPoint.getX() - center.getX())*Math.cos(Math.toRadians(angle)) - (cPoint.getY() - center.getY())*Math.sin(Math.toRadians(angle));
			double y = center.getY() + (cPoint.getX() - center.getX())*Math.sin(Math.toRadians(angle)) + (cPoint.getY() - center.getY())*Math.cos(Math.toRadians(angle));
			cPoint.set(x,y);		
		}
	}
	
	public static Mob getAlpha(){
		if(Const.mobs.size() == 0){
			return null;
		}
		Mob alpha = Const.mobs.get(0);
		for(int i = 0;i < Const.mobs.size() - 1;i++){
			if(alpha.getCurrentLife() < Const.mobs.get(i + 1).getCurrentLife()){
				alpha = Const.mobs.get(i + 1);
			}
		}
		return alpha;
	}
	
	public String toString(){
		return name;
	}
	
	public int getWidth(){return this.width;}
	public void setWidth(int width){this.width = width;}
	public int getHeight(){return this.height;}
	public void setHeight(int height){this.height = height;}
	public int getRadiusDetection(){return this.radiusDetection;}
	public void setRadiusDetection(int radiusDetection){this.radiusDetection = radiusDetection;}
	public double getInitialLife(){return this.initialLife;}
	public void setInitialLife(double life){this.initialLife = life;}
	public double getCurrentLife(){return this.life;}
	public void setCurrentLife(double life){this.life = life;}
	public double getSpeed(){return this.speed;}
	public void setSpeed(double speed){this.speed = speed;}
	public double getResistance(){return this.resistance;}
	public void setResistance(double resistance){this.resistance = resistance;}
	public void setRotation(double rotation){this.rotation = rotation;}
	public double getRotation(){return this.rotation;}
}
