package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import toolkit.Const;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		DISPLAY_FPS(2,12,g2d);
		DISPLAY_DOUBLE_PENDULUM(g2d);
		DISPLAY_PENDULUM(g2d);
	}
	
	public void DISPLAY_FPS(int x,int y,Graphics2D g2d){
		g2d.drawString("" + Const.window.fps, x, y);
	}
	
	public void DISPLAY_DOUBLE_PENDULUM(Graphics2D g2d){
		for(int i = 0;i < Const.doublePendulums.size();i++){
			Const.doublePendulums.get(i).update();
			Const.doublePendulums.get(i).drawPendulum(g2d);
		}
	}
	
	public void DISPLAY_PENDULUM(Graphics2D g2d){
		for(int i = 0;i < Const.doublePendulums.size();i++){
			Const.pendulums.get(i).update();
			Const.pendulums.get(i).render(g2d);
		}
	}
}
