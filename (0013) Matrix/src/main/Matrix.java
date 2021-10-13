package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
	
	public static void rotateX(double angle,Vec2 v){
		double[][] rotMatrix = {{1,0,0},
				      			{0,Math.cos(angle),-Math.sin(angle)},
				      			{0,Math.sin(angle),Math.cos(angle)}};
		//return Matrix.mult(rotMatrix, m2);
	}
	
	public static void logMatrix(double[][] matrix){
		if(matrix == null){
			System.out.println("The matrix is null");
			return;
		}

		for(int i = 0;i < matrix.length;i++){
			for(int j = 0;j < matrix[i].length;j++){
				if((j == 0) && (j == matrix[i].length-1))
					System.out.print("[" + matrix[i][j] + "]");
				else if(j == 0)
					System.out.print("[" + matrix[i][j] + ",");
				else if(j == matrix[i].length-1)
					System.out.print(matrix[i][j] + "]");
				else
					System.out.print(matrix[i][j] + ",");

			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static double[] ArrayListToArray(ArrayList<Double> arr){
		double[] result = new double[arr.size()];
		for(int i = 0;i < arr.size();i++){
			result[i] = arr.get(i);
		}
		return result;
	}


	public static double[][] uniArrayToMatrix(double[] tab,int rowSize,int colSize){
		if(tab.length != rowSize*colSize)
			return null;

		double[][] matrix = new double[rowSize][colSize];
		for(int i = 0;i < rowSize;i++){
			for(int j = 0;j < colSize;j++){
				matrix[i][j] = tab[i*colSize + j];
			}
		}
		return matrix;
	}
	
	public static double[][] adjugate(double[][] matrix){
		if(matrix == null || !isUniform(matrix) || !isSquare(matrix))
			return null;
		int row = matrix.length;
		int col = matrix[0].length;
		double[][] comatrice = new double[row][col];

		for(int i = 0;i < row;i++){
			for(int j = 0;j < col;j++){
				ArrayList<Double> temp = new ArrayList<Double>();	 
				for(int k = 0;k < row;k++){
					for(int v = 0;v < col;v++){
						if(k != i && v != j){
							temp.add(matrix[k][v]);
						}
					}
				}
				if((i + j)%2 == 0)
					comatrice[i][j] = det(uniArrayToMatrix(ArrayListToArray(temp),col-1,col-1));
				else
					comatrice[i][j] = -det(uniArrayToMatrix(ArrayListToArray(temp),col-1,col-1));
			}
		}
		return comatrice;
	}
	
	public static double[][] inverse(double[][] matrix){
		return multScalar(1/det(matrix),transpose(adjugate(matrix)));
	}
	
	public static double[][] transpose(double[][] matrix){
		if(matrix == null || !isUniform(matrix))
			return null;
		int row = matrix.length;
		int col = matrix[0].length;
		double[][] result = new double[col][row];
		for(int i = 0;i < row;i++){
			for(int j = 0;j < col;j++){
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}

	public static double det(double[][] matrix) {
		if(matrix == null){
			System.out.println("The matrix is null in function : (det)");
			return 0;
		}

		double temporary[][];
		double result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return (result);
		}

		if (matrix.length == 2) {
			result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temporary = new double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temporary[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temporary[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (double) i) * det(temporary);
		}
		return (result);
	}

	public static double[][] add(double[][] m1,double[][] m2){
		if(SomethingNulls(m1,m2))
			return null;
		if(!sameSize(m1,m2)){
			System.out.println("Matrix addition not allowed between these 2 matrix : The two matrices must be of the same dimension");
			return null;
		}

		double[][] result = new double[m1.length][m1[0].length];

		for(int i = 0;i < m1.length;i++){
			for(int j = 0;j < m1[0].length;j++){
				result[i][j] = m1[i][j] + m2[i][j];
			}
		}
		return result;
	}

	public static double[][] sub(double[][] m1,double[][] m2){
		if(SomethingNulls(m1,m2))
			return null;
		if(!sameSize(m1,m2)){
			System.out.println("Matrix substraction not allowed between these 2 matrix : The two matrices must be of the same dimension");
			return null;
		}

		double[][] result = new double[m1.length][m1[0].length];

		for(int i = 0;i < m1.length;i++){
			for(int j = 0;j < m1[0].length;j++){
				result[i][j] = m1[i][j] - m2[i][j];
			}
		}
		return result;
	}

	public static double[][] mult(double[][] m1,double[][] m2){
		if(SomethingNulls(m1,m2))
			return null;
		if(!(m1[0].length == m2.length && isUniform(m1) && isUniform(m2))){
			System.out.println("Matrix multiplication not allowed between these 2 matrix : The two matrices must have the same number of rows and columns");
			return null;
		}

		int colM1 = m1[0].length;
		int rowM1 = m1.length;
		int colM2 = m2[0].length;

		double[][] result = new double[rowM1][colM2];

		for(int i = 0;i < rowM1;i++){
			for(int j = 0;j < colM2;j++){
				for(int k = 0;k < colM1;k++){
					result[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return result;
	}
	
	public static double[][] div(double[][] m1,double[][] m2){
		return mult(m1, inverse(m2));
	}
	
	public static double[][] pow(double[][] matrix,int power){
		if(!isUniform(matrix) || !isSquare(matrix) || SomethingNulls(matrix))
			return null;
		
		double[][] result = matrix;
		if(power == -1)
			return inverse(matrix);
		else if(power < -1){
			double[][] inv = inverse(matrix);
			result = inverse(matrix);

			for(int i = 1;i < -power;i++){
				result = mult(result,inv);
			}
			return result;
		}else if(power == 0){
			return identity(matrix.length);
		}

		for(int i = 1;i < power;i++){
			result = mult(result,matrix);
		}
		
		return result;
	}
	
	
	
	public static double[][] identity(int size){
		double[][] result = new double[size][size];
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++){
				result[i][j] = (i == j) ? 1 : 0; 
			}
		}
		return result;
	}
	
	public static double[][] multScalar(double scalar,double[][] matrix){
		if(matrix == null || !isUniform(matrix))
			return null;
		int col = matrix[0].length;
		int row = matrix.length;
		double[][] result = new double[row][col];
		for(int i = 0;i < row;i++){
			for(int j = 0;j < col;j++){
				result[i][j] = scalar * matrix[i][j];
			}
		}
		return result;
	}

	public static boolean isUniform(double[][] mat){
		int ref = mat[0].length;
		for(int i = 1;i < mat.length;i++){
			if(mat[i].length != ref){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSquare(double[][] matrix){
		if(matrix.length == matrix[0].length && isUniform(matrix))
			return true;
		return false;
	}

	public static boolean sameSize(double[][] m1,double[][] m2){
		if(m1 == null || m2 == null)
			return false;
		if(isUniform(m1) && isUniform(m2) && m1.length == m2.length && m1[0].length == m2[0].length)
			return true;
		return false;
	}

	public static boolean SomethingNulls(double[][]...ds){
		for(int i = 0;i < ds.length;i++){
			if(ds[i] == null){
				return true;
			}
		}
		return false;
	}
}
