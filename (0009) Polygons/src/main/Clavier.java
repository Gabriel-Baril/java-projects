package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import toolkit.PVector;

import toolkit.Const;

public class Clavier implements KeyListener {

	public void keyPressed(KeyEvent e) {
		transformation(e);
		option(e);
	}

	public void transformation(KeyEvent e){
		double moveSpeed = 5;
		double angSpeed = 1;
		for(int i = 0;i < Const.polygons.size();i++){
			if(Const.polygons.get(i).isFocused()){
				switch(e.getKeyCode()){
				case KeyEvent.VK_RIGHT:
					Const.polygons.get(i).move(new PVector(moveSpeed,0));
					break;
				case KeyEvent.VK_LEFT:
					Const.polygons.get(i).move(new PVector(-moveSpeed,0));
					break;
				case KeyEvent.VK_UP:
					Const.polygons.get(i).move(new PVector(0,-moveSpeed));
					break;
				case KeyEvent.VK_DOWN:
					Const.polygons.get(i).move(new PVector(0,moveSpeed));
					break;
				case KeyEvent.VK_E:
					Const.polygons.get(i).rotate(angSpeed);
					break;
				case KeyEvent.VK_Q:
					Const.polygons.get(i).rotate(-angSpeed);
					break;
				case KeyEvent.VK_W:
					Const.polygons.get(i).bigger();
					break;
				case KeyEvent.VK_S:
					Const.polygons.get(i).smaller();
					break;
				case KeyEvent.VK_R:
					Const.polygons.get(i).reflectY();
					break;
				case KeyEvent.VK_F:
					Const.polygons.get(i).reflectX();
					break;
				}
			}
		}
	}

	public void option(KeyEvent e){
		//Structure de controle pour les options
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_1: Const.polygons.get(i).SHOW_PERIMETER = (Const.polygons.get(i).SHOW_PERIMETER == true) ? false : true; break;
				case KeyEvent.VK_2: Const.polygons.get(i).SHOW_AREA = (Const.polygons.get(i).SHOW_AREA == true) ? false : true; break;
				case KeyEvent.VK_3: Const.polygons.get(i).SHOW_TOTAL_ANGLE = (Const.polygons.get(i).SHOW_TOTAL_ANGLE == true) ? false : true; break;
				case KeyEvent.VK_4: Const.polygons.get(i).SHOW_NB_SIDES = (Const.polygons.get(i).SHOW_NB_SIDES == true) ? false : true; break;
				case KeyEvent.VK_5: Const.polygons.get(i).SHOW_NB_POINTS = (Const.polygons.get(i).SHOW_NB_POINTS == true) ? false : true; break;
				case KeyEvent.VK_6: Const.polygons.get(i).SHOW_IF_CONVEX = (Const.polygons.get(i).SHOW_IF_CONVEX == true) ? false : true; break;
				case KeyEvent.VK_7: Const.polygons.get(i).SHOW_IF_COMPLEX = (Const.polygons.get(i).SHOW_IF_COMPLEX == true) ? false : true; break;

				case KeyEvent.VK_T: Const.polygons.get(i).SHOW_POINT = (Const.polygons.get(i).SHOW_POINT == true) ? false : true; break;
				case KeyEvent.VK_Y: Const.polygons.get(i).SHOW_SEGMENT = (Const.polygons.get(i).SHOW_SEGMENT == true) ? false : true; break;
				case KeyEvent.VK_U: Const.polygons.get(i).SHOW_POINT_ANGLE = (Const.polygons.get(i).SHOW_POINT_ANGLE == true) ? false : true; break;
				case KeyEvent.VK_I: Const.polygons.get(i).SHOW_SEGMENT_DISTANCE = (Const.polygons.get(i).SHOW_SEGMENT_DISTANCE == true) ? false : true; break;
				case KeyEvent.VK_O: Const.polygons.get(i).SHOW_POINT_COORD = (Const.polygons.get(i).SHOW_POINT_COORD == true) ? false : true; break;
				case KeyEvent.VK_P: Const.polygons.get(i).SHOW_SEGMENT_EQU = (Const.polygons.get(i).SHOW_SEGMENT_EQU == true) ? false : true; break;
				case KeyEvent.VK_G: Const.polygons.get(i).SHOW_SEGMENT_LINE = (Const.polygons.get(i).SHOW_SEGMENT_LINE == true) ? false : true; break;

				case KeyEvent.VK_H: Const.polygons.get(i).SHOW_BARYCENTER = (Const.polygons.get(i).SHOW_BARYCENTER == true) ? false : true; break;
				case KeyEvent.VK_J: Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT = (Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT == true) ? false : true; break;
				case KeyEvent.VK_K: Const.polygons.get(i).SHOW_BARYCENTER_ANGLE = (Const.polygons.get(i).SHOW_BARYCENTER_ANGLE == true) ? false : true; break;
				case KeyEvent.VK_L: Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_DISTANCE = (Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_DISTANCE == true) ? false : true; break;
				case KeyEvent.VK_Z: Const.polygons.get(i).SHOW_BARYCENTER_COORD = (Const.polygons.get(i).SHOW_BARYCENTER_COORD == true) ? false : true; break;
				case KeyEvent.VK_X: Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_EQU = (Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_EQU == true) ? false : true; break;
				case KeyEvent.VK_C: Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_LINE = (Const.polygons.get(i).SHOW_BARYCENTER_SEGMENT_LINE == true) ? false : true; break;
				}
			}
		}

	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
