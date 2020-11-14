package layers.double_based;

import layers.MatrixOperations;

/**
 * Subclass of LayerD, used as the hidden (inner) layers of a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see NeuralNetworkD
 * @see LayerD
 */
public class LayerInnerD extends LayerD {
	/**
	 * Creates a LayerInnerF via the LayerF superconstructor.
	 * 
	 * @param nbrNeurons number of neurons
	 * @param nbrLinks   number of links
	 */
	public LayerInnerD(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	/**
	 * Returns the output of this layer.
	 * 
	 * @return output of layer
	 */
	public double[] getOutput() {
		double[][] temp = MatrixOperations.matMultD(weights, this.getActivation());
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
		return "InnerLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
