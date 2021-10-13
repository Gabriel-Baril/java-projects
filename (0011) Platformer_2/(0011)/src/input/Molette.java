package input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import player.Camera;

public class Molette implements MouseWheelListener{
	private static Molette instance;
	
	private Molette() {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	public static Molette getInstance() {
		if(instance == null) {
			instance = new Molette();
		}
		return instance;
	}
}
