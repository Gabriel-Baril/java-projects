package main;

public class Polynomial {
		static double dividedDifference(Vec2[] points) {
		  if (points.length == 1)
		    return points[0].y;

		  if (points.length == 2)
		    return (points[1].y - points[0].y)/(points[1].x - points[0].x);

		  Vec2[] p1 = new Vec2[points.length - 1];
		  Vec2[] p2 = new Vec2[points.length - 1];

		  for (int i = 0; i < points.length; i++) {
		    if (i != 0)
		      p1[i - 1] = points[i];
		    if (i != points.length - 1)
		      p2[i] = points[i];
		  }

		  return (dividedDifference(p1) - dividedDifference(p2))/(points[points.length - 1].x - points[0].x);
		}

		static double[] polynomialInterpolation(Vec2[] points) {
		  double[] result = new double[points.length];
		  Vec2[] temp;
		  for (int i = 0; i < points.length; i++) {
		    temp = new Vec2[i+1];
		    for (int j = 0; j < i+1; j++) {
		      temp[j] = points[j];
		    }
		    result[i] = dividedDifference(temp);
		  }
		  
		  String t;
		  for (int i = 0; i < points.length; i++) {
		    if (result[i] != 0) {
		      t = "";
		      System.out.print(result[i]);
		      for (int j = 0; j < i; j++) {
		        t += (points[j].x != 0)?"(x" + ((points[j].x < 0)?"+":"-") + ((points[j].x < 0)?-points[j].x:points[j].x) + ")":"(x)";
		      }
		      System.out.print(t);
		      if (i < points.length - 1)
		        System.out.print(" + ");
		    }
		  }
		  return result;
		}
		
		static double[][] polynomialRegression(Vec2[] points,int power){
			if(points.length == 1)
				return new double[][]{ {points[0].y} };
			if(points.length == 2)
				return new double[][]{ {points[0].y - ((points[1].y - points[0].y)/(points[1].x - points[0].x))*points[0].x}, {(points[1].y - points[0].y)/(points[1].x - points[0].x)} };
			
			double[][] matrix_X = new double[points.length][power];
			double[][] matrix_Y = new double[points.length][1];
			
			for(int i = 0;i < points.length;i++){
				for(int j = 0;j < power;j++){
					matrix_X[i][j] = Math.pow(points[i].x,j);
				}
			}
			
			for(int i = 0;i < points.length;i++){
				matrix_Y[i][0] = points[i].y;
			}
			
			return Matrix.mult(Matrix.mult(Matrix.inverse(Matrix.mult(Matrix.transpose(matrix_X), matrix_X)), Matrix.transpose(matrix_X)), matrix_Y);
		}
}
