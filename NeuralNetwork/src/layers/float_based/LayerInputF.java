package layers.float_based;

import layers.MatrixOperations;

/**
 * Subclass of LayerF, used as the input (first) layer of a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkF
 * @see LayerF
 */
public class LayerInputF extends LayerF {
	/**
	 * Creates a LayerInputF via the LayerF superconstructor.
	 * 
	 * @param nbrNeurons number of neurons
	 * @param nbrLinks   number of links
	 */
	public LayerInputF(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	/**
	 * Returns the output of this layer, the activation function is not applied to
	 * the inputs in this layer.
	 * 
	 * @return output of layer
	 */
	public float[] getOutput() {
		float[][] temp = MatrixOperations.matMultF(weights, input);
		float[] output = new float[bias.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = temp[i][0] + bias[i][0];
		}
		return output;
	}

	/**
	 * Returns a string representation of this layer.
	 * 
	 * @return String representation of layer
	 */
	public String toString() {
		return "InputLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
