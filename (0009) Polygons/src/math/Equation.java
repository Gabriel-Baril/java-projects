package math;

import toolkit.Const;

public class Equation {
	public double[] left; // côté gauche de l'équation
	public double[] right; // côté droit de l'équation
	
	// constructeur
	public Equation(double[] left,double[] right){
		this.left = new double[Math.max(left.length,right.length)];
		this.right = new double[Math.max(left.length,right.length)];
		this.left = constructTab(this.left,left);
		this.right = constructTab(this.right,right);
	}
	
	// Construit un tableau intelligement
	private double[] constructTab(double[] tab,double[] content){
		int dif = tab.length - content.length;
		for(int i = 0;i < dif;i++){
			tab[i] = 0;
		}
		for(int i = dif,j = 0;i < tab.length;i++,j++){
			tab[i] = content[j];
		}
		return tab;
	}
	
	// Réduit l'équation sous la forme ax + ... b + c = 0
	public double[] reduce(){
		double[] n_lft = new double[left.length];
		for(int i = 0;i < left.length;i++){
			n_lft[i] = left[i] + -right[i];  
		}
		return n_lft;
	}
	
	// Résous l'équation pour x si possible
	public void solve(){
		if(reduce().length == 2){
			double a = reduce()[0];
			double b = reduce()[1];
			System.out.println("x  ∈ [" + Const.trf(-b/a) + "]");
		}else if(reduce().length == 3){
			double a = reduce()[0];
			double b = reduce()[1];
			double c = reduce()[2];
			double disc = b*b - 4*a*c;
			if(disc > 0){
				double x1 = ((-b - Math.sqrt(disc))/(2*a));
				double x2 = ((-b + Math.sqrt(disc))/(2*a));
				System.out.println("x  ∈ [" + Const.trf(x1) + ";" + Const.trf(x2) + "]");
			}
			if(disc == 0){
				double x = (-b/2*a);
				System.out.println("x  ∈ [" + Const.trf(x) + "]");
			}
			if(disc < 0){
				System.out.println("NO REAL SOLUTION");
			}
		}else{
			System.out.println("NOT ABLE TO SOLVE THIS");
		}
	}
	
	// Rend un tableau en équation
	public String render(double[] tab){
		String equation = "";
		for(int i = 0;i < tab.length;i++){
			if(tab[i] != 0){
				if(tab.length - i - 1 == 0){
					equation += Const.trf(tab[i]);
				}else if(tab.length - i - 1 == 1){
					if(tab[i] == 1){
						equation += "x" + " + ";
					}else{
						equation += Const.trf(tab[i]) + "x" + " + ";
					}
				}else{
					if(tab[i] == 1){
						equation += "x^" + (tab.length - i - 1) + " + ";
					}else{
						equation += Const.trf(tab[i]) + "x^" + (tab.length - i - 1) + " + ";
					}
				}
			}
		}
		return equation;
	}
	
	// Rend l'équation complète
	public String show(){
		return render(left) + " = " + render(right);
	}
	
	public String showRegle(){
		return "y = " + render(left);
	}
}

