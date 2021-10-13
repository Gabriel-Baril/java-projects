package geometry;

import toolkit.PVector;

import math.Equation;
import toolkit.Const;

public class Segment {
	public Vertex start;
	public Vertex end;
	
	public Segment(Vertex start,Vertex end){
		this.start = start;
		this.end = end;
	}
	
	public Equation getEqu(){
		double[] equation = new double[2];
		double[] _blank = {0};
		equation[0] = -((end.pos.y - start.pos.y)/(end.pos.x - start.pos.x));
		equation[1] = (start.pos.y - (((end.pos.y - start.pos.y)/(end.pos.x - start.pos.x))*start.pos.x));
		return new Equation(equation,_blank);
	}
	
	public Segment findLine(){
		double origin = (start.pos.y - (((end.pos.y - start.pos.y)/(end.pos.x - start.pos.x))*start.pos.x));
		double pente = ((end.pos.y - start.pos.y)/(end.pos.x - start.pos.x));
		
		return new Segment(new Vertex(0,origin),new Vertex(Const.WIDTH,(pente*Const.WIDTH + origin)));
	}
	
	public PVector getVector(){
		return new PVector(start.pos.x - end.pos.x,start.pos.y - end.pos.y);
	}
	
	public PVector getPerp(){
		return PVector.PerpenticularCounterClockwise(getVector());
	}
	
	public double dist(){
		return Math.sqrt((start.pos.x - end.pos.x)*(start.pos.x - end.pos.x) + (start.pos.y - end.pos.y)*(start.pos.y - end.pos.y));
	}
}
