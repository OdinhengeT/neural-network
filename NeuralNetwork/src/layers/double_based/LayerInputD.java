package layers.double_based;

import layers.MatrixOperations;

/**
 * Subclass of LayerD, used as the input (first) layer of a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkD
 * @see LayerD
 */
public class LayerInputD extends LayerD {
	/**
	 * Creates a LayerInputF via the LayerF superconstructor.
	 * 
	 * @param nbrNeurons number of neurons
	 * @param nbrLinks   number of links
	 */
	public LayerInputD(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	/**
	 * Returns the output of this layer, the activation function is not applied to
	 * the inputs in this layer.
	 * 
	 * @return output of layer
	 */
	public double[] getOutput() {
		double[][] temp = MatrixOperations.matMultD(weights, input);
		double[] output = new double[bias.length];
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
