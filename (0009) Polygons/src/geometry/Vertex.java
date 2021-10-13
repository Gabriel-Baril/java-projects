package geometry;

import toolkit.PVector;

import toolkit.Const;

public class Vertex{
	public PVector pos;
	public String name;

	public Vertex(double x,double y){
		pos = new PVector(x,y);
	}
	
	public Vertex(double x,double y,String name) {
		pos = new PVector(x,y);
		this.name = name;
	}

	public void update(){
	}
	
	public Segment connectTo(Vertex v){
		Segment seg = new Segment(this,v);
		return seg;
	}

	public boolean collideWithMouse(double mouseX,double mouseY){
		if(Math.sqrt((this.pos.x - mouseX)*(this.pos.x - mouseX) + (this.pos.y - mouseY)*(this.pos.y - mouseY)) < Const.P_WEIGHT * 5){
			return true;
		}else{
			return false;
		}
	}
}
