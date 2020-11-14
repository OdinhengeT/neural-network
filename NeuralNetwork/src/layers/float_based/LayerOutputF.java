package layers.float_based;

/**
 * Subclass of LayerF, used as the hidden (inner) layers of a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkF
 * @see LayerF
 */
public class LayerOutputF extends LayerF {
	/**
	 * Creates a LayerOutputF via the LayerF superconstructor. Overrides
	 * super.weights and super.bias with null values
	 * 
	 * @param nbrNeurons number of neurons
	 */
	public LayerOutputF(int nbrNeurons) {
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
	public float[] getOutput() {
		float[] output = new float[input.length];
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
