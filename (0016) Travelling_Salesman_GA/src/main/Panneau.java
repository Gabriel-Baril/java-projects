package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Panneau instance;
	private Graphics2D g2d;
	
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i < Main.gen_algo.size;i++) {
			Population cPop = Main.gen_algo.populations[i];
			if(cPop.equals(Main.gen_algo.getBest())) {
				for(int j = 0;j < cPop.cities.size();j++) {
					int next = (j == cPop.cities.size() - 1) ? 0 : (j + 1);
					g2d.setColor(new Color(0,0,0));
					g2d.drawString(cPop.totalDistance() + " px", 0, 10);
					g2d.drawString("" + Main.gen_algo.generation, 0, 20);
					g2d.fillOval(cPop.cities.get(j).getX() - 2, cPop.cities.get(j).getY() - 2, 4, 4);
					g2d.drawString("" + j, cPop.cities.get(j).getX() - 2, cPop.cities.get(j).getY() - 2);
					g2d.drawString("(" + cPop.cities.get(j).getX() + "," + cPop.cities.get(j).getY() + ")", cPop.cities.get(j).getX(), cPop.cities.get(j).getY());
					g2d.drawLine(cPop.cities.get(j).getX(), cPop.cities.get(j).getY(), cPop.cities.get(next).getX(), cPop.cities.get(next).getY());
				}
			}
		}
	}
	
	public static Panneau getInstance() {
		if(Panneau.instance == null)
			Panneau.instance = new Panneau();
		return Panneau.instance;
	}
	
	public Graphics2D getG2D() {
		return this.g2d;
	}
}
