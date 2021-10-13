package main;

import java.util.ArrayList;
import java.util.Collections;

public class Function {
	private ArrayList<Polynomial> poly;
	private ArrayList<Double> roots = new ArrayList<Double>();

	public Function(ArrayList<Polynomial> polynomial){
		this.poly = polynomial;
	}

	public static double NewtonRaphson(double guess,Function f,double oldGuess,int timeTry){
		if(timeTry > 200){
			return Double.MAX_VALUE;
		}
		if(Math.abs(guess - oldGuess) < 0.000000000000001){
			return guess;
		}else{
			timeTry++;
			return NewtonRaphson(guess - (f.f(guess)/f.Derivee().f(guess)),f,guess,timeTry);
		}
	}

	public static Function sub(Function f,Function g) {
		int l_length = Math.max(f.getPolynomial().size(),g.getPolynomial().size());
		double[] output = new double[l_length];
		double[] f_tab = Main.transformTab(Main.toArray(f.getPolynomial()), l_length);
		double[] g_tab = Main.transformTab(Main.toArray(g.getPolynomial()), l_length);
		for(int i = 0;i < l_length;i++) {
			output[i] = f_tab[i] - g_tab[i];
		}
		return new Function(Main.toArrayList(output));
	}

	public static Function add(Function f,Function g) {
		int l_length = Math.max(f.getPolynomial().size(),g.getPolynomial().size());
		double[] output = new double[l_length];
		double[] f_tab = Main.transformTab(Main.toArray(f.getPolynomial()), l_length);
		double[] g_tab = Main.transformTab(Main.toArray(g.getPolynomial()), l_length);
		for(int i = 0;i < l_length;i++) {
			output[i] = f_tab[i] + g_tab[i];
		}
		return new Function(Main.toArrayList(output));
	}

	public static Function mult(Function f,Function g) {
		ArrayList<Polynomial> output = new ArrayList<Polynomial>();
		for(int i = 0;i < f.getPolynomial().size();i++) {
			for(int j = 0;j < g.getPolynomial().size();j++) {
				output.add(Polynomial.mult(f.getPolynomial().get(i), g.getPolynomial().get(j)));
			}
		}
		Function t = new Function(output);
		t.reduce();
		return t;
	}

	public static ArrayList<Point> Intersection(Function f,Function g) {
		int l_length = Math.max(f.getPolynomial().size(),g.getPolynomial().size());
		double[] output = new double[l_length];
		double[] f_tab = Main.transformTab(Main.toArray(f.getPolynomial()), l_length);
		double[] g_tab = Main.transformTab(Main.toArray(g.getPolynomial()), l_length);
		for(int i = 0;i < l_length;i++) {
			output[i] = f_tab[i] - g_tab[i];
		}
		ArrayList<Double> roots = new Function(Main.toArrayList(output)).Roots();
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0;i < roots.size();i++){
			points.add(new Point(roots.get(i),g.f(roots.get(i))));
		}
		return points;
	}

	public Function tangentAt(double x) {
		ArrayList<Polynomial> tangent = new ArrayList<Polynomial>();	
		tangent.add(new Polynomial(Derivee().f(x),1));
		tangent.add(new Polynomial(Derivee().f(x)*-x + f(x),0));
		return new Function(tangent);
	}

	public ArrayList<Interval> DecreasingInterval() {
		ArrayList<Double> roots = Derivee().Roots();
		ArrayList<Interval> decreasing = new ArrayList<Interval>();
		for(int i = 0;i < roots.size();i++) {
			if(i == 0) {
				if(Derivee().f(Main.random(roots.get(i) - 10,roots.get(i))) < 0) {
					decreasing.add(new Interval(Double.MIN_VALUE,roots.get(i)));
				}
			}else if(i == roots.size()-1){
				if(Derivee().f(Main.random(roots.get(i),roots.get(i) + 10)) < 0) {
					decreasing.add(new Interval(roots.get(i),Double.MAX_VALUE));
				}
				if(Derivee().f(Main.random(roots.get(i-1),roots.get(i))) < 0) {
					decreasing.add(new Interval(roots.get(i-1),roots.get(i)));
				}
			}else {
				if(Derivee().f(Main.random(roots.get(i-1),roots.get(i))) < 0) {
					decreasing.add(new Interval(roots.get(i-1),roots.get(i)));
				}
			}
		}
		return decreasing;
	}

	public ArrayList<Interval> IncreasingInterval() {
		ArrayList<Double> roots = Derivee().Roots();
		ArrayList<Interval> increasing = new ArrayList<Interval>();
		for(int i = 0;i < roots.size();i++) {
			if(i == 0) {
				if(Derivee().f(Main.random(roots.get(i) - 10,roots.get(i))) > 0) {
					increasing.add(new Interval(Double.MIN_VALUE,roots.get(i)));
				}
			}else if(i == roots.size()-1){
				if(Derivee().f(Main.random(roots.get(i),roots.get(i) + 10)) > 0) {
					increasing.add(new Interval(roots.get(i),Double.MAX_VALUE));
				}
				if(Derivee().f(Main.random(roots.get(i-1),roots.get(i))) > 0) {
					increasing.add(new Interval(roots.get(i-1),roots.get(i)));
				}
			}else {
				if(Derivee().f(Main.random(roots.get(i-1),roots.get(i))) > 0) {
					increasing.add(new Interval(roots.get(i-1),roots.get(i)));
				}
			}
		}
		return increasing;
	}

	public void Analize() {
		System.out.print("f(x) = ");Division.polyShow(Main.toArray(getPolynomial()));
		System.out.print("Derivative : f(x) = ");Division.polyShow(Main.toArray(Derivee().getPolynomial()));
		System.out.println("Zeros : " + Roots());
		System.out.println("Origin : " + Origin());
		System.out.println("Increasing : " + IncreasingInterval());
		System.out.println("Decreasing : " + DecreasingInterval());
		System.out.println("Extremums : " + Extremums());
		System.out.println("Global Minimum : " + GlobalMinimum());
		System.out.println("Global Maximum : " + GlobalMaximum());
		System.out.println("Inflection : " + InflectionPoints());
//		if(getDegree() > 0) {
//			System.out.println();
//			System.out.println("-----------------(Derivative analisis downward)-----------------");
//			System.out.println();
//			Derivee().Analize();
//		}else {
//			System.out.println();
//			System.out.println("----------------------------------------------------------------");
//		}
	}


	public ArrayList<Polynomial> sortInverse(){
		sort();
		ArrayList<Polynomial> unsorted = new ArrayList<Polynomial>();
		for(int i = 0;i < poly.size();i++){
			for(int j = 0;j < poly.size();j++){
				if(poly.get(j).exponent == i){
					unsorted.add(poly.get(j));
				}
			}
		}
		this.poly = unsorted;
		return unsorted;
	}

	public ArrayList<Polynomial> sort(){
		ArrayList<Polynomial> unsorted = new ArrayList<Polynomial>();
		for(int i = 0;i < poly.size();i++){
			for(int j = 0;j < poly.size();j++){
				if(poly.get(j).exponent == getDegree() - i){
					unsorted.add(poly.get(j));
				}
			}
		}
		this.poly = unsorted;
		return unsorted;
	}

	public void reduce(){
		for(int i = 0;i < poly.size();i++){
			for(int j = 0;j < poly.size();j++){
				if(i != j && poly.get(i).exponent == poly.get(j).exponent){
					poly.get(i).setValue(poly.get(i).value + poly.get(j).value);
					poly.remove(j);
					reduce();
				}
			}
		}
	}

	public ArrayList<Point> InflectionPoints(){
		ArrayList<Point> inflection = new ArrayList<Point>();
		for(int i = 0;i < Derivee().Derivee().Roots().size();i++){
			inflection.add(new Point(Derivee().Derivee().Roots().get(i),f(Derivee().Derivee().Roots().get(i))));
		}
		return inflection;
	}

	public Point GlobalMaximum(){
		if(Extremums().size() > 0){
			Point max = Extremums().get(0);
			for(int i = 0;i < Extremums().size();i++){
				if(Extremums().get(i).getY() > max.getY()){
					max = Extremums().get(i);
				}
			}
			return max;
		}
		return null;
	}

	public Point GlobalMinimum(){
		if(Extremums().size() > 0){
			Point min = Extremums().get(0);
			for(int i = 0;i < Extremums().size();i++){
				if(Extremums().get(i).getY() < min.getY()){
					min = Extremums().get(i);
				}
			}
			return min;
		}
		return null;
	}

	public void Range(){
		if(getDegree() % 2 == 0){
			System.out.println("");
		}else{
			//System.out.println("(-∞,∞)");
		}
	}

	public void Domain() {

	}

	public ArrayList<Double> Roots(){
		return findRoots(this,new ArrayList<Double>());
	}

	public double Origin(){
		return f(0);
	}

	public ArrayList<Point> Extremums(){
		ArrayList<Point> extremums = new ArrayList<Point>();
		for(int i = 0;i < Derivee().Roots().size();i++){
			extremums.add(new Point(Derivee().Roots().get(i),f(Derivee().Roots().get(i))));
		}
		return extremums;
	}

	public ArrayList<Double> findRoots(Function f,ArrayList<Double> roots){
		double nearbyRoot = NewtonRaphson(10,f,12,0);
		if(nearbyRoot == Double.MAX_VALUE){
			Collections.sort(roots);
			this.roots = roots;
			return roots;
		}
		double[] deno = {-nearbyRoot,1.0};
		roots.add(nearbyRoot);
		Function func = new Function(Main.toArrayList(Division.polyLongDiv(Main.toArray(f.getPolynomial()), Main.transformTab(deno, f.getPolynomial().size())).quotient));
		return findRoots(func,roots);
	}

	public Function Derivee(){
		ArrayList<Polynomial> polynomial = new ArrayList<Polynomial>();
		for(int i = 0;i < poly.size();i++){
			if(poly.get(i).exponent > 0){
				polynomial.add(new Polynomial(poly.get(i).value*poly.get(i).exponent,poly.get(i).exponent - 1));
			}
		}
		return new Function(polynomial);
	}

	public double f(double x){
		double result = 0;
		for(int i = 0;i < poly.size();i++){
			if(poly.get(i).exponent < 1){
				result += poly.get(i).value;
			}else{
				result += poly.get(i).value*(Math.pow(x, poly.get(i).exponent));
			}
		}
		return result;
	}

	public int getDegree(){
		int degree = 0;
		for(int i = 0;i < poly.size();i++){
			if(poly.get(i).exponent > degree){
				degree = poly.get(i).exponent;
			}
		}
		return degree;
	}

	public void show(){
		for(int i = 0;i < poly.size();i++){
			poly.get(i).show();
		}
		System.out.println();
	}

	public ArrayList<Polynomial> getPolynomial(){return this.poly;}

	public ArrayList<Double> getRoots() {
		return roots;
	}

	public void setRoots(ArrayList<Double> roots) {
		this.roots = roots;
	}
}
