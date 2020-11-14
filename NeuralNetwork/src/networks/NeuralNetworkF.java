package networks;

import java.util.function.Function;
import layers.float_based.*;

/**
 * NeuralNetworkF is a simple implementation of a neural network using matrices.
 * Uses float as basic calculation unit.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkD
 * @see LayerF
 */
public class NeuralNetworkF implements NeuralNetwork {

	/**
	 * The Sigmoid function, used as the non-linear activation function.
	 */
	protected static final Function<Float, Float> sigmoidFunction = a -> 1.0f / (1.0f + (float) Math.exp(-1 * a));

	/**
	 * Derivative of the Sigmoid function (above).
	 */
	protected static final Function<Float, Float> derivedSigmoidFunction = a -> sigmoidFunction.apply(a)
			* (1 - sigmoidFunction.apply(a));

	/**
	 * The rate of learning of the network
	 */
	private float learningRate;

	/**
	 * Number of layers and neurons per Layer in this Network
	 */
	protected int[] layerStruct;

	/**
	 * The layers (LayerF) in the NeuralNetwork.
	 */
	private LayerF[] network;

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
	public NeuralNetworkF(int[] layerStruct, float learningRate) {
		this.learningRate = learningRate;
		this.layerStruct = layerStruct;
		network = new LayerF[layerStruct.length];
		network[0] = new LayerInputF(layerStruct[0], layerStruct[1]);
		for (int L = 1; L < layerStruct.length - 1; L++) {
			network[L] = new LayerInnerF(layerStruct[L], layerStruct[L + 1]);
		}
		network[layerStruct.length - 1] = new LayerOutputF(layerStruct[layerStruct.length - 1]);
	}

	/**
	 * Returns 'f' signifying that this NeuralNetwork has float as its basic
	 * calculation unit.
	 * 
	 * @return 'f'
	 */
	public char getBasicCalculationUnit() {
		return 'f';
	}

	/**
	 * The NeuralNetwork is given an input vector (float), and calculates an output
	 * vector (float) by passing its values through the NeuralNetwork.
	 * 
	 * @param input a float[] of input values to the NeuralNetwork
	 * @returns a float[] containing the output of the NeuralNetwork
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 */
	public float[] run(float[] input) {
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
	 * Trains the NeuralNetwork, using multiple vector inputs (float) and their
	 * corresponding target vectors (float), by updating the weights and biases in
	 * its MatrixLayers using backpropagation and gradient descent.
	 * 
	 * @param input,  multiple vector inputs (float) gathered in an array
	 * @param target, multiple vector targets (float) corresponding to the input
	 *                vectors in input
	 * @throws IllegalArgumentException if input and target are of different length
	 */
	public void train(float[][] input, float[][] target) {
		if (input.length != target.length) {
			throw new IllegalArgumentException("input and target not of same length");
		}

		int nbrTrainingSets = input.length;
		float[] result = new float[layerStruct[layerStruct.length - 1]];

		float[][][] deltaWeights = new float[layerStruct.length][1][1];
		float[][] deltaBias = new float[layerStruct.length][1];
		for (int layer = 0; layer < layerStruct.length - 1; layer++) {
			deltaWeights[layer] = new float[layerStruct[layer + 1]][layerStruct[layer]];
			deltaBias[layer] = new float[layerStruct[layer + 1]];
		}

		for (int trainingSet = 0; trainingSet < nbrTrainingSets; trainingSet++) {
			if (input[trainingSet].length != layerStruct[0]) {
				throw new IllegalArgumentException("input array of incorrect length");
			}
			if (target[trainingSet].length != layerStruct[layerStruct.length - 1]) {
				throw new IllegalArgumentException("target array of incorrect length");
			}

			float[][] tempBias = new float[2][1];

			result = run(input[trainingSet]);
			tempBias[1] = new float[target[trainingSet].length];
			for (int neuron = 0; neuron < target[trainingSet].length; neuron++) {
				tempBias[1][neuron] = (result[neuron] - target[trainingSet][neuron])
						* derivedSigmoidFunction.apply(network[layerStruct.length - 1].getInputTo(neuron));
			}

			for (int layer = layerStruct.length - 2; layer >= 0; layer--) {
				tempBias[0] = new float[layerStruct[layer]];

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
	 * run(double[]) is set to throw IllegalCallerException, float-based
	 * implementation.
	 * 
	 * @throws IllegalCallerException when called
	 */
	public double[] run(double[] input) {
		throw new IllegalCallerException();
	}

	/**
	 * train(double[][], double[][]) is set to throw IllegalCallerException,
	 * float-based implementation.
	 * 
	 * @throws IllegalCallerException when called
	 */
	public void train(double[][] input, double[][] target) {
		throw new IllegalCallerException();
	}

	/**
	 * Returns a String representation of the NeuralNetwork.
	 * 
	 * @returns a String describing the NeuralNetwork
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("==NeuralNetwork(f)==" + System.lineSeparator());
		for (int i = 0; i < layerStruct.length; i++) {
			sb.append(network[i].toString() + System.lineSeparator());
		}
		return sb.toString();
	}

}
