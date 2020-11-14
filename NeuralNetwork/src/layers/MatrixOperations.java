package layers;

/**
 * Simple class defining some simple matrix operations such as matrix
 * multiplication (dot-product) of two float[][] or two double[][[]
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see LayerF
 * @see LayerD
 *
 */
public class MatrixOperations {

	/**
	 * Checks if two float[][] (or float matrices) are of the exact same shape
	 * 
	 * @param m1 float[][] "matrix" number 1
	 * @param m2 float[][] "matrix" number 2
	 * @return true if m1 and m2 are of the exact same shape, else false
	 */
	public static boolean areMatSameDimF(float[][] m1, float[][] m2) {
		if (m1.length != m2.length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length)
				return false;
		}
		return true;
	}

	/**
	 * Checks if two double[][] (or double matrices) are of the exact same shape
	 * 
	 * @param m1 double[][] "matrix" number 1
	 * @param m2 double[][] "matrix" number 2
	 * @return true if m1 and m2 are of the exact same shape, else false
	 */
	public static boolean areMatSameDimD(double[][] m1, double[][] m2) {
		if (m1.length != m2.length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length)
				return false;
		}
		return true;
	}

	/**
	 * Checks if the float[][] (float matrices) are dot multipliable (in the m1 * m2
	 * order)
	 * 
	 * @param m1 float[][] "matrix" number 1
	 * @param m2 float[][] "matrix" number 2
	 * @return true if m1 and m2 multipliable (in the m1 * m2 order)
	 */
	public static boolean areMatMultF(float[][] m1, float[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (m2.length != m1[i].length)
				return false;
		}
		return true;
	}

	/**
	 * Checks if the double[][] (double matrices) are dot multipliable (in the m1 *
	 * m2 order)
	 * 
	 * @param m1 double[][] "matrix" number 1
	 * @param m2 double[][] "matrix" number 2
	 * @return true if m1 and m2 multipliable (in the m1 * m2 order)
	 */
	public static boolean areMatMultD(double[][] m1, double[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (m2.length != m1[i].length)
				return false;
		}
		return true;
	}

	/**
	 * Multiplies m1 with m2 (in that order) if possible, throws
	 * IllegalArgumentException if not.
	 * 
	 * @param m1 float[][] "matrix" number 1
	 * @param m2 float[][] "matrix" number 2
	 * @return result of m1 * m2
	 * @throws IllegalArgumentException if m1 * m2 is not defined
	 */
	public static float[][] matMultF(float[][] m1, float[][] m2) {
		if (!areMatMultF(m1, m2)) {
			throw new IllegalArgumentException(
					"The two matrices do not have a defined dot-product in the given multiplication order");
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

	/**
	 * Multiplies m1 with m2 (in that order) if possible, throws
	 * IllegalArgumentException if not.
	 * 
	 * @param m1 double[][] "matrix" number 1
	 * @param m2 double[][] "matrix" number 2
	 * @return result of m1 * m2
	 * @throws IllegalArgumentException if m1 * m2 is not defined
	 */
	public static double[][] matMultD(double[][] m1, double[][] m2) {
		if (!areMatMultD(m1, m2)) {
			throw new IllegalArgumentException(
					"The two matrices do not have a defined dot-product in the given multiplication order");
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
