package layers.matrixLayers;

import java.util.Random;
import java.util.function.Function;

public abstract class MatrixLayer {

	protected float[][] input;
	protected float[][] weights;
	protected float[][] bias;

	protected static Function<Float, Float> sigFun = a -> 1 / (1 + (float) Math.exp(-1 * a));

	public MatrixLayer(int nbrNeurons, int nbrLinks) {
		this.input = new float[nbrNeurons][1];
		this.weights = new float[nbrLinks][nbrNeurons];
		this.bias = new float[nbrLinks][1];

		Random rand = new Random();
		for (int i = 0; i < nbrLinks; i++) {
			for (int j = 0; j < nbrNeurons; j++) {
				this.weights[i][j] = (float) (rand.nextGaussian() / Math.sqrt((float) nbrNeurons));
			}
			this.bias[i][0] = (float) (rand.nextGaussian() / Math.sqrt((float) nbrNeurons));
		}
	}
	
	public int getNbrNeurons() {
		return this.input.length;
	}
	
	public int getNbrLinks() {
		return this.weights.length;
	}
	
	public void input(float[] input) {
		if (this.input.length != input.length) {
			throw new IllegalArgumentException("Input is of wrong Dimension");
		} 
		for (int i = 0; i < input.length; i++) {
			this.input[i][0] = input[i];
		}
	}
	
	public abstract float[] getOutput();
	
	public void updateWeights(float[][] deltaWeights) {
		if (!matrixIsOfSameDimension(this.weights, deltaWeights)) {
			throw new IllegalArgumentException("deltaWeights is of wrong dimension");
		}
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[i].length; j++) {
				weights[i][j] += deltaWeights[i][j];
			}
		}
	}
	
	public void updateBias(float[][] deltaBias) {
		if (!matrixIsOfSameDimension(this.weights, deltaBias)) {
			throw new IllegalArgumentException("deltaBias is of wrong dimension");
		}
		for (int i = 0; i < bias.length; i++) {
				bias[i][0] += deltaBias[i][0];
		}
	}
	
	public abstract String toString();

	protected static float[][] matrixMult(float[][] m1, float[][] m2) {
		if (!matricesAreMultiplyable(m1, m2)) {
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
	
	protected static boolean matrixIsOfSameDimension(float[][] m1, float[][] m2) {
		if (m1.length != m2.length) return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length) return false;
		}
		return true;
	}
	
	protected static boolean matricesAreMultiplyable(float[][] m1, float[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (m2.length != m1[i].length) return false;
		}
		return true;
	}
	
}
