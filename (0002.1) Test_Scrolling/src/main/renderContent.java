package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class renderContent extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		this.setBackground(Color.CYAN);
		for(int i = 0;i < Main.images.size();i++){
			AffineTransform at = AffineTransform.getTranslateInstance(Main.images.get(i).getX(), Main.images.get(i).getY());
			at.scale(Main.images.get(i).getScaleX(), Main.images.get(i).getScaleY());
			at.rotate(Math.toRadians(Main.images.get(i).getRotation()), Main.images.get(i).getImage().getWidth(null)/2,Main.images.get(i).getImage().getHeight(null)/2);
			g2d.setColor(Color.RED);
			g2d.drawImage(Main.images.get(i).getImage(),at, null);
		}
	}
}
