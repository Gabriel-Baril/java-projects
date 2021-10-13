package infWindow;

import javax.swing.JFrame;

import toolkit.Const;

public class InfFenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public InfPanneau panel = new InfPanneau();

	public InfFenetre(){
		build();
	}
	
	public void build(){
		this.setSize(Const.INF_WIDTH,Const.INF_HEIGHT);
		this.setVisible(true);
		this.setTitle("GEOMETRY_INFORMATION");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
	}
}
