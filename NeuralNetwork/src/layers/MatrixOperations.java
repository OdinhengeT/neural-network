package layers;

public class MatrixOperations {

	public static boolean areMatSameDimF(float[][] m1, float[][] m2) {
		if (m1.length != m2.length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length)
				return false;
		}
		return true;
	}

	public static boolean areMatMultF(float[][] m1, float[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (m2.length != m1[i].length)
				return false;
		}
		return true;
	}
	
	public static float[][] matMultF(float[][] m1, float[][] m2) {
		if (!areMatMultF(m1, m2)) {
			throw new IllegalArgumentException();
		}
		float[][] result = new float[m1.length][m2[0].length];
		float temp = 0;
		for (int row_m1 = 0; row_m1 < m1.length; row_m1++) {
			for (int col_m2 = 0; col_m2 < m2[0].length; col_m2++) {
				temp = 0;
				for (int i = 0; i < m2.length; i++) {
					temp += m1[row_m1][i] * m2[i][col_m2];
				}
				result[row_m1][col_m2] = temp;
			}
		}
		return result;
	}
	
	public static boolean areMatSameDimD(double[][] m1, double[][] m2) {
		if (m1.length != m2.length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length)
				return false;
		}
		return true;
	}

	public static boolean areMatMultD(double[][] m1, double[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (m2.length != m1[i].length)
				return false;
		}
		return true;
	}
	
	public static double[][] matMultD(double[][] m1, double[][] m2) {
		if (!areMatMultD(m1, m2)) {
			throw new IllegalArgumentException();
		}
		double[][] result = new double[m1.length][m2[0].length];
		double temp = 0;
		for (int row_m1 = 0; row_m1 < m1.length; row_m1++) {
			for (int col_m2 = 0; col_m2 < m2[0].length; col_m2++) {
				temp = 0;
				for (int i = 0; i < m2.length; i++) {
					temp += m1[row_m1][i] * m2[i][col_m2];
				}
				result[row_m1][col_m2] = temp;
			}
		}
		return result;
	}
	
	
}
