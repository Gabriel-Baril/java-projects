package com.tawin.physicEngine.behavior;

public interface Clickable extends Rendable {
	public boolean isClicked(double x,double y);
	public Object getInfo();
	public void renderConsoleInfo();
	public void renderGraphicsInfo();
}
