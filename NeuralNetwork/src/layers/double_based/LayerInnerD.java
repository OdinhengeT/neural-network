package layers.double_based;

import layers.MatrixOperations;

public class LayerInnerD extends LayerD {

	public LayerInnerD(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	public double[] getOutput() {
		double[][] temp = MatrixOperations.matMultD(weights, this.getActivation());
		if (!MatrixOperations.areMatSameDimD(temp, bias)) {
			throw new IllegalArgumentException();
		}
		double[] output = new double[bias.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = temp[i][0] + bias[i][0];
		}
		return output; 
	}
	
	public String toString() {
		return "InnerLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
