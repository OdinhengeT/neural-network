package networks;

import java.util.function.Function;
import layers.double_based.*;

/**
 * NeuralNetworkF is a simple implementation of a neural network using matrices.
 * Uses double as basic calculation unit.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkF
 * @see LayerD
 */
public class NeuralNetworkD implements NeuralNetwork {

	/**
	 * The Sigmoid function, used as the non-linear activation function.
	 */
	protected static final Function<Double, Double> sigmoidFunction = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	/**
	 * Derivative of the Sigmoid function (above).
	 */
	protected static final Function<Double, Double> derivedSigmoidFunction = a -> sigmoidFunction.apply(a)
			* (1 - sigmoidFunction.apply(a));
	
	/**
	 * The rate of learning of the network
	 */
	private double learningRate;

	/**
	 * Number of layers and neurons per Layer in this Network
	 */
	protected int[] layerStruct;

	/**
	 * The layers (LayerD) in the NeuralNetwork.
	 */
	private LayerD[] network;

	/**
	 * Creates a NeuralNetwork with nbrLayers Layers with nbrLinks and nbrNeurons as
	 * provided by argument. The first Layer is an InputMatrixLayer, the last Layer
	 * is an OutputMatrixLayer and the Layers in between are InnerMatrixLayers. The
	 * Network has a learningRate defined in argument.
	 * 
	 * @param learningRate the NeuralNetworkF's rate of learning
	 * 
	 * @param layerStruct  an int[] with length equal to the number of Layers of the
	 *                     NeuralNetwork, and values equal to the number of Neurons
	 *                     (excluding eventual BiasNeurons)
	 */
	public NeuralNetworkD(int[] layerStruct, double learningRate) {
		this.learningRate = learningRate;
		this.layerStruct = layerStruct;
		network = new LayerD[layerStruct.length];
		network[0] = new LayerInputD(layerStruct[0], layerStruct[1]);
		for (int L = 1; L < layerStruct.length - 1; L++) {
			network[L] = new LayerInnerD(layerStruct[L], layerStruct[L + 1]);
		}
		network[layerStruct.length - 1] = new LayerOutputD(layerStruct[layerStruct.length - 1]);
	}

	/**
	 * Returns 'd' signifying that this NeuralNetwork has double as its basic
	 * calculation unit.
	 * 
	 * @return 'd'
	 */
	public char getBasicCalculationUnit() {
		return 'd';
	}

	/**
	 * The NeuralNetwork is given an input vector (double), and calculates an output
	 * vector (double) by passing its values through the NeuralNetwork.
	 * 
	 * @param input a double[] of input values to the NeuralNetwork
	 * @returns a double[] containing the output of the NeuralNetwork
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 */
	public double[] run(double[] input) {
		if (input.length != layerStruct[0]) {
			throw new IllegalArgumentException("Input of wrong length");
		}
		for (int i = 0; i < layerStruct.length; i++) {
			network[i].input(input);
			input = network[i].getOutput();
		}
		return input;
	}

	/**
	 * Trains the NeuralNetwork, using multiple vector inputs (double) and their
	 * corresponding target vectors (double), by updating the weights and biases in
	 * its MatrixLayers using backpropagation and gradient descent.
	 * 
	 * @param input,  multiple vector inputs (double) gathered in an array
	 * @param target, multiple vector targets (double) corresponding to the input
	 *                vectors in input
	 * @throws IllegalArgumentException if input and target are of different length
	 */
	public void train(double[][] input, double[][] target) {
		if (input.length != target.length) {
			throw new IllegalArgumentException("input and target not of same length");
		}

		int nbrTrainingSets = input.length;
		double[] result = new double[layerStruct[layerStruct.length - 1]];

		double[][][] deltaWeights = new double[layerStruct.length][1][1];
		double[][] deltaBias = new double[layerStruct.length][1];
		for (int layer = 0; layer < layerStruct.length - 1; layer++) {
			deltaWeights[layer] = new double[layerStruct[layer + 1]][layerStruct[layer]];
			deltaBias[layer] = new double[layerStruct[layer + 1]];
		}

		for (int trainingSet = 0; trainingSet < nbrTrainingSets; trainingSet++) {
			if (input[trainingSet].length != layerStruct[0]) {
				throw new IllegalArgumentException("input array of incorrect length");
			}
			if (target[trainingSet].length != layerStruct[layerStruct.length - 1]) {
				throw new IllegalArgumentException("target array of incorrect length");
			}

			double[][] tempBias = new double[2][1];

			result = run(input[trainingSet]);
			tempBias[1] = new double[target[trainingSet].length];
			for (int neuron = 0; neuron < target[trainingSet].length; neuron++) {
				tempBias[1][neuron] = (result[neuron] - target[trainingSet][neuron])
						* derivedSigmoidFunction.apply(network[layerStruct.length - 1].getInputTo(neuron));
			}

			for (int layer = layerStruct.length - 2; layer >= 0; layer--) {
				tempBias[0] = new double[layerStruct[layer]];

				for (int link = 0; link < layerStruct[layer + 1]; link++) {
					for (int neuron = 0; neuron < layerStruct[layer]; neuron++) {
						tempBias[0][neuron] += tempBias[1][link] * network[layer].getWeightAt(link, neuron)
								* derivedSigmoidFunction.apply(network[layer].getInputTo(neuron));

						deltaWeights[layer][link][neuron] -= tempBias[1][link]
								* sigmoidFunction.apply(network[layer].getInputTo(neuron)) * learningRate
								/ nbrTrainingSets;
					}
					deltaBias[layer][link] -= tempBias[1][link] * learningRate / nbrTrainingSets;
				}
				tempBias[1] = tempBias[0];
			}
		}

		for (int layer = 0; layer < layerStruct.length - 1; layer++) {
			network[layer].updateBias(deltaBias[layer]);
			network[layer].updateWeights(deltaWeights[layer]);
		}
	}

	/**
	 * run(float[]) is set to throw IllegalCallerException, double-based
	 * implementation.
	 * 
	 * @throws IllegalCallerException when called
	 */
	public float[] run(float[] input) {
		throw new IllegalCallerException();
	}

	/**
	 * train(float[][], float[][]) is set to throw IllegalCallerException,
	 * double-based implementation.
	 * 
	 * @throws IllegalCallerException when called
	 */
	public void train(float[][] input, float[][] target) {
		throw new IllegalCallerException();
	}
	
	/**
	 * Returns a String representation of the NeuralNetwork.
	 * 
	 * @returns a String describing the NeuralNetwork
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("==NeuralNetwork(d)==" + System.lineSeparator());
		for (int i = 0; i < layerStruct.length; i++) {
			sb.append(network[i].toString() + System.lineSeparator());
		}
		return sb.toString();
	}

}
