package main;

import javax.swing.JButton;

public class Bouton extends JButton{
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Bouton(String name) {
		this.name = name;
		build();
	}
	
	private void build() {
		setText(name);
	}
}
