package toolkit;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import toolkit.PVector;

import geometry.Polygon;
import geometry.Segment;
import geometry.Vertex;
import main.Clavier;
import main.Molette;
import main.Panneau;
import main.Souris;
import math.Equation;

public class Const {
	
	public static ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	
	public static final double GRAVITY = 0.01;
	
	public static final int UP_MARGIN = 27;
	public static final int LEFT_MARGIN = 5;

	public static final int WIDTH = 700; //Largeur fenetre
	public static final int HEIGHT = 700; //hauteur fenetre
	
	public static final int INF_WIDTH = 700;
	public static final int INF_HEIGHT = 500;
	
	public static final int P_WEIGHT = 4; //épaisseur point
	public static final int L_WEIGHT = 2; //épaisseur ligne
	public static final Panneau panel = new Panneau();
	public static final Clavier kListener = new Clavier();
	public static final Souris mListener = new Souris();
	public static final Molette mwListener = new Molette();
	public static boolean running = true;


	public static String[] optionNames = {
			"SHOW_PERIMETER",
			"SHOW_AREA",
			"SHOW_TOTAL_ANGLE",
			"SHOW_NB_SIDES",
			"SHOW_NB_POINTS",
			"SHOW_IF_CONVEX",
			"SHOW_IF_COMPLEX",
			//
			"SHOW_POINT",
			"SHOW_SEGMENT",
			"SHOW_POINT_ANGLE",
			"SHOW_SEGMENT_DISTANCE",
			"SHOW_POINT_COORD",
			"SHOW_SEGMENT_EQU",
			"SHOW_SEGMENT_LINE",
			//
			"SHOW_BARYCENTER",
			"SHOW_BARYCENTER_SEGMENT",
			"SHOW_BARYCENTER_ANGLE",
			"SHOW_BARYCENTER_SEGMENT_DISTANCE",
			"SHOW_BARYCENTER_COORD",
			"SHOW_BARYCENTER_SEGMENT_EQU",
			"SHOW_BARYCENTER_SEGMENT_LINE"
	};
	
	public static String[] optionShortcuts = {
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			
			"T",
			"Y",
			"U",
			"I",
			"O",
			"P",
			"G",
			
			"H",
			"J",
			"K",
			"L",
			"Z",
			"X",
			"C"
	};
	
	public static Vertex intersect(Segment d1,Segment d2){
		Equation inter = new Equation(d1.getEqu().reduce(),d2.getEqu().reduce());
		double x = solve(inter);
		double y = f(d1.getEqu(), x);
		return new Vertex(-x,y);
	}
	
	public static double f(Equation eq,double x){
		double a = eq.reduce()[0];
		double b = eq.reduce()[1];
		return a*x + b;
	}
	
	public static double solve(Equation eq){
		double a = eq.reduce()[0];
		double b = eq.reduce()[1];
		return -b/a;
	}
	
	public static double dist(Vertex v1,Vertex v2){
		return Math.sqrt((v1.pos.x - v2.pos.x)*(v1.pos.x - v2.pos.x) + (v1.pos.y - v2.pos.y)*(v1.pos.y - v2.pos.y));
	}
	
	// Trouve le nombre de décimale d'un nombre et l'applique dynamiquement à celui-ci
	public static String trf(double x){
		return String.format("%." + 1 + "f",x);
	}
	
	public static int pgcd(int a,int b){
		while(a*b != 0){
			if(a > b){
				a -= b;
			}else{
				b -= a;
			}
		}
		return a;
	}
	
	// Renvoie le nombre de décimale d'un nombre
	public static int nBDecimal(double x){
		if(x % (int)x == 0){
			return 0;
		}else{
			String t_number = Double.toString(x);
			for(int i = 0;i < t_number.length();i++){
				if(t_number.charAt(i) == '.'){
					return t_number.length() - i - 1;
				}
			}
		}
		return -1;
	}
	
	// Rend un tableau
	public static String showTab(double[] tab){
		String str = "";
		for(int i = 0;i < tab.length;i++){
			if(i == 0){
				str += "[" + trf(tab[i]) + ",";
			}else if(i < tab.length - 1){
				str += trf(tab[i]) + ",";
			}else{
				str += trf(tab[i]) + "]";
			}
		}
		System.out.println(str);
		return str;
	}
}
