package main;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import entity.active.Mob;
import entity.passive.Food;
import math.PVector;

public class Const {
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final String TITLE = "Learning_AI_Simulation";
	public static boolean running = true;
	public static boolean updateGame = true;
	public static final int DELAY = 20;
	
	public static ArrayList<Food> foods = new ArrayList<Food>();
	public static ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	public static int rand(int d,int e){
		return d + (int)(Math.random() * ((e - d) + 1));
	}
	
	public static double rand(double d,double e){
		return d + (Math.random() * ((e - d)));
	}
	
	public static double dist(PVector p1,PVector p2){
		try{
			return Math.sqrt((p2.getX() - p1.getX())*(p2.getX() - p1.getX()) + (p2.getY() - p1.getY())*(p2.getY() - p1.getY()));
		}catch(NullPointerException e){System.out.println("p1:" + p1 + "   p2:" + p2);}
		return 0;
	}
}
