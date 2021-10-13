package main;

public class Interval {
	private double min;
	private double max;
	
	public Interval(double min,double max) {
		this.setMin(min);
		this.setMax(max);
	}
	
	public String toString() {
		if(min == Double.MIN_VALUE) {
			return " ]" + "-infinity" + "," + max + "[ ";
		}
		if(max == Double.MAX_VALUE) {
			return " ]" + min + "," + "infinity" + "[ ";
		}
		return " ]" + min + "," + max + "[ ";
	}

	public double getMin() {return min;}
	public void setMin(double min) {this.min = min;}
	public double getMax() {return max;}
	public void setMax(double max) {this.max = max;}
}
