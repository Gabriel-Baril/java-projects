package main;

public class Main {
	public static boolean running = true;
	public static int WIDTH = 720;
	public static int HEIGHT = 480;
	public static Panneau panel = new Panneau();
	public static Clavier kListener = new Clavier();
	public static Palet playerPalet = new Palet(20,20,10,100,20);
	public static Palet IAPalet = new Palet(WIDTH - 35,20,10,100,20);
	public static Ball ball = new Ball(WIDTH/2 - 5,HEIGHT/2 - 5,10,10,(float)(2 + Math.random() * 10),(float)(2 + Math.random() * 10));
	
	public static void main(String[] args){
		new Fenetre();
	}
}
