package main;

import java.util.Random;

public class Prob {
	public static double NormalRandomDouble(double min,double max){
		return min + Math.random()*(max - min);
	}
	
	public static int NormalRandomInt(int min,int max){
		return min + (int)(Math.random()*(max - min));
	}
	
	public static double GaussianRandomDouble(double deviation,double mean){
		return (new Random().nextGaussian() * deviation) + mean;
	}

	public static int GaussianRandomInt(int deviation,int mean){
		return (int)(new Random().nextGaussian() * deviation) + mean;
	}
	
	public static double PerlinNoiseDouble(double start,double acceptation){
		return NormalRandomDouble(start - acceptation,start + acceptation);
	}
}
