package neurons;

/**
 * This is an extension of Neuron, used to represent the Neurons in the last
 * Layer (OutputLayer) of a NeuronBasedNeuralNetwork.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see NeuronBasedNeuralNetwork
 * @see Layer
 * @see BiasNeuron
 * @see InputNeuron
 * @see InnerNeuron
 * @see OutputNeuron
 */
public class OutputNeuron extends Neuron {

	/**
	 * Creates a new OutputNeuron (Neuron without links).
	 */
	public OutputNeuron() {
		super(0);
		weights = null;
	}

	/**
	 * Returns the unweighted output of this Neuron.
	 * 
	 * @return the Sigmoid function of the sum of inputs to this Neuron
	 */
	public double getOutput() {
		return sigFun.apply(input);
	}

	/**
	 * Overrides getOutputTo(int index) in superclass (Neuron), always throws IllegalCallerException.
	 * 
	 * @throws IllegalCallerException if called
	 */
	public double getOutputTo(int index) {
		throw new IllegalCallerException("getOutputTo(int index) called on an OutputNeuron.");
	}

	/**
	 * Returns a String representation of the BiasNeuron.
	 * 
	 * @return String describing the BiasNeuron
	 */
	public String toString() {
		return "outN ";
	}

}
