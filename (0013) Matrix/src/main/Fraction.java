package main;

public class Fraction {
	public static double format(int fl_point,double nb){
		String val = String.format("%." + fl_point + "f", nb);
		return Double.valueOf(val.replace(',','.'));
	}
	
	public static String toFraction(double nb){
		int move = 1;
		while(nb != Math.floor(nb)){
			nb *= 10;
			move++;
		}
		
		int den = (int)Math.pow(10, move-1);
		
		int pgcd = PGCD((int)nb,den);
		
		return (int)nb/pgcd + "/" + den/pgcd;
	}
	
	public static int PGCD(int n1,int n2){
		while(!(n1*n2 == 0)){
			if(n1 > n2)
				n1 -= n2;
			else
				n2 -= n1;
		}
		return n1;
	}
}
