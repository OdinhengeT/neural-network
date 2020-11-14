package layers.float_based;

public class LayerOutputF extends LayerF {

	public LayerOutputF(int nbrNeurons) {
		super(nbrNeurons, 1);
		bias = null;
		weights = null;
	}

	public float[] getOutput() {
		float[] output = new float[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = sigmoidFunction.apply(this.getInputTo(i));
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
