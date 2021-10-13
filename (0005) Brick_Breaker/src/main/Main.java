package main;

import java.util.ArrayList;

public class Main {
	public static boolean running = true;
	public static Clavier kListener = new Clavier();
	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static Panneau panel = new Panneau();
	public static Ball ball = new Ball(Main.WIDTH/2,Main.HEIGHT/2,10,10);
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	public static Palet palet = new Palet(WIDTH/2 - 50,HEIGHT - 70,100,10);
	
	public static void main(String[] args){
		new Fenetre();
	}
}
