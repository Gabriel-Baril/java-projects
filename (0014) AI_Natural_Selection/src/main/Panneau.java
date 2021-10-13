package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.active.Mob;

public class Panneau extends JPanel{
	private static Panneau instance;
	Graphics2D g2d;
	
	public void paintComponent(Graphics g){
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		g2d.drawString("Mob(s) : " + Const.mobs.size(),0,10);
		g2d.drawString("Food(s) : " + Const.foods.size(),0,20);
		if(Const.foods.size() != 0) {
			g2d.drawString("Rapport : " + ((double)Const.foods.size()/(double)Const.mobs.size()),0,30);
		}
		
		if(Mob.getAlpha() != null){
			//g2d.drawString("Alpha (RAD: " + Mob.getAlpha().getRadiusDetection() + ",LFE: " + (int)Mob.getAlpha().getLife() + ",SPD: " + String.format("%.2f", Mob.getAlpha().getSpeed()) + ",RST: " + String.format("%.2f", Mob.getAlpha().getResistance()) + ")", (int)Mob.getAlpha().Center().getX() - 100, (int)Mob.getAlpha().Center().getY() - 10);
		}
		DISPLAY_FOOD();
		DISPLAY_MOB();
	}
	
	public void DISPLAY_FOOD(){
		for(int i = 0;i < Const.foods.size();i++){
			Const.foods.get(i).Display(g2d);
		}
	}
	
	public void DISPLAY_MOB(){
		for(int i = 0;i < Const.mobs.size();i++){
			Const.mobs.get(i).update(g2d);
		}
	}
	
	public static Panneau getInstance(){
		if(instance == null){
			instance = new Panneau();
		}
		return instance;
	}
}
