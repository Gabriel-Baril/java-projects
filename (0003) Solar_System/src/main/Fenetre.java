package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	Bouton button1 = new Bouton("Stop");

	public Fenetre(){
		this.build();
		this.setup();
		this.gameLoop();
	}
	
	private void build() {
		button1.addActionListener(new button1Listener());
		Main.panel.add(button1);
		this.addKeyListener(Main.kListener);
		
		setTitle("Solar System");
		setSize(Main.WIDTH,Main.HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(Main.panel);
	}
	
	private void setup() {
		for(int i = 0;i < 1000;i++) {
			Main.planets.add(new Planet((float)(Math.random() * Main.WIDTH),(float)(Math.random() * Main.WIDTH),(float)(5 + Math.random() * 15),10,Color.BLACK));
		}
		Main.stars.add(new Star(500,500,5,5,Color.RED));
	}
	
	private void gameLoop() {
		while(true) {
			updateGame(25);
			System.out.println("");
		}
	}
	
	private void updateGame(int time) {
		while(Main.running) {
			try {
				Thread.sleep(time);
				for(Planet planet : Main.planets) {
					for(Star star : Main.stars) {
						planet.setAttractor(star);
						planet.update();
						//planet.collideEachOther();
					}
				}
				Main.panel.repaint();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean pass = false;
			if(Main.running) {
				Main.running = false;
				button1.setText("Start");
				pass = true;
			}
			if(!Main.running && !pass) {
				button1.setText("Stop");
				Main.running = true;
			}
			pass = true;
		}
	}
}
