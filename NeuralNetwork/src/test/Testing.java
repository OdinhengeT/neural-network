package test;

import networks.MatrixBasedNeuralNetwork;

public class Testing {

	public static void main(String[] args) {
		
		int[] layers = new int[] {2, 3, 1};
		
		MatrixBasedNeuralNetwork mbnn = new MatrixBasedNeuralNetwork(layers);
		float[] inp = new float[2];
		inp[0] = 3.7f;
		inp[1] = 2.54f;
		System.out.println(mbnn.run(inp)[0]);
		System.out.println(mbnn.toString());
		
	}

}
