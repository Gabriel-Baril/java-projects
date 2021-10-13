package geometry;

public class Projection {
	double min;
	double max;
	
	public Projection(double min,double max){
		this.min = min;
		this.max = max;
	}
	
	public boolean overlap(Projection p){
		if(p.min < max && p.max > min){
			return true;
		}else{
			return false;
		}
	}
}
