package layers.float_based;

import layers.MatrixOperations;

public class LayerInnerF extends LayerF {

	public LayerInnerF(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	public float[] getOutput() {
		float[][] temp = MatrixOperations.matMultF(weights, this.getActivation());
		if (!MatrixOperations.areMatSameDimF(temp, bias)) {
			throw new IllegalArgumentException();
		}
		float[] output = new float[bias.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = temp[i][0] + bias[i][0];
		}
		return output; 
	}
	
	public String toString() {
		return "InnerLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
