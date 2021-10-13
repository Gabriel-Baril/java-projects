package main;

public class Main {
	public static GA gen_algo = new GA(300,10);
	
	public static void main(String[] args) {
		new Fenetre();
	}
	
	public static double dist(int x1,int y1,int x2,int y2) {
		return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
}
