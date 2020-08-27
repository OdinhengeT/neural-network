package neurons;

/**
 * This is an extension of Neuron used to represent bias in a
 * NeuronBasedNeuralNetwork.
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
public class BiasNeuron extends Neuron {

	/**
	 * A BiasNeuron acts like a normal Neuron, but has a static input of 1.0. Thus
	 * it is not linked to by any Neuron from the previous layer. However it is
	 * connected to every Neuron in the next layer, just like a regular Neuron.
	 * 
	 * @param nbrLinks number of Neurons this Neuron connects to in the next Layer
	 */
	public BiasNeuron(int nbrLinks) {
		super(nbrLinks);
	}

	/**
	 * Overrides the input method for Bias-Neurons, so it doesn't accidentally get
	 * called.
	 * 
	 * @param new input, has no effect
	 * @throws IllegalCallerException if called
	 */
	public void input(double input) {
		throw new IllegalCallerException("input() called on BiasNeuron");
	}

	/**
	 * Overrides method from Neuron, Bias-Neurons always have the Output of 1.0.
	 * Returns 1.0.
	 * 
	 * @return 1.0
	 */
	public double getOutput() {
		return 1.0;
	}

	/**
	 * Overrides method from Neuron, Because Bias-Neurons always have an output of
	 * 1.0, its output to a given neuron is always equal to the weight. Returns the
	 * weight of the i:th link.
	 * 
	 * @param i index of the link
	 * @return weight of the link with the given index
	 */
	public double getOutputTo(int i) {
		return weights[i];
	}

	/**
	 * Returns a String representation of the BiasNeuron.
	 * 
	 * @return String describing the BiasNeuron
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("B(");
		for (double w : weights) {
			sb.append(w + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(") ");
		return sb.toString();
	}

}
