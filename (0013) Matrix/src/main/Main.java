package main;

import java.lang.reflect.Method;

public class Main {
	
	
	public static int sup_vente(int[] finance,int value){
		int vente = 0;
		for (int i = 0; i < finance.length;i++) {
			if (finance[i] > value) {
				vente = vente + 1;
			}
		}
		return vente;
	}

	public static void main(String[] args){
		double[][] mat1 = { {34,2,3,6,7,87,9,8,78,34,2,3,6,7,87,9,8,78},
							{34,2,345,6,7,8,9,8,78,34,2,3,6,7,87,9,8,78},
							{342,2,3,6123,7,86,9,8123,785,342,2,3,6123,7,86,9,8123,785},
							{34,2123,3,65,7,83,9,8,758,342,2,3,6123,7,86,9,8123,785},
							{341,2,33,6,7,8321,9,8,78,342,2,3,6123,7,86,9,8123,785},
							{34,23,3,64,73,8,9,83,78,342,2,3,6123,7,86,9,8123,785},
							{343,2,13,6,7,8,9,8,785,342,2,3,6123,7,86,9,8123,785},
							{34,223,3,6,7,82,9,85,78,342,2,3,6123,7,86,9,8123,785},
							{344,2,1233,6,7,8,9,8,78,342,2,3,6123,7,86,9,8123,785},
							{34,2,3,6,7,87,9,8,78,34,2,3,6,7,87,9,8,78},
							{34,2,345,6,7,8,9,8,78,34,2,3,6,7,87,9,8,78},
							{342,2,3,6123,7,86,9,8123,785,342,2,3,6123,7,86,9,8123,785},
							{34,2123,3,65,7,83,9,8,758,342,2,3,6123,7,86,9,8123,785},
							{341,2,33,6,7,8321,9,8,78,342,2,3,6123,7,86,9,8123,785},
							{34,23,3,64,73,8,9,83,78,342,2,3,6123,7,86,9,8123,785},
							{343,2,13,6,7,8,9,8,785,342,2,3,6123,7,86,9,8123,785},
							{34,223,3,6,7,82,9,85,78,342,2,3,6123,7,86,9,8123,785},
							{344,2,1233,6,7,8,9,8,78,342,2,3,6123,7,86,9,8123,785} };
		System.out.println(Matrix.det(mat1));
	}
}
