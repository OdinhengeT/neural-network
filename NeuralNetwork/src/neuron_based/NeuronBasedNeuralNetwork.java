package neuron_based;

import java.util.function.Function;

import neuron_based.layers.*;

/**
 * A Precursor to NeuralNetwork using instances of the Neuron class and its
 * subclasses instead of matrices. It is implemented with Double as the basic
 * calculation unit.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see NeuronLayer
 * @see Neuron
 */
public class NeuronBasedNeuralNetwork {

	/**
	 * The rate of learning of the network
	 */
	private static final double LEARNING_RATE = 0.8;

	/**
	 * Number of Layers in this Network
	 */
	protected int[] layerStruct;

	/**
	 * The Layers in this network
	 */
	private NeuronLayer[] network;

	/**
	 * The Sigmoid function (Double to Double).
	 */
	protected static Function<Double, Double> sigmoidFunction = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	/**
	 * Derivative of the Sigmoid function (Double to Double).
	 */
	protected static Function<Double, Double> derivedSigmoidFunction = a -> sigmoidFunction.apply(a)
			* (1 - sigmoidFunction.apply(a));

	/**
	 * Creates a NeuronBasedNeuralNetwork with nbrLayers Layers with nbrLinks and
	 * nbrNeurons as provided by argument. The first Layer is an InputLayer, the
	 * last Layer is an OutputLayer and the Layers in between are InnerLayers.
	 * 
	 * @param layerStruct an int[] with length equal to the number of Layers the
	 *                    Network should have, and values equal to the number of
	 *                    Neurons (excluding eventual BiasNeurons)
	 */
	public NeuronBasedNeuralNetwork(int[] layerStruct) {
		this.layerStruct = layerStruct;
		network = new NeuronLayer[layerStruct.length];
		network[0] = new InputNeuronLayer(layerStruct[0], layerStruct[1]);
		for (int L = 1; L < layerStruct.length - 1; L++) {
			network[L] = new InnerNeuronLayer(layerStruct[L], layerStruct[L + 1]);
		}
		network[layerStruct.length - 1] = new OutputNeuronLayer(layerStruct[layerStruct.length - 1]);
	}

	/**
	 * Returns all the networks Layers in a Layer[].
	 * 
	 * @returns network Layer[] with the Networks Layers.
	 */
	public NeuronLayer[] getLayers() {
		return network;
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
		if (input.length != network[0].getNbrNeurons()) {
			throw new IllegalArgumentException("Network input of incorrect dimension");
		}
		for (int L = 0; L < layerStruct.length; L++) {
			network[L].input(input);
			input = network[L].getOutput();
		}
		return network[layerStruct.length - 1].getOutput();
	}

	/**
	 * Trains the NeuralNetwork, using multiple vector inputs (double) and their
	 * corresponding target vectors (double), by updating the weights and biases of
	 * its Neurons using backpropagation and gradient descent.
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

		int nbrTrainingCases = input.length;

		double[][][] dW = new double[layerStruct.length][1][1];
		for (int layer = 0; layer < dW.length - 1; layer++) {
			dW[layer] = new double[network[layer].getNbrNeurons() + 1][network[layer].getNbrLinks()];
		}
		dW[dW.length - 1] = new double[network[dW.length - 1].getNbrNeurons()][1];

		for (int trainingSet = 0; trainingSet < nbrTrainingCases; trainingSet++) {

			double[][] dE = error(input[trainingSet], target[trainingSet]);

			for (int layer = 0; layer < layerStruct.length - 1; layer++) {
				for (int neuron = 0; neuron <= network[layer].getNbrNeurons(); neuron++) {
					for (int link = 0; link < network[layer].getNbrLinks(); link++) {
						double temp = dE[layer + 1][link] * network[layer].getNeuronAt(neuron).getOutput();
						dW[layer][neuron][link] -= temp * LEARNING_RATE / nbrTrainingCases;
					}
				}
			}
		}

		for (int layer = 0; layer < layerStruct.length - 1; layer++) {
			for (int neuron = 0; neuron < network[layer].getNbrNeurons() + 1; neuron++) {
				network[layer].updateWeights(dW[layer]);
			}
		}

	}

	/**
	 * Help method used in the train method. Returns a matrix with each element
	 * containing the derivative of the error function with respect to the input of
	 * each Neuron. Note that this matrix is not square, but contains as many rows
	 * as there are Layers in the network and as many columns as the current Layer
	 * has.
	 * 
	 * @param input  input vector containing input values
	 * @param target target vector containing target values
	 * @returns a non-square matrix containing the derivatives of the error function
	 *          with respect to the input of each Neuron
	 * @throws IllegalArgumentException if length of input or target vectors isn't
	 *                                  equal to the number of Input/Output Neurons
	 */
	private double[][] error(double[] input, double[] target) {
		if (input.length != network[0].getNbrNeurons()) {
			throw new IllegalArgumentException("input array of incorrect length");
		}
		if (target.length != network[layerStruct.length - 1].getNbrNeurons()) {
			throw new IllegalArgumentException("target array of incorrect length");
		}

		double[][] dE = new double[layerStruct.length][1];
		double[] result = run(input);

		// OutputLayer, calculated first as the error backpropagates.
		double[] outdE = new double[target.length];

		for (int neuron = 0; neuron < target.length; neuron++) {
			double temp = derivedSigmoidFunction.apply(network[layerStruct.length - 1].getNeuronAt(neuron).getInput());
			outdE[neuron] = (result[neuron] - target[neuron]) * temp;
		}

		dE[layerStruct.length - 1] = outdE;

		// InnerLayers
		for (int layer = layerStruct.length - 2; layer >= 0; layer--) {
			dE[layer] = new double[network[layer].getNbrNeurons()];

			for (int neuron = 0; neuron < network[layer].getNbrNeurons(); neuron++) {
				for (int link = 0; link < network[layer].getNbrLinks(); link++) {

					dE[layer][neuron] += dE[layer + 1][link] * network[layer].getNeuronAt(neuron).getWeightTo(link);
				}

				dE[layer][neuron] *= derivedSigmoidFunction.apply(network[layer].getNeuronAt(neuron).getInput());
			}
		}
		return dE;
	}

	/**
	 * Returns a String representation of the Network.
	 * 
	 * @returns a String describing the Network
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("==Network==");
		for (NeuronLayer l : network) {
			sb.append(System.lineSeparator());
			sb.append(l.toString());
		}
		return sb.toString();
	}

}