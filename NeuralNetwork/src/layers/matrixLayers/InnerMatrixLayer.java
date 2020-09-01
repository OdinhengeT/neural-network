package layers.matrixLayers;

public class InnerMatrixLayer extends MatrixLayer {

	public InnerMatrixLayer(int nbrNeurons, int nbrLinks) {
		super(nbrNeurons, nbrLinks);
	}

	public float[] getOutput() {
		float[][] temp = matrixMult(weights, input);
		if (!matrixIsOfSameDimension(temp, bias)) {
			throw new IllegalArgumentException();
		}
		float[] output = new float[bias.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = sigFun.apply(temp[i][0] + bias[i][0]);
		}
		return output;
	}
	
	public String toString() {
		return "InnerLayer: " + input.length + " neurons with " + bias.length + " links";
	}

}
