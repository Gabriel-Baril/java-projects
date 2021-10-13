package main;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static boolean running = true;
	public static Panneau panel = new Panneau();
	public static ArrayList<Planet> planets = new ArrayList<Planet>();
	public static ArrayList<Star> stars = new ArrayList<Star>();
	public static Clavier kListener = new Clavier();
	
	public static void main(String[] args) {
		new Fenetre();
	}
}
