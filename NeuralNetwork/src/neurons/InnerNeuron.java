package neurons;

/**
 * This is an extension of Neuron. It is used to represent Neurons inside a
 * NeuronBasedNeuralNetwork (in between the input and output Layers).
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
public class InnerNeuron extends Neuron {

	/**
	 * Creates a new InnerNeuron with nbrLinks weighted links
	 *  
	 * @param nbrLinks number of Neurons this Neuron connects to in the next Layer
	 */
	public InnerNeuron(int nbrLinks) {
		super(nbrLinks);
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
	 * Returns the weighted output of this Neuron (to the given index).
	 * 
	 * @param index index of output
	 * @return output to the link of index
	 */
	public double getOutputTo(int index) {
		return weights[index] * sigFun.apply(input);
	}

	/**
	 * Returns a String representation of the BiasNeuron.
	 * 
	 * @return String describing the BiasNeuron
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("innN(");
		for (double w : weights) {
			sb.append(w + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(") ");
		return sb.toString();
	}

}
