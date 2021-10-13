package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import main.General;

public class RenderContent extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		General.game.setBackground(Color.cyan);
		
		if(General.player != null) {
			if(General.player.isAlive()) {
				g2d.setColor(General.player.getColor());
				g2d.fillRect((int) General.player.pos.getX(),(int) General.player.pos.getY(),(int) General.player.getWidth(),(int) General.player.getHeight());
			}
			
			if(!General.player.isAlive()) {
				for(int i = 0;i < General.player.deadAnimation.particles.size();i++) {
					g2d.setColor(General.player.getColor());
					g2d.fillRect((int) General.player.deadAnimation.particles.get(i).pos.getX(),(int) General.player.deadAnimation.particles.get(i).pos.getY(),(int) General.player.deadAnimation.particles.get(i).getWidth(),(int) General.player.deadAnimation.particles.get(i).getHeight());
				}
			}
		}
		
		
		for(int i = 0;i < General.platforms.size();i++) {
			g2d.setColor(General.platforms.get(i).getColor());
			g2d.fillRect((int) General.platforms.get(i).pos.getX(),(int) General.platforms.get(i).pos.getY(),(int) General.platforms.get(i).getWidth(),(int) General.platforms.get(i).getHeight());
		}
		
		for(int i = 0;i < General.enemies.size();i++) {
			g2d.setColor(General.enemies.get(i).getColor());
			g2d.fillRect((int) General.enemies.get(i).pos.getX(),(int) General.enemies.get(i).pos.getY(),(int) General.enemies.get(i).getWidth(),(int) General.enemies.get(i).getHeight());
		}
		
		for(int i = 0;i < General.coins.size();i++) {
			g2d.setColor(General.coins.get(i).getColor());
			g2d.fillRect((int) General.coins.get(i).pos.getX(),(int) General.coins.get(i).pos.getY(),(int) General.coins.get(i).getWidth(),(int) General.coins.get(i).getHeight());
		}
	}
}
