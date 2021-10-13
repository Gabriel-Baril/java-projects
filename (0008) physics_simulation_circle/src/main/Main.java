package main;

import java.util.ArrayList;

public class Main {
	public static boolean running = true;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static Panneau panel = new Panneau();
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
	public static final Vector GRAVITY = new Vector(0,0.5f);
	
	public static void main(String[] args){
		new Fenetre();
	}
}
