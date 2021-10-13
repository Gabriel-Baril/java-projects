package main;

import java.util.ArrayList;

public class Main {
	public static boolean running = true;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static Panneau panel = new Panneau();
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public static void main(String[] args){
		new Fenetre();
	}
}
