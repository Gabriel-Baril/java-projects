package main.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entity.active.Mob;
import entity.passive.Food;

public class Souris implements MouseListener,MouseMotionListener{
	private static Souris instance;
	
	public void mouseClicked(MouseEvent e) {
		new Mob(e.getX() - 5,e.getY() - 25,20,10);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	
	public static Souris getInstance(){
		if(instance == null){
			instance = new Souris();
		}
		return instance;
	}

}
