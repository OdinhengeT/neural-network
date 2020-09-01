package neurons;

import java.util.function.Function;
import java.util.Random;

/**
 * This is a simple abstract class describing a Neuron in a
 * NeuronBasedNeuralNetwork, and has several subclasses with different Neurons.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see NeuronBasedNeuralNetwork
 * @see NeuronLayer
 * @see BiasNeuron
 * @see InputNeuron
 * @see InnerNeuron
 * @see OutputNeuron
 */
public abstract class Neuron {

	/**
	 * Non-Linear function (Sigmoid function) applied on the inputs of neurons.
	 */
	protected static Function<Double, Double> sigFun = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	/**
	 * Number of Neurons this Neuron links to in the next layer.
	 */
	protected int nbrLinks;

	/**
	 * The input this Neuron received from the previous layer.
	 */
	protected double input;

	/**
	 * The weights of this Neuron.
	 */
	protected double[] weights;

	/**
	 * Creates a new Neuron (Super constructor) with nbrLinks links with weights,
	 * stored in the double[] weights.
	 * 
	 * @param nbrLinks the number of links
	 */
	public Neuron(int nbrLinks) {
		this.nbrLinks = nbrLinks;
		weights = new double[nbrLinks];
	}

	/**
	 * Assigns a random value to each element in weights, following a Gaussian
	 * (normal) distribution of average 0.0 and standard deviation of 1.0.
	 * 
	 * @param nbrNeurons number of Neurons in the layer of this Neuron
	 */
	public void createWeights(int nbrNeurons) {
		Random rand = new Random();
		for (int index = 0; index < nbrLinks; index++) {
			weights[index] = rand.nextGaussian() / Math.sqrt((double) nbrNeurons);
		}
	}

	/**
	 * @returns nbrLinks (number of links the neuron has)
	 */
	public int getNbrLinks() {
		return nbrLinks;
	}

	/**
	 * Gives the neuron a new input.
	 * 
	 * @param input, the new input
	 */
	public void input(double input) {
		this.input = input;
	}

	/**
	 * Returns the last received input.
	 * 
	 * @returns input
	 */
	public double getInput() {
		return input;
	}

	/**
	 * Returns the Neurons original output, without weights
	 * 
	 * @returns output (defined in some way) of the Neuron
	 */
	public abstract double getOutput();

	/**
	 * Returns the Neurons output multiplied by the i:th weight, the output to the
	 * i:th neuron in the next layer.
	 * 
	 * @param index index of element to return
	 * 
	 * @returns output (defined in some way) of the Neuron to the i:th connected
	 *          Neuron
	 */
	public abstract double getOutputTo(int index);

	/**
	 * Returns the i:th element in weights, the weight of the i:th link.
	 * 
	 * @param i index of element to return
	 */
	public double getWeightTo(int index) {
		return weights[index];
	}

	/**
	 * Nugdes the weights of this Neuron by amounts provided in argument,
	 * deltaWeights.
	 * 
	 * @param deltaWeights double[] containing the nugdes for each weight
	 * 
	 * @throws IllegalArgumentException if deltaWeights is of wrong length
	 */
	public void updateWeights(double[] deltaWeights) {
		if (deltaWeights.length != nbrLinks)
			throw new IllegalArgumentException("Incorrect number of weights to change.");
		for (int index = 0; index < nbrLinks; index++) {
			weights[index] += deltaWeights[index];
		}
	}

	/**
	 * Sets the weights of this Neuron to the values provided in argument,
	 * deltaWeights .
	 * 
	 * @param deltaWeights double[] containing the new weights
	 * 
	 * @throws IllegalArgumentException if deltaWeights is of wrong length
	 */
	public void setWeights(double[] deltaWeights) {
		if (deltaWeights.length != nbrLinks)
			throw new IllegalArgumentException("Incorrect number of weights to change.");
		for (int index = 0; index < nbrLinks; index++) {
			weights[index] = deltaWeights[index];
		}
	}

	/**
	 * Returns a String representation of the Neuron.
	 * 
	 * @returns a String describing the Neuron
	 */
	public abstract String toString();

}