package input;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FenetreListener implements WindowListener{

	private static FenetreListener instance;
	
	public void windowActivated(WindowEvent e) {
		
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.out.println("Closed");
	}

	public void windowDeactivated(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		
	}

	public static FenetreListener getInstance() {
		if(instance == null) {
			instance = new FenetreListener();
		}
		return instance;
	}
}
