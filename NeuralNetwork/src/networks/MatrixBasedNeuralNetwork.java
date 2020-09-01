package networks;

import layers.matrixLayers.*;

public class MatrixBasedNeuralNetwork{

	private MatrixLayer[] network;
	private int nbrLayers;
	
	public MatrixBasedNeuralNetwork(int[] layers) {
		nbrLayers = layers.length;
		network = new MatrixLayer[nbrLayers];
		network[0] = new InputMatrixLayer(layers[0], layers[1]);
		for (int L = 1; L < nbrLayers - 1; L++) {
			network[L] = new InnerMatrixLayer(layers[L], layers[L + 1]);
		}
		network[nbrLayers - 1] = new OutputMatrixLayer(layers[nbrLayers - 1]);
	}

	public float[] run(float[] input) {
		if (input.length != network[0].getNbrNeurons()) {
			throw new IllegalArgumentException("Input of wrong length");
		}
		for (int i = 0; i < nbrLayers; i++) {
			network[i].input(input);
			input = network[i].getOutput();
		}
		return input;
	}

	public void train(float[][] input, float[][] target) {
		if (input.length != target.length) {
			throw new IllegalArgumentException("input and target not of same length");
		}
		
		int nbrTrainingCases = input.length;
		
		for (int trainingCase = 0; trainingCase < nbrTrainingCases; trainingCase++) {
			//how we gonna do dis??
		}
		
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("==MatrixBasedNeuralNetwork==" + System.lineSeparator());
		for (int i = 0; i < nbrLayers; i++) {
			sb.append(network[i].toString() + System.lineSeparator());
		}
		return sb.toString();
	}

}
