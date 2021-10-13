package main;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	public Fenetre(){
		General.setup();
		buildWindow();
	}

	private void buildWindow(){
		this.addKeyListener(General.keyBoard);
		this.addMouseListener(General.mouse);
		this.addMouseWheelListener(General.mouseWheel);
		setTitle(Constant.TITLE);
		setSize(Constant.WIDTH,Constant.HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(General.game);
		gameLoop();
	}

	private void gameLoop(){
		while(General.running){
			General.updateGame();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}