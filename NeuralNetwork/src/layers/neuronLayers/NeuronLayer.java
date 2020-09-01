package layers.neuronLayers;

import neurons.*;

/**
 * This abstract class contain all basic methods and attributes of a Neuron
 * based Layer.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see NeuronBasedNeuralNetwork
 * @see InputNeuronLayer
 * @see InnerNeuronLayer
 * @see OutputNeuronLayer
 * @see Neuron
 */
public abstract class NeuronLayer {

	protected Neuron[] neurons;
	protected int nbrNeurons;
	protected int nbrLinks;

	/*
	 * Creates a Layer (Super constructor) with nbrNeurons Neurons (excluding
	 * eventual BiasNeurons) each with nbrLinks links.
	 */
	public NeuronLayer(int nbrNeurons, int nbrLinks) {
		this.nbrNeurons = nbrNeurons;
		this.nbrLinks = nbrLinks;
	}

	/**
	 * Gives each non-BiasNeuron a new input given by corresponding element in
	 * double[] input. Input is updated by the input(double) method in Neuron.
	 * 
	 * @param input double[] with an input for each Neuron in the Layer
	 * 
	 * @throws IllegalArgumentException if input is of incorrect length
	 */
	public void input(double[] input) {
		if (input.length != nbrNeurons) {
			throw new IllegalArgumentException("Incorrect number of inputs.");
		}
		for (int i = 0; i < nbrNeurons; i++) {
			neurons[i].input(input[i]);
		}
	}

	/**
	 * Returns the number of Neurons (excluding eventual BiasNeurons) present in
	 * the Layer.
	 * 
	 * @returns nbrNeurons the number of Neurons in the Layer
	 */
	public int getNbrNeurons() {
		return nbrNeurons;
	}

	/**
	 * Returns the number of links each Neuron in the Layer has.
	 * 
	 * @returns nbrLinks, number of links each Neuron has
	 */
	public int getNbrLinks() {
		return nbrLinks;
	}

	/**
	 * Returns all the Neurons in the Layer.
	 * 
	 * @returns neurons Neuron[] with all Neurons in the Layer
	 */
	public Neuron[] getNeurons() {
		return neurons;
	}

	/**
	 * Returns the Neuron at index index in the Layer, may return a BiasNeurons.
	 * 
	 * @param index index of Neuron to return
	 * @returns the Neuron at index index
	 */
	public Neuron getNeuronAt(int index) {
		return neurons[index];
	}

	/**
	 * Returns the output of the Layer.
	 * 
	 * @returns an output from the layer, defined in some way, as a double[]
	 */
	public abstract double[] getOutput();

	/**
	 * Takes a double[][], one double[] for each neuron in the network (including
	 * eventual BiasNeurons) and updates their weights with their updateWeight
	 * method.
	 * 
	 * @param deltaWeights a double[][] containing all the weight nudges for each
	 *                     neuron in the Layer
	 * @throws IllegalArgumentException if deltaWeights does not have the same
	 *                                  length as the number of Neurons in the Layer
	 *                                  (Including eventual BiasNeurons)
	 */
	public void updateWeights(double[][] deltaWeights) {
		if (deltaWeights.length != nbrNeurons + 1)
			throw new IllegalArgumentException("Incorrect number of neurons to update");
		for (int neuron = 0; neuron < nbrNeurons + 1; neuron++) {
			neurons[neuron].updateWeights(deltaWeights[neuron]);
		}
	}

	/**
	 * Returns a String representation of the Layer.
	 * 
	 * @returns a String describing the Layer
	 */
	public abstract String toString();

}
