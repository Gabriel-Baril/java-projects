package menu;

import java.awt.Color;

import main.Const;
import main.Panneau;
import platform.Rectangle;

public class Bouton extends Rectangle{
	public static int fontSize = 30;
	private String text;
	
	public Bouton(double x, double y,String text) {
		super(x, y, Panneau.getInstance().getG2D().getFontMetrics().stringWidth(text), Panneau.getInstance().getG2D().getFontMetrics().getHeight());
		this.setText(text);
		Const.buttonMenus.add(this);
	}
	
	public void update() {
		Panneau.getInstance().getG2D().drawString(getText(),(int)pos.x,(int)pos.y);
		super.render();
	}

	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
}
