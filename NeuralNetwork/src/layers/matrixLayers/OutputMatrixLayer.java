package layers.matrixLayers;

public class OutputMatrixLayer extends MatrixLayer {

	public OutputMatrixLayer(int nbrNeurons) {
		super(nbrNeurons, 1);
		bias = null;
	}

	public float[] getOutput() {
		float[][] temp = matrixMult(weights, input);
		float[] output = new float[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = sigFun.apply(temp[i][0]);
		}
		return output;
	}
	
	public void updateBias(float[][] deltaBias) {
		throw new IllegalCallerException("");
	}

	public String toString() {
		return "OutputLayer: " + input.length + " neurons without links";
	}

}
