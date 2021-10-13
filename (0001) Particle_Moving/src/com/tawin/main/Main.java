package com.tawin.main;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final boolean viewTrajectory = false;
	public static ArrayList<Rect> rects = new ArrayList<Rect>();
	
	public static void main(String[] args) {
		for(int i = 0;i < 3000;i++){
			rects.add(new Rect(20 + (int)(Math.random() * WIDTH - 20),20 + (int)(Math.random() * HEIGHT - 20),1 + (int)(Math.random() * 5),1 + (int)(Math.random() * 5),-5 + (int)(Math.random() * 5),-5 + (int)(Math.random() * 5)));
		}
		new Fenetre();
	}
}
