package main;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	public static double random(double minValue, double d) {
        Random r = new Random();
        return minValue + (d - minValue) * r.nextDouble();
    }

	public static void showTab(double[] tab){
		String output = "";
		for(int i = 0;i < tab.length;i++){
			if(i == 0){
				output += "[" + tab[i] + ",";
			}else if(i == tab.length - 1){
				output += tab[i] + "]";
			}else{
				output += tab[i] + ",";
			}
		}
		System.out.println(output);
	}

	public static void showArrayList(ArrayList<Polynomial> p){
		for(int i = 0;i < p.size();i++){
			p.get(i).show();
		}
	}

	public static ArrayList<Polynomial> toArrayList(double[] tab){
		ArrayList<Polynomial> arrayList = new ArrayList<Polynomial>();
		for(int i = 0;i < tab.length;i++){
			arrayList.add(new Polynomial(tab[i],i));
		}
		return arrayList;
	}

	public static double[] toArray(ArrayList<Polynomial> p){
		Function f = new Function(p);
		f.sortInverse();
		double[] tab = new double[f.getDegree()+1];
		for(int i = 0;i < f.getPolynomial().size();i++){
			tab[i] = f.getPolynomial().get(i).value;
		}
		return tab;
	}

	public static double[] transformTab(double[] tab,int size){
		double[] output = new double[size];
		for(int i = 0;i < tab.length;i++){
			output[i] = tab[i];
		}
		return output;
	}
	
	

	public static void main(String[] args) {
		double[] n = new double[]{9,0,1};
		double[] d = new double[]{1,2};

		ArrayList<Polynomial> t = new ArrayList<>();
		t.add(new Polynomial(-1,3));
		t.add(new Polynomial(-2,2));
		t.add(new Polynomial(43,1));
		t.add(new Polynomial(0,0));

		ArrayList<Polynomial> t2 = new ArrayList<>();
		t2.add(new Polynomial(2,1));
		t2.add(new Polynomial(3,0));

		Function f = new Function(t);
		Function f2 = new Function(t2);
		
		
		
		//System.out.println(Function.Intersection(f, f2));

		//Division.polyShow(Main.toArray(f.tangentAt(1).getPolynomial()));

		f.Analize();
		//f2.Analize();
	}
}
