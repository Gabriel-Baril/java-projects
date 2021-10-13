package main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import toolkit.PVector;

import geometry.Vertex;
import toolkit.Const;

public class Souris implements MouseListener,MouseMotionListener{
	
	public void mouseDragged(MouseEvent e) {
		for(int i = 0;i < Const.polygons.size();i++) {
			Const.polygons.get(i).checkPointDragged(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		for(int i = 0;i < Const.polygons.size();i++) {
			Const.polygons.get(i).checkBarycenterClicked(e);
			Const.polygons.get(i).checkPointDestroyed(e);
		}
	}


	public void mouseMoved(MouseEvent e) {
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).POLYPOINT_COLLIDING(new PVector(e.getX() - Const.LEFT_MARGIN,e.getY() - Const.UP_MARGIN))) {
				Const.polygons.get(i).setColor(Color.red);
			}else {
				Const.polygons.get(i).setColor(Color.green);
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
