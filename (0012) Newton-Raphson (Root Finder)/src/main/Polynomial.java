package main;

public class Polynomial {
	double value;
	int exponent;
	
	public Polynomial(double value,int exponent){
		this.value = value;
		this.exponent = exponent;
	}
	
	public static Polynomial mult(Polynomial p1,Polynomial p2){
		return new Polynomial(p1.value*p2.value,p1.exponent+p2.exponent);
	}
	
	public static Polynomial sub(Polynomial p1,Polynomial p2){
		return new Polynomial(p1.value-p2.value,p1.exponent);
	}
	
	public void setValue(double value){this.value = value;}
	public double getValue(){return value;}
	
	public void refresh(){
		if(exponent < 0){
			exponent = 0;
		}
	}
	
	public void show(){
			if(exponent == 0){
				System.out.print(value + " + ");
			}else{
				System.out.print(value + "x^" + exponent + " + ");
			}
		}
	
}
