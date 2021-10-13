package com.tawin.physicEngine.rendering;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.tawin.physicEngine.toolKit.Vec2;

public class Table {
	Vec2 pos;
	Dimension cellSize;
	float nbRow;
	float nbColumn;
	double width;
	double height;
	ArrayList<ArrayList<String>> data;

	public Table(Vec2 pos,Dimension cellSize,float nbRow,float nbColumn){
		this.pos = pos;
		this.cellSize = cellSize;
		this.nbRow = nbRow;
		this.nbColumn = nbColumn;
	}
	
	public void renderTab(){
		Graphics2D g2d = Panneau.getInstance().getG2D();
		
		for(int i = 0;i < nbColumn + 1;i++){
			g2d.drawLine((int)(pos.x + cellSize.width*i),(int)pos.y, (int)(pos.x + cellSize.width*i),(int)(pos.y + cellSize.height*nbRow));
		}
		
		for(int i = 0;i < nbRow + 1;i++){
			g2d.drawLine((int)pos.x,(int)(pos.y + cellSize.height*i), (int)(pos.x + cellSize.width*nbColumn),(int)(pos.y + cellSize.height*i));
		}
		
	}
}
