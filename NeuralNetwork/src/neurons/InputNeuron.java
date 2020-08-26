package neurons;

/**
 * This is an extension of Neuron, used to represent the Neurons in the first
 * Layer (InputLayer) in a NeuronBasedNeuralNetwork.
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
public class InputNeuron extends Neuron {

	/**
	 * Creates a new InputNeuron with nbrLinks weighted links.
	 * 
	 * @param nbrLinks number of Neurons this Neuron connects to in the next Layer
	 */
	public InputNeuron(int nbrLinks) {
		super(nbrLinks);
	}

	/**
	 * Returns the unweighted output of this Neuron.
	 * 
	 * @return the the sum of inputs to this Neuron
	 */
	public double getOutput() {
		return input;
	}

	/**
	 * Returns the weighted output of this Neuron (to the given index).
	 * 
	 * @param index index of output
	 * @return output to the link of index
	 */
	public double getOutputTo(int index) {
		return input * weights[index];
	}

	/**
	 * Returns a String representation of the BiasNeuron.
	 * 
	 * @return String describing the BiasNeuron
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("inpN(");
		for (double w : weights) {
			sb.append(w + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(") ");
		return sb.toString();
	}

}
