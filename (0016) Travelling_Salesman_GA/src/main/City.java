package main;

public class City {
	private int x;
	private int y;
	
	public City(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public City(City copy) {
		this.x = copy.x;
		this.y = copy.y;
	}
	
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void set(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
