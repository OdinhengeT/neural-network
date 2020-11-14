package layers.double_based;

import java.util.Random;
import java.util.function.Function;
import layers.MatrixOperations;

/**
 * This abstract class heavily outlines the functionality of a layer in a
 * NeuralNetwork, using double as its basic calculation unit. The implementation
 * is based on expressing the weights & biases as matrices instead of as
 * instances of some 'Neuron' class.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetworkD
 * @see LayerInnerD
 * @see LayerInputD
 * @see LayerOutputD
 * @see MatrixOperations
 */
public abstract class LayerD {
	/**
	 * Input given to the layer, in reallity a colon-vector, but expressed as a
	 * double[][] so it can be used in methods defined by the MatrixOperations class.
	 */
	protected double[][] input;
	/**
	 * Weights linking this layer to the next one in the network. Arranged so
	 * weights on the same row link to the same neuron in the next layer.
	 */
	protected double[][] weights;
	/**
	 * Biases of the neurons in the next layer in the network, in reallity a
	 * colon-vector, but expressed as a double[][] so it can be used in methods
	 * defined by the MatrixOperations class.
	 */
	protected double[][] bias;

	/**
	 * The Sigmoid function, used as the non-linear activation function.
	 */
	protected static Function<Double, Double> sigmoidFunction = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	/**
	 * Constructs a LayerD with nbrNeurons neurons and nbrLinks links (the next
	 * layer in the network has nbrLinks neurons). Initiates all the weights and
	 * biases with random values (following a gaussian distribution with mean 0 and
	 * standard deviation 1). these random values are then devided by the square
	 * root of the number of neurons in the layer.
	 * 
	 * @param nbrNeurons number of neurons
	 * @param nbrLinks   number of links
	 */
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
	
	/**
	 * Gives the layer a new input.
	 * 
	 * @param input the vectorized input assigned to the layer
	 * @throws IllegalArgumentException if input is of incorrect length
	 */
	public void input(double[] input) {
		if (this.input.length != input.length) {
			throw new IllegalArgumentException("Input is of wrong Dimension");
		}
		for (int i = 0; i < input.length; i++) {
			this.input[i][0] = input[i];
		}
	}
	
	/**
	 * Returns the input given to the neuron at neuron.
	 * 
	 * @param neuron the index of the neuron of interest
	 * @return last assigned input to said neuron
	 */
	public double getInputTo(int neuron) {
		return this.input[neuron][0];
	}

	/**
	 * Returns the weight that connects the neuron at index neuron in this layer
	 * with the neuron at index link in the next layer.
	 * 
	 * @param link   index of neuron in next layer
	 * @param neuron index of neuron in this layer
	 * @return weight from neuron to link
	 */
	public double getWeightAt(int link, int neuron) {
		return this.weights[link][neuron];
	}

	/**
	 * Applies the Activation function (Sigmoid function) to each input.
	 * 
	 * @return activation of each neuron in layer
	 */
	public double[][] getActivation() {
		double[][] activation = new double[this.input.length][1];
		for (int i = 0; i < this.input.length; i++) {
			activation[i][0] = sigmoidFunction.apply(this.input[i][0]);
		}
		return activation;
	}

	/**
	 * Updates the weights of the layer by amount specified in argument.
	 * 
	 * @param deltaWeights amount to nudge each weight
	 * @throws IllegalArgumentException if deltaWeights not of same dimension as
	 *                                  this.weights
	 */
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

	/**
	 * Updates the biases to the next layer by amount specified in argument.
	 * 
	 * @param deltaBias amount to nudge each bias
	 * @throws IllegalArgumentException if deltaBias not of same length as this.bias
	 */
	public void updateBias(double[] deltaBias) {
		if (bias.length != deltaBias.length) {
			throw new IllegalArgumentException("deltaBias is of wrong length");
		}
		for (int i = 0; i < bias.length; i++) {
			bias[i][0] += deltaBias[i];
		}
	}

	/**
	 * Returns the vectorized output of this layer.
	 * 
	 * @return output of layer
	 */
	public abstract double[] getOutput();
	
	/**
	 * Returns a string representation of this layer.
	 * 
	 * @return String representation of layer
	 */
	public abstract String toString();

}
