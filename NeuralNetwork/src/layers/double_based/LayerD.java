package layers.double_based;

import java.util.Random;
import java.util.function.Function;
import layers.MatrixOperations;

public abstract class LayerD {

	protected double[][] input;
	protected double[][] weights;
	protected double[][] bias;

	protected static Function<Double, Double> sigmoidFunction = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	public LayerD(int nbrNeurons, int nbrLinks) {
		this.input = new double[nbrNeurons][1];
		this.weights = new double[nbrLinks][nbrNeurons];
		this.bias = new double[nbrLinks][1];

		Random rand = new Random();
		for (int i = 0; i < nbrLinks; i++) {
			for (int j = 0; j < nbrNeurons; j++) {
				this.weights[i][j] = (rand.nextGaussian() / Math.sqrt(nbrNeurons));
			}
			this.bias[i][0] = (rand.nextGaussian() / Math.sqrt(nbrNeurons));
		}
	}

	public void input(double[] input) {
		if (this.input.length != input.length) {
			throw new IllegalArgumentException("Input is of wrong Dimension");
		}
		for (int i = 0; i < input.length; i++) {
			this.input[i][0] = input[i];
		}
	}

	public double getInputTo(int i) {
		return this.input[i][0];
	}

	public double getWeightAt(int i, int j) {
		return this.weights[i][j];
	}
	
	public double[][] getActivation() {
		double[][] activation = new double[this.input.length][1];
		for (int i = 0; i < this.input.length; i++) {
			activation[i][0] = sigmoidFunction.apply(this.input[i][0]);
		}
		return activation;
	}

	public abstract double[] getOutput();

	public void updateWeights(double[][] deltaWeights) {
		if (!MatrixOperations.areMatSameDimD(this.weights, deltaWeights)) {
			String errorMessage = "deltaWeights is of wrong dimension:" + System.lineSeparator() + "Expected: "
					+ weights.length + ", " + weights[0].length + " Got: " + deltaWeights.length + ", "
					+ deltaWeights[0].length;
			throw new IllegalArgumentException(errorMessage);
		}
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[i].length; j++) {
				weights[i][j] += deltaWeights[i][j];
			}
		}
	}

	public void updateBias(double[] deltaBias) {
		if (bias.length != deltaBias.length) {
			throw new IllegalArgumentException("deltaBias is of wrong length");
		}
		for (int i = 0; i < bias.length; i++) {
			bias[i][0] += deltaBias[i];
		}
	}

	public abstract String toString();

}
