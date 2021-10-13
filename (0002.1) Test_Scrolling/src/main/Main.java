package main;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 206;
	public static final int HEIGHT = 228;
	public static boolean running = true;
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
	public static Clavier kListener = new Clavier();
	public static renderContent render = new renderContent();
	public static ArrayList<Sprite> images = new ArrayList<Sprite>();
	public static Player player = new Player(0,0,new Sprite("data/coin_sprite_1.png",0,0,1,1,0));
	
	public static void main(String[] args) {
		new Fenetre();
	}
}
