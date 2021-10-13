package com.tawin.physicEngine.toolKit;

public class Conversion {
	public final static double MILLI = 0.001;
	public final static double CENTI = 0.01;
	public final static double DECI =  0.1;
	public final static double METER = 1;
	public final static double DECA =  10;
	public final static double HECTO = 100;
	public final static double KILO =  1000;
	
	public static double convert(double unit, double val, double converted_unit){
		return val*unit/converted_unit;
	}
	
}
