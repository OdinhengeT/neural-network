package layers.double_based;

public class LayerOutputD extends LayerD {

	public LayerOutputD(int nbrNeurons) {
		super(nbrNeurons, 1);
		bias = null;
		weights = null;
	}

	public double[] getOutput() {
		double[] output = new double[input.length];
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
