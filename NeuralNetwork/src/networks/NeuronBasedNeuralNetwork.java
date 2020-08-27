package networks;

import neuronBasedLayers.*;

/**
 * Implementation of the abstract class NeuralNetwork using instances of the
 * Neuron class and its subclasses.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see Layer
 * @see Neuron
 */
public class NeuronBasedNeuralNetwork extends NeuralNetwork {

	/**
	 * The rate of learning of the network
	 */
	private static final double LEARNING_RATE = 0.8;

	/**
	 * The Layers in this network
	 */
	private Layer[] network;

	/**
	 * Creates a NeuronBasedNeuralNetwork with nbrLayers Layers with nbrLinks and
	 * nbrNeurons as provided by argument. The first Layer is an InputLayer, the
	 * last Layer is an OutputLayer and the Layers in between are InnerLayers.
	 * 
	 * @param layers an int[] with length equal to the number of Layers the Network
	 *               should have, and values equal to the number of Neurons
	 *               (excluding eventual BiasNeurons)
	 */
	public NeuronBasedNeuralNetwork(int[] layers) {
		super(layers.length);
		network = new Layer[nbrLayers];
		network[0] = new InputLayer(layers[0], layers[1]);
		for (int L = 1; L < nbrLayers - 1; L++) {
			network[L] = new InnerLayer(layers[L], layers[L + 1]);
		}
		network[nbrLayers - 1] = new OutputLayer(layers[nbrLayers - 1]);
	}

	/**
	 * Returns all the networks Layers in a Layer[].
	 * 
	 * @returns network Layer[] with the Networks Layers.
	 */
	public Layer[] getLayers() {
		return network;
	}

	/**
	 * The NeuralNetwork is given an input vector and by passing its values through
	 * the NeuralNetwork, calculates an output vector.
	 * 
	 * @param input a double[] of inputvalues (one for each InputNeuron)
	 * @returns a double[] containing the output of each OutputNeuron
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 */
	public double[] run(double[] input) {
		if (input.length != network[0].getNbrNeurons()) {
			throw new IllegalArgumentException("Network input of incorrect dimension");
		}
		for (int L = 0; L < nbrLayers; L++) { // Loopar igenom nätverkets lager.
			network[L].input(input); // inputar input till lager L.
			input = network[L].getOutput(); // sätter input till outputen av lager L.
		}
		return network[nbrLayers - 1].getOutput();
	}

	/**
	 * Trains the network with multiple inputs (each being a vector) and their
	 * corresponding targets (also a vector each), by updating the weights of the
	 * Neurons in the NeuralNetwork using gradient descent.
	 * 
	 * @param input  multiple input vectors put together in an array
	 * @param target multiple target vectors corresponding to those in input
	 * @throws IllegalArgumentException if input and target are of different length
	 */
	public void train(double[][] input, double[][] target) {
		if (input.length != target.length) {
			throw new IllegalArgumentException("input and target not of same length");
		}

		int nbrTrainingCases = input.length;

		double[][][] dW = new double[nbrLayers][1][1];
		for (int layer = 0; layer < dW.length - 1; layer++) {
			dW[layer] = new double[network[layer].getNbrNeurons() + 1][network[layer].getNbrLinks()];
		}
		dW[dW.length - 1] = new double[network[dW.length - 1].getNbrNeurons()][1];

		for (int trainingSet = 0; trainingSet < nbrTrainingCases; trainingSet++) {

			double[][] dE = error(input[trainingSet], target[trainingSet]);

			for (int layer = 0; layer < nbrLayers - 1; layer++) {
				for (int neuron = 0; neuron <= network[layer].getNbrNeurons(); neuron++) {
					for (int link = 0; link < network[layer].getNbrLinks(); link++) {
						double temp = dE[layer + 1][link] * network[layer].getNeuronAt(neuron).getOutput();
						dW[layer][neuron][link] -= temp * LEARNING_RATE / nbrTrainingCases;
					}
				}
			}
		}

		for (int layer = 0; layer < nbrLayers - 1; layer++) {
			for (int neuron = 0; neuron < network[layer].getNbrNeurons() + 1; neuron++) {
				network[layer].updateWeights(dW[layer]);
			}
		}

	}

	/**
	 * Help method used in the train method. Returns a matrix with each element
	 * containing the derivative of the error function with respect to each Neuron.
	 * Note that this matrix is not square, but contains as many rows as there are
	 * Layers in the network and as many columns as the current Layer has.
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
		if (target.length != network[nbrLayers - 1].getNbrNeurons()) {
			throw new IllegalArgumentException("target array of incorrect length");
		}

		double[][] dE = new double[nbrLayers][1];
		double[] result = run(input);

		// OutputLayer, calculated first as the error backpropagates.
		double[] outdE = new double[target.length];

		for (int neuron = 0; neuron < target.length; neuron++) {
			double temp = DsigFun.apply(network[nbrLayers - 1].getNeuronAt(neuron).getInput());
			outdE[neuron] = (result[neuron] - target[neuron]) * temp;
		}

		dE[nbrLayers - 1] = outdE;

		// InnerLayers
		for (int layer = nbrLayers - 2; layer >= 0; layer--) {
			dE[layer] = new double[network[layer].getNbrNeurons()];

			for (int neuron = 0; neuron < network[layer].getNbrNeurons(); neuron++) {

				for (int link = 0; link < network[layer].getNbrLinks(); link++) {
					double dOdI = DsigFun.apply(network[layer].getNeuronAt(neuron).getInput());
					double weight = network[layer].getNeuronAt(neuron).getWeightTo(link);

					dE[layer][neuron] += dE[layer + 1][link] * weight * dOdI;
				}
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
		for (Layer l : network) {
			sb.append(System.lineSeparator());
			sb.append(l.toString());
		}
		return sb.toString();
	}

}