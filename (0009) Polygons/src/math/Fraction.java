package math;

import toolkit.Const;

public class Fraction {
	double num;
	double den;
	
	public Fraction(double num,double den){
		this.num = num;
		this.den = den;
	}
	
	public void removeDecimal(){
		int maxDecimal = Math.max(Const.nBDecimal(this.num),Const.nBDecimal(this.den));
		this.num *= Math.pow(10,maxDecimal);
		this.den *= Math.pow(10,maxDecimal);
	}
	
	public void reduce(){
		removeDecimal();
		int pgcd = Const.pgcd((int)this.num,(int)this.den);
		this.num /= pgcd;
		this.den /= pgcd;
	}
	
	public double approximate(){
		return this.num/this.den;
	}
	
	public void show(){
		System.out.println(this.num + " / " + this.den);
	}
}
