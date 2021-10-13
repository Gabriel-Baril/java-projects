package main;

import javax.swing.JFrame;

import main.listener.Clavier;
import main.listener.Molette;
import main.listener.Mouse;


public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int   WIDTH = 900;
	public static final int   HEIGHT = 900;
	public static boolean     running = true;
	public static final int   DELAY = 16;

	public Fenetre() {
		this.setup();
		this.build();
		this.gameLoop();
	}

	public void setup() {
	}

	public void build() {
		this.addKeyListener(Clavier.getInstance());
		this.addMouseListener(Mouse.getInstance());
		this.addMouseMotionListener(Mouse.getInstance());
		this.addMouseWheelListener(Molette.getInstance());

		this.setTitle("Travelling_salesman_GA");
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(Panneau.getInstance());
	}

	public void gameLoop() {
		while(Fenetre.running) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.update();
		}
	}

	public void update() {
		Main.gen_algo.naturalSelection();
		Panneau.getInstance().repaint();
	}
}
