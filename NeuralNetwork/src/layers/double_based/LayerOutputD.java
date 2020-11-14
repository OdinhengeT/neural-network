package layers.double_based;

/**
 * Subclass of LayerF, used as the hidden (inner) layers of a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkD
 * @see LayerD
 */
public class LayerOutputD extends LayerD {
	/**
	 * Creates a LayerOutputD via the LayerF superconstructor. Overrides
	 * super.weights and super.bias with null values
	 * 
	 * @param nbrNeurons number of neurons
	 */
	public LayerOutputD(int nbrNeurons) {
		super(nbrNeurons, 1);
		bias = null;
		weights = null;
	}

	/**
	 * Returns the output of this layer. Since this is the last layer, there are no
	 * weights, instead the output is given by the activation of the neurons in the
	 * layer
	 * 
	 * @return output of layer
	 */
	public double[] getOutput() {
		double[] output = new double[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = sigmoidFunction.apply(this.getInputTo(i));
		}
		return output;
	}

	/**
	 * Returns a string representation of this layer.
	 * 
	 * @return String representation of layer
	 */
	public String toString() {
		return "OutputLayer: " + input.length + " neurons without links";
	}

}
