package entity.passive;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Const;
import main.Panneau;
import math.PVector;

public class Food {
	public PVector pos;
	private int radius;
	private Color color;
	private double nutritionRate = Const.rand(5.0, 13.0);
	
	public Food(double x,double y,int radius,Color color){
		Const.foods.add(this);
		this.pos = new PVector(x - radius,y - radius);
		this.radius = radius;
		this.color = color;
	}
	
	public void Display(Graphics2D g2d){
		g2d.setColor(this.color);
		g2d.drawOval((int)this.pos.x, (int)this.pos.y, this.radius*2, this.radius*2);
	}
	
	public String toString(){
		return "(x: " + pos.x + ",y: " + pos.y + ")" + " - radius: " + radius;
	}
	
	public int getRadius(){return this.radius;}
	public void setRadius(int radius){this.radius = radius;}
	public Color getColor(){return this.color;}
	public void setColor(Color color){this.color = color;}
	public double getNutritionRate() {return nutritionRate;}
	public void setNutritionRate(double nutritionRate) {this.nutritionRate = nutritionRate;}
}
