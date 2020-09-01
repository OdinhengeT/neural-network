package layers.matrixLayers;

public class InputMatrixLayer extends MatrixLayer {

	public InputMatrixLayer(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	public float[] getOutput() {
		float[][] temp = matrixMult(weights, input);
		if (!matrixIsOfSameDimension(temp, bias)) {
			throw new IllegalArgumentException();
		}
		float[] output = new float[bias.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = temp[i][0] + bias[i][0];
		}
		return output;
	}

	public String toString() {
		return "InputLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
