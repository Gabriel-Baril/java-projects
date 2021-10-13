package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Division {
    public static int polyDegree(double[] p) {
        for (int i = p.length - 1; i >= 0; --i) if (p[i] != 0.0) return i;
        return Integer.MIN_VALUE;
    }
 
    public static double[] polyShiftRight(double[] p, int places) {
        if (places <= 0) return p;
        int pd = polyDegree(p);
        if (pd + places >= p.length) {
            throw new IllegalArgumentException("The number of places to be shifted is too large");
        }
        double[] d = Arrays.copyOf(p, p.length);
        for (int i = pd; i >= 0; --i) {
            d[i + places] = d[i];
            d[i] = 0.0;
        }
        return d;
    }
 
    public static void polyMultiply(double[] p, double m) {
        for (int i = 0; i < p.length; ++i) {
            p[i] *= m;
        }
    }
 
    public static void polySubtract(double[] p, double[] s) {
        for (int i = 0; i < p.length; ++i) {
            p[i] -= s[i];
        }
    }
 
    public static Solution polyLongDiv(double[] n, double[] d) {
        if (n.length != d.length) {
            throw new IllegalArgumentException("Numerator and denominator vectors must have the same size");
        }
        int nd = polyDegree(n);
        int dd = polyDegree(d);
        if (dd < 0) {
            throw new IllegalArgumentException("Divisor must have at least one one-zero coefficient");
        }
        if (nd < dd) {
            throw new IllegalArgumentException("The degree of the divisor cannot exceed that of the numerator");
        }
        double[] n2 = Arrays.copyOf(n, n.length);
        double[] q = new double[n.length];
        while (nd >= dd) {
            double[] d2 = polyShiftRight(d, nd - dd);
            q[nd - dd] = n2[nd] / d2[nd];
            polyMultiply(d2, q[nd - dd]);
            polySubtract(n2, d2);
            nd = polyDegree(n2);
        }
        return new Solution(q, n2);
    }
 
    public static void polyShow(double[] p) {
        int pd = polyDegree(p);
        for (int i = pd; i >= 0; --i) {
            double coeff = p[i];
            if (coeff == 0.0) continue;
            if (coeff == 1.0) {
                if (i < pd) {
                    System.out.print(" + ");
                }
            } else if (coeff == -1.0) {
                if (i < pd) {
                    System.out.print(" - ");
                } else {
                    System.out.print("-");
                }
            } else if (coeff < 0.0) {
                if (i < pd) {
                    System.out.printf(" - %.1f", -coeff);
                } else {
                    System.out.print(coeff);
                }
            } else {
                if (i < pd) {
                    System.out.printf(" + %.1f", coeff);
                } else {
                    System.out.print(coeff);
                }
            }
            if (i > 1) System.out.printf("x^%d", i);
            else if (i == 1) System.out.print("x");
        }
        System.out.println();
    }
    
    
//	public static ArrayList<Polynomial> divide(ArrayList<Polynomial> p1,ArrayList<Polynomial> p2,ArrayList<Polynomial> output){
//		ArrayList<Polynomial> tab = new ArrayList<Polynomial>();
//		ArrayList<Polynomial> result = new ArrayList<Polynomial>();
//		Polynomial p = new Polynomial(p1.get(0).value/p2.get(0).value,p1.get(0).exponent - p2.get(0).exponent);
//		for(int i = 0;i < p2.size();i++){
//			tab.add(Polynomial.mult(p, p2.get(i)));
//		}
//		for(int i = 0;i < new Function(p1).getDegree();i++){
//			boolean contain = false;
//			for(int j = 0;j < p1.size();j++){
//				if(p1.get(j).exponent == i){
//					contain = true;
//				}
//			}
//			if(!contain){
//				p1.add(new Polynomial(0,i));
//			}
//		}
//		for(int i = 0;i < p1.size();i++){
//			for(int j = 0;j < tab.size();j++){
//				if(p1.get(i).exponent == tab.get(j).exponent){
//					result.add(Polynomial.sub(p1.get(i), tab.get(j)));
//				}
//			}
//		}
//		for(int i = 0;i < result.size();i++){
//			if(result.get(i).value == 0){
//				result.remove(i);
//			}
//		}
//		output.add(p);
//		if(result.size() == 1) {
//			for(int i = 0;i < output.size();i++){
//				output.get(i).show();
//			}
//			return output;
//		}
//		return divide(result,p2,output);
//	}
}
